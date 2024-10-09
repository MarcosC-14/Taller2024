/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo7.taller.poo.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Uvenk
 */
public class ClienteDao {
    // instanciar coneccion

    ConectorSql cn = new ConectorSql();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

// variables para enviar datos entre interfaces
    public static int id_user = 0;
    public static String nombre = "";
    public static String correo = "";
    public static String contraseña = "";
    public static String telefono = "";
//Metodo de login

    public Cliente loginQuery(String user, String password) {
        String query = "SELECT *FROM cliente WHERE nombre = ? AND contraseña = ?";
        Cliente cliente = new Cliente();
        try {
            conn = cn.getConnection();
            pst = conn.prepareCall(query);
            //Enviar parametros
            pst.setString(1, user);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                nombre = cliente.getNombre();
                
                cliente.setCorreo(rs.getString("correo"));
                correo = cliente.getCorreo();
                
                cliente.setContrasenia(rs.getString("contraseña"));
                contraseña = cliente.getContrasenia();
                
                cliente.setTelefono(rs.getString("telefono"));
                telefono = cliente.getTelefono();

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener Empleado" + e);
        }
        return cliente;
    }
    
}
