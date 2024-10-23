/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.ClasesDao;
import modelo.Reserva;
import persistencia.SQLiteManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import modelo.Capacidad;
import modelo.Cliente;
import modelo.Mesa;
import modelo.Tarjeta;
import modelo.Ubicacion;
import java.sql.Date;

/**
* Esta clase se encarga de realizar operaciones sobre la tabla reservas de la base
* de datos. 
* Tales como guardar los datos de la reserva, indicar si una mesa está disponible
* para una fecha y hora determinada, obtener un listado de mesas, obtener el historial
* de reservas de un cliente.
* Se encarga de conectar a la base de datos y manejar las consultas necesarias para
* gestionar las reservas.
* @author Marcos Ramon Caraballo, Angelina María Vialle,
* @version 27/10/2024
*/
public class ReservaDAO {
    
    private SQLiteManager conn = new SQLiteManager();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    /**
     * Constructor por defecto de la clase ReservaDAO.
     */
    public ReservaDAO(){}
  
    /**
    * Este método se encarga de guardar los datos de la reserva en la base de datos,
    * en su correspondiente tabla.
    * @param    reserva es un objeto de tipo reserva con todos los datos necesarios
    * para hacer una reserva.
    * @return   true si se guardaron los datos en la base de datos, false en caso
    * contrario
    */
    public boolean realizarReserva(Reserva reserva){
         boolean realizado = false;
         con = conn.getConexion();
         ResultSet rs;
         String sql = "INSERT into reserva (fecha,hora,comentario,num_mesa,id_cliente,id_tarjeta) VALUES(?,?,?,?,?,?)";
         try {
            ps = con.prepareStatement(sql);
            ps.setString(1, reserva.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ps.setString(2,reserva.getHora().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            ps.setString(3, reserva.getComentario());
            ps.setInt(4, reserva.getMesa().getNumero());
            ps.setInt(5,reserva.getCliente().getId());
            ps.setString(6, reserva.getTarjeta().getNumero());
            realizado = (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println("Prueba 1"+e.toString());
        }finally {
            conn.cerrarConexion();
        } 
        return realizado;
    }
    
    /**
    * Este método te indica si la mesa se encuentra disponible en una fecha y hora determinada
    * @param    numMesa indica el número de la mesa que se revisara si esta disponible
    * @param    fecha indica la fecha en la que se revisara si esta disponible la mesa
    * @param    hora indica la hora que se revisara para saber si la mesa está disponible
    * @return   true en caso de que la mesa este disponible, en caso contrario false
    */
    public boolean mesaDisponible(int numMesa, LocalDate fecha, LocalTime hora){
        boolean disponible = false;
        Connection con = conn.getConexion();
        ResultSet rs;
        String sql = "SELECT * FROM reserva WHERE num_mesa = ? AND fecha = ? AND hora = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,numMesa);
            ps.setString(2, fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ps.setString(3, hora.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            
            rs = ps.executeQuery();
            if(!rs.next()){
                disponible = true;
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally {
            conn.cerrarConexion();
        }
        
        return disponible;
    }
    
    /**
    * Se encarga de guardar todas las mesas de la base de datos en un ArrayList 
    * de tipo Mesa.
    * return    una lista con todas las mesas.
    */
    public ArrayList<Mesa> mesas(){
        Connection con = conn.getConexion();
        ResultSet rs;
        PreparedStatement ps;
        ArrayList<Mesa> mesas = new ArrayList<Mesa>();
        String sql = "SELECT * FROM mesa";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Mesa mesa = new Mesa();
                mesa.setNumero(rs.getInt("numero"));
                Capacidad c = null;
                switch (rs.getString("capacidad")){
                    case "dos":
                        c = Capacidad.dos;
                        break;
                    case "cuatro":
                        c = Capacidad.cuatro;
                        break;
                    case "seis":
                        c = Capacidad.seis;
                        break;
                    case "ocho":
                        c = Capacidad.ocho;
                        break;
                    case "diez":
                        c = Capacidad.diez;
                        break;
                    default:
                        break;
                }
                mesa.setCapacidad(c);
                
                Ubicacion u = null;
                switch (rs.getString("ubicacion")){
                    case "Interior":
                        u = Ubicacion.Interior;
                        break;
                    case "Exterior":
                        u = Ubicacion.Exterior;
                        break;
                    default:
                        break;
                }
                mesa.setUbicacion(u);
                mesas.add(mesa);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally {
           conn.cerrarConexion();
        }
        return mesas;
    }
    
    /**
    * Se encarga de obtener el historial de reservas de un cliente. 
    * @param    c es un objeto de tipo cliente que indican todos los datos de un 
    * cliente.
    * @return   una lista con el historia de las reservas de ese cliente.
    */
    public ArrayList<Reserva> obtenerReservasHistorial(Cliente c){
        Connection con = conn.getConexion();
        ResultSet rs;
        PreparedStatement ps;
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        String sql = "SELECT rTabla.*, mTabla.*, tTabla.*, "
                + "tTabla.nombre AS nombre_tarjeta, tTabla.emisor,"
                + "tTabla.numero AS numero_tarjeta, tTabla.cod_seguridad"
                + " FROM reserva AS rTabla INNER JOIN mesa AS mTabla "
                + "ON rTabla.num_mesa = mTabla.numero  "
                + "LEFT JOIN tarjeta AS tTabla "
                + "ON rTabla.id_tarjeta = tTabla.numero  "
                + "WHERE id_cliente = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,c.getId());
            
            rs = ps.executeQuery();
            while(rs.next()){
                Reserva reserva = new Reserva();
                reserva.setCliente(c);
                reserva.setComentario(rs.getString("comentario"));
                reserva.setAsistencia(Boolean.valueOf(rs.getString("asistencia")));
                
                reserva.setFecha(LocalDate.parse(rs.getString("fecha"), 
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                reserva.setHora(LocalTime.parse(rs.getString("hora"), 
                        DateTimeFormatter.ofPattern("HH:mm:ss")));
                
                int mesaNum = rs.getInt("numero");
                Capacidad mesaUbi = Capacidad.valueOf(rs.getString("Capacidad"));
                Ubicacion mesaCap = Ubicacion.valueOf(rs.getString("Ubicacion"));
                
                reserva.setMesa(new Mesa(mesaNum,mesaUbi,mesaCap,reserva));
                
                String tNombre = rs.getString("nombre_tarjeta");
                String tNum = rs.getString("numero_tarjeta");
                String tEmi = rs.getString("emisor");
                String tCodSeguridad = rs.getString("cod_seguridad");
                
                reserva.setTarjeta(new Tarjeta(tNombre,tNum,tEmi,tCodSeguridad,reserva));
                
                reservas.add(reserva);
            }
        }catch(SQLException e){
            System.out.println("hola"+e.toString());
        }finally{
            conn.cerrarConexion();
        }
        return reservas;
    }
    
    /**
     * Obtener todas las reservas del dia de hoy
     */
     public ArrayList<Reserva> obtenerReservasDeHoy() {
        Connection con = conn.getConexion();
        ResultSet rs;
        PreparedStatement ps;
        LocalDate hoy = LocalDate.now();
        String sql = "SELECT * FROM reserva WHERE fecha=?";
        ArrayList<Reserva> reservasDeHoy = new ArrayList<Reserva>();
        
        
        try{
             ps = con.prepareStatement(sql);
             ps.setString(1, hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            rs = ps.executeQuery();
            while (rs.next()) {
                Mesa mesa = obtenerMesaPorNumero(rs.getInt("num_mesa"));
                ClienteDAO cliente = new ClienteDAO();
                String [] vector = new  String [3];
                vector = cliente.mostrarDatos(rs.getInt("id_cliente")); //nombre, correo, telefono
                Cliente clienteActual = new Cliente(); 
                clienteActual.setNombre(vector[0]);
                clienteActual.setCorreo(vector[1]);
                clienteActual.setTelefono(vector[2]);
                Reserva reserva1 = new Reserva();
                reserva1.setCliente(clienteActual);
                /**
                Reserva reserva = new Reserva(
                        mesa,
                        rs.getString("cliente"),
                        LocalDateTime.parse(rs.getString("hora_inicio")),
                        LocalDateTime.parse(rs.getString("hora_fin")),
                        rs.getBoolean("asistido")
                );
                */
                reservasDeHoy.add(reserva1);
            }
        
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally{
            conn.cerrarConexion();
        }
        return reservasDeHoy;
    }
     
     /**
      * Metodo que se encarga de buscar una mesa por su numero de mesa.
      * @param  num representa el numero de mesa
      * @return una mesa con numero, capacidad y ubicacion.
      */
     public Mesa obtenerMesaPorNumero(int num){
         Connection con = conn.getConexion();
         ResultSet rs;
         PreparedStatement ps;
         Mesa mesa = new Mesa();
         String sql= "SELECT * FROM mesa WHERE numero=?";
         try{
             ps=con.prepareStatement(sql);
             ps.setInt(1,num);
             rs=ps.executeQuery();
             
             while(rs.next()){
                 int numero = rs.getInt("numero");
                 Ubicacion ubicacion =  Ubicacion.valueOf(rs.getString("ubicacion").toUpperCase());
                 Capacidad capacidad =  Capacidad.valueOf(rs.getString("capacidad").toUpperCase());
                 mesa.setNumero(numero);
                 mesa.setUbicacion(ubicacion);
                 mesa.setCapacidad(capacidad);
             }
         }catch(SQLException e){
             System.out.println(e.getMessage());
         }finally{
             conn.cerrarConexion();    
         }
         return mesa;
     }
}
