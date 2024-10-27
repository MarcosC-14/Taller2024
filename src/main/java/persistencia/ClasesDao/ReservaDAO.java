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

/**
* Esta clase se encarga de realizar operaciones sobre la tabla reservas de la base
* de datos. 
* Tales como guardar los datos de la reserva, indicar si una mesa está disponible
* para una fecha y hora determinada, obtener un listado de mesas, obtener el historial
* de reservas de un cliente, obtener todas las reservas del dia de hoy y obtener la mesa
* por el numero de mesa.
* Se encarga de conectar a la base de datos y manejar las consultas necesarias para
* gestionar las reservas.
* @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
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
                sql = "SELECT * FROM bloqueo_evento WHERE fecha = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1,fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                
                rs = ps.executeQuery();
                while(rs.next()){
                    if(rs.getInt("mesa") == numMesa){
                        disponible = false;
                        break;
                    }else{
                        if(rs.getString("hora_inicio") != null
                                && rs.getString("hora_fin") != null){
                            
                            LocalTime auxHoraI = LocalTime.parse(rs.getString("hora_inicio"), 
                            DateTimeFormatter.ofPattern("HH:mm:ss"));
                        
                            LocalTime auxHoraF = LocalTime.parse(rs.getString("hora_fin"), 
                            DateTimeFormatter.ofPattern("HH:mm:ss"));

                            if((auxHoraI.isBefore(hora)
                                && auxHoraF.isAfter(hora))
                                    || hora.equals(auxHoraI)
                                    || hora.equals(auxHoraF)){
                                disponible = false;
                                break;
                            }
                        }
                    }
                }
                
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
                reserva.setId(rs.getInt("id"));
                reserva.setCliente(c);
                reserva.setComentario(rs.getString("comentario"));
                reserva.setAsistencia(rs.getBoolean("asistencia"));
                
                reserva.setFecha(LocalDate.parse(rs.getString("fecha"), 
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                reserva.setHora(LocalTime.parse(rs.getString("hora"), 
                        DateTimeFormatter.ofPattern("HH:mm:ss")));
                
                if(rs.getString("hora_fin")!= null){
                    reserva.setTiempoFinalizacion(LocalTime.parse(rs.getString("hora_fin"), 
                        DateTimeFormatter.ofPattern("HH:mm:ss")));
                }else{
                    reserva.setTiempoFinalizacion(null);
                }
                if(rs.getString("hora_inicio")!= null){
                    reserva.setTiempoOcupacion(LocalTime.parse(rs.getString("hora_inicio"), 
                        DateTimeFormatter.ofPattern("HH:mm:ss")));
                }else{
                    reserva.setTiempoOcupacion(null);
                }
                
                int mesaNum = rs.getInt("numero");
                Capacidad mesaCap = Capacidad.valueOf(rs.getString("capacidad"));
                Ubicacion mesaUbi = Ubicacion.valueOf(rs.getString("ubicacion"));
                reserva.setMesa(new Mesa(mesaNum,mesaCap,mesaUbi,reserva));
                
                String tNombre = rs.getString("nombre_tarjeta");
                String tNum = rs.getString("numero_tarjeta");
                String tEmi = rs.getString("emisor");
                String tCodSeguridad = rs.getString("cod_seguridad");
                
                reserva.setTarjeta(new Tarjeta(tNombre,tEmi,tNum,tCodSeguridad));
                
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
     * Te devuelve una lista de reservas de dia de hoy.
     * @return  las reservas de clientes del dia de hoy.
     */
     public ArrayList<Reserva> obtenerReservasDeHoy() {
        Connection con = conn.getConexion();
        ResultSet rs;
        PreparedStatement ps;
        LocalDate hoy = LocalDate.now();
        String sql = "SELECT * FROM reserva WHERE fecha=?";
        String baseCliente="SELECT * FROM reserva ";
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
                reserva1.setFecha(hoy);
                reserva1.setId(rs.getInt("id"));
                reserva1.setComentario(rs.getString("comentario"));
                reserva1.setCliente(clienteActual);
                if(rs.getInt("asistencia")==1){
                    reserva1.setAsistencia(true);
                }else{
                    reserva1.setAsistencia(false);
                }
                reserva1.setMesa(mesa);
                reserva1.setHora(LocalTime.parse(rs.getString("hora"), 
                        DateTimeFormatter.ofPattern("HH:mm:ss")));
                if(rs.getString("hora_fin")!= null){
                    reserva1.setTiempoFinalizacion(LocalTime.parse(rs.getString("hora_fin"), 
                        DateTimeFormatter.ofPattern("HH:mm:ss")));
                }else{
                    reserva1.setTiempoFinalizacion(null);
                }
                if(rs.getString("hora_inicio")!= null){
                    reserva1.setTiempoOcupacion(LocalTime.parse(rs.getString("hora_inicio"), 
                        DateTimeFormatter.ofPattern("HH:mm:ss")));
                }else{
                    reserva1.setTiempoOcupacion(null);
                }
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
                 Ubicacion ubicacion =  Ubicacion.valueOf(rs.getString("ubicacion"));
                 Capacidad capacidad =  Capacidad.valueOf(rs.getString("capacidad"));
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
     /**
      * Sirve para que cambiar la asistencia de una reserva. 
      * @param  numMesa representa el numero de mesa.
      * @param  hora representa la hora de la reserva.
      * @param asistencia representa la asistencia anterior que tenia
      * @return true si la cambio, false si no la cambio.
      */
     public boolean cambiarAsistencia( int numMesa, LocalTime hora, boolean asistencia){
        
         boolean actualizacion=false;
         Connection con = conn.getConexion();
         ResultSet rs;
         PreparedStatement ps;
         String sql ="UPDATE reserva SET asistencia = ? WHERE num_mesa=? AND hora=?";
         try{
             ps=con.prepareStatement(sql);
             if(asistencia){
             ps.setInt(1,0);
             }else{
             ps.setInt(1, 1);
             }
             ps.setInt(2,numMesa);
             ps.setString(3, hora.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
             actualizacion=(ps.executeUpdate()>0);
         }catch(SQLException e){
             System.out.println(e.getMessage());
         }finally{
             conn.cerrarConexion();
         }
         return actualizacion;
     }
     
     /**
      * Se encarga de modificar una reserva existente de un cliente
      * @param reserva representa la reserva a modificar
      * @return false si no se pudo modificar, true si se modificó con éxito
      */
     public boolean modificarReserva(Reserva reserva){
         boolean realizado = false;
         con = conn.getConexion();
         ResultSet rs;
         String sql = "UPDATE reserva SET fecha = ?, hora = ?, "
                 + "comentario = ?, num_mesa = ?, id_cliente= ?,"
                 + " id_tarjeta = ? WHERE id = ?";
         try {
            ps = con.prepareStatement(sql);
            ps.setString(1, reserva.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ps.setString(2,reserva.getHora().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            ps.setString(3, reserva.getComentario());
            ps.setInt(4, reserva.getMesa().getNumero());
            ps.setInt(5,reserva.getCliente().getId());
            ps.setString(6, reserva.getTarjeta().getNumero());
            ps.setInt(7, reserva.getId());
            realizado = (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            conn.cerrarConexion();
        } 
        return realizado;
     }
     /**
      * Se encarga de eliminar una reserva de la base de datos
      * @param reserva representa la reserva a eliminar
      * @return false si no se pudo eliminar, true si se eliminó con éxito
      */
     public boolean eliminarReserva(Reserva reserva){
         boolean eliminado = false;
         con = conn.getConexion();
         ResultSet rs;
         String sql = "DELETE FROM reserva WHERE id = ?";
         try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, reserva.getId());
            eliminado = (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            conn.cerrarConexion();
        } 
        return eliminado;
     }
     
     /**
      * Método que guarda el tiempo de ocupación y el de finalización en caso
      * de que hayan sido modificados
      * @param reserva representa la reserva a modificar
      * @return false si no se pudo modificar, true si modificó con éxito
      */
     public boolean modificarTiempoOcupacionFin(Reserva reserva){
         boolean modificado = false;
         con = conn.getConexion();
         ResultSet rs;
         String sql = "UPDATE reserva SET hora_inicio = ?,"
                 + " hora_fin = ?"
                 + " WHERE id = ?";
         try {
             
             System.out.println(reserva.getTiempoOcupacion());
             System.out.println(reserva.getTiempoFinalizacion());
            ps = con.prepareStatement(sql);
            if(reserva.getTiempoOcupacion() != null){
                ps.setString(1, reserva.getTiempoOcupacion().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }else{
                ps.setString(1,null);
            }
            if(reserva.getTiempoFinalizacion()!= null){
                ps.setString(2,reserva.getTiempoFinalizacion().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }else{
                ps.setString(2,null);
            }
            ps.setInt(3, reserva.getId());
             System.out.println(reserva.getId());
            modificado = (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            conn.cerrarConexion();
        } 
        return modificado;
     }
     	  /**
	  *ArrayList que devuelva todas las reservas pasadas, si su fecha es menor a hoy lo guarda en su arrayList
	  */
	  public ArrayList<Reserva> obtenerReservasPasadas(){
		Connection con = conn.getConexion();
                ResultSet rs;
                PreparedStatement ps;
		LocalDate hoy = LocalDate.now();
		ArrayList<Reserva> reservasPasadas = new ArrayList<Reserva>();
		String sql="SELECT * FROM reserva WHERE asistencia = 0 AND multa = 0";
		 try{
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    
			 while (rs.next()) {
                                if(LocalDate.parse(rs.getString("fecha"),formatoFecha).isBefore(hoy)){
                                    
                                    Reserva reserva = new Reserva();
                                    reserva.setId(rs.getInt("id"));
                                    String fechaString = rs.getString("fecha");
                                    LocalDate fechaReserva = LocalDate.parse(fechaString, formatoFecha);
                                    reserva.setFecha(fechaReserva);
                                    reserva.setComentario(rs.getString("comentario"));
                                    reserva.setHora(LocalTime.parse(rs.getString("hora")));
                                    if(rs.getInt("asistencia")==0&&(rs.getInt("multa"))==0){
                                        reserva.setAsistencia(false);
                                        reserva.setMulta(false);
                                        reservasPasadas.add(reserva);
                                    
                                }
                         
                                }
                         }
                 }catch(SQLException e){
				System.out.println(e.getMessage());
			}finally{
				conn.cerrarConexion();
			}
		
	  
            return reservasPasadas;
        }
         /**
            * Cobrar la multa al dia siguiente (automatico), cambie el estado de
            * multa de Reserva= true, se guarda en base de datos como 1
      */
	  public boolean cobrarMulta(ArrayList<Reserva> reservas){
              Connection con = conn.getConexion();
                ResultSet rs;
                PreparedStatement ps;
		String sql="UPDATE reserva SET multa = ? WHERE id = ?";
		boolean cobrar= false;
                for(Reserva reserva: reservas){
                    if(reserva.getAsistencia()==false && reserva.getMulta()==false){
                        reserva.setMulta(true);
                        
                    try{
                    ps = con.prepareStatement(sql);
                    ps.setInt(1,1);
                    ps.setInt(2, reserva.getId());
                    cobrar =(ps.executeUpdate()>0);				
                    }catch(SQLException e){
			System.out.println(e.getMessage());
                    } 
                    }
                    }
                
		conn.cerrarConexion();
                      
		return cobrar;
	  } 
          
          /**
           * Cuenta todas las asistencias en la base de datos
           * de un cliente pasado por parámetro
           * @param cliente representa el cliente al que se contarán
           * sus asistencias
           * @return    un entero que representa la cantidad de asistencias
           */
          public int cantidadAsistencias(Cliente cliente){
              Connection con = conn.getConexion();
              ResultSet rs;
              PreparedStatement ps;
              String sql ="SELECT COUNT(*) FROM reserva"
                      + " WHERE id_cliente = ? AND asistencia = 1";
              int cantidad = 0;
              try{
                  ps=con.prepareStatement(sql);
                  ps.setInt(1, cliente.getId());
               
                  rs = ps.executeQuery();
                  if(rs.next()){
                      cantidad = rs.getInt(1);
                  }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }finally{
                conn.cerrarConexion();
            }
            return cantidad;
              
              
            
          }
          
          public ArrayList<Reserva> obtenerReservas(){
            Connection con = conn.getConexion();
            ResultSet rs;
            PreparedStatement ps;
            ArrayList<Reserva> reservas = new ArrayList<Reserva>();
            String sql = "SELECT rTabla.*, mTabla.*, tTabla.*, cTabla.*, "
                    + "cTabla.id AS id_cTabla, "
                    + "tTabla.nombre AS nombre_tarjeta, tTabla.emisor, "
                    + "tTabla.numero AS numero_tarjeta, tTabla.cod_seguridad "
                    + " FROM reserva AS rTabla INNER JOIN mesa AS mTabla "
                    + "ON rTabla.num_mesa = mTabla.numero "
                    + "LEFT JOIN tarjeta AS tTabla "
                    + "ON rTabla.id_tarjeta = tTabla.numero "
                    + "INNER JOIN cliente AS cTabla "
                    + "ON rTabla.id_cliente = cTabla.id ";
            try{
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Reserva reserva = new Reserva();
                    reserva.setId(rs.getInt("id"));
                    Cliente c = new Cliente();
                    c.setNombre(rs.getString("nombre"));
                    c.setCorreo(rs.getString("correo"));
                    c.setContrasenia(rs.getString("contrasenia"));
                    c.setId(rs.getInt("id_cTabla"));
                    c.setTelefono("telefono");
                    
                    reserva.setCliente(c);
                    
                    reserva.setComentario(rs.getString("comentario"));
                    reserva.setAsistencia(rs.getBoolean("asistencia"));

                    reserva.setFecha(LocalDate.parse(rs.getString("fecha"), 
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    reserva.setHora(LocalTime.parse(rs.getString("hora"), 
                            DateTimeFormatter.ofPattern("HH:mm:ss")));

                    if(rs.getString("hora_fin")!= null){
                        reserva.setTiempoFinalizacion(LocalTime.parse(rs.getString("hora_fin"), 
                            DateTimeFormatter.ofPattern("HH:mm:ss")));
                    }else{
                        reserva.setTiempoFinalizacion(null);
                    }
                    if(rs.getString("hora_inicio")!= null){
                        reserva.setTiempoOcupacion(LocalTime.parse(rs.getString("hora_inicio"), 
                            DateTimeFormatter.ofPattern("HH:mm:ss")));
                    }else{
                        reserva.setTiempoOcupacion(null);
                    }

                    int mesaNum = rs.getInt("numero");
                    Capacidad mesaUbi = Capacidad.valueOf(rs.getString("Capacidad"));
                    Ubicacion mesaCap = Ubicacion.valueOf(rs.getString("Ubicacion"));

                    reserva.setMesa(new Mesa(mesaNum,mesaUbi,mesaCap,reserva));

                    String tNombre = rs.getString("nombre_tarjeta");
                    String tNum = rs.getString("numero_tarjeta");
                    String tEmi = rs.getString("emisor");
                    String tCodSeguridad = rs.getString("cod_seguridad");

                    reserva.setTarjeta(new Tarjeta(tNombre,tEmi,tNum,tCodSeguridad));

                    reservas.add(reserva);
                }
            }catch(SQLException e){
                System.out.println("hola"+e.toString());
            }finally{
                conn.cerrarConexion();
            }
            return reservas;
          }
          
}
