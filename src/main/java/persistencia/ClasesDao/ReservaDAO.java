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
 *
 * @author marco
 */


public class ReservaDAO {
    
    private SQLiteManager conn = new SQLiteManager();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
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
        } finally {
           try {
                conn.cerrarConexion();
            }catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return realizado;
    }
    
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
        }try {
                conn.cerrarConexion();
            }catch (Exception e) {
                System.out.println(e.toString());
            }
        
        return disponible;
    }
    
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
        } finally{
            try {
                conn.cerrarConexion();
            }catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        
        return mesas;
    }
    
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
}
