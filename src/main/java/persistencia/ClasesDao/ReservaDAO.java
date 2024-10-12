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
import java.time.format.DateTimeFormatter;

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
         String sql = "INSERT into reserva (fecha,comentario,num_mesa,id_cliente,id_tarjeta) VALUES(?,?,?,?)";
         try {
            con = conn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, reserva.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ps.setString(2, reserva.getComentario());
            ps.setInt(3, reserva.getMesa().getNumero());
            ps.setString(4,reserva.getCliente().getCorreo());
            ps.setString(5, reserva.getTarjeta().getNumero());
            realizado = (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return realizado;
    }
}
