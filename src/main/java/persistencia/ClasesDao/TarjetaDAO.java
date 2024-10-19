/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.ClasesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Tarjeta;
import persistencia.SQLiteManager;

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
//    public boolean verificarTarjeta(Tarjeta tarjeta){
//        boolean coincide = false;
//        con = conn.getConexion();
//        ResultSet rs;
//        String sql = "SELECT * FROM tarjeta WHERE num_mesa = ? AND fecha = ? AND hora = ?";
//        
//    }
}
