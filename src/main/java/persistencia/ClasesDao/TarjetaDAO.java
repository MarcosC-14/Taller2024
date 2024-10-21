/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.ClasesDao;
import persistencia.SQLiteManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Tarjeta;
import java.sql.SQLException;


/**
 *
 * @author marco
 */
public class TarjetaDAO {
    
    private SQLiteManager conn = new SQLiteManager();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
//    
 //public boolean verificarTarjeta(Tarjeta tarjeta){
  //      boolean coincide = false;
    //    con = conn.getConexion();
      //  ResultSet rs;
        // String sql = "SELECT * FROM tarjeta WHERE  numero  = ?";
        
   // }
    public boolean guardarTarjeta(Tarjeta tarjeta){
        boolean realizado=false;
        con= conn.getConexion();
        ResultSet rs;
        String sql= "INSERT into tarjeta (nombre, emisor, numero, cod_seguridad) VALUES (?,?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,tarjeta.getNombre());
            ps.setString(2,tarjeta.getEmisor());
            ps.setString(3,tarjeta.getNumero());
            ps.setString(4,tarjeta.getCodSeguridad());
            realizado=(ps.executeUpdate()>0);
        }catch(SQLException e){
            System.out.println("hola"+e.toString());
        } finally {
            try {
                conn.cerrarConexion();
            }catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return realizado;
    
    }
    
    
    
    
     
}
