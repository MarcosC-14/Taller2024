/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.ClasesDao;

import modelo.Cliente;
import persistencia.SQLiteManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class ClienteDAO{
    
    private SQLiteManager conn = new SQLiteManager();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    // variables para enviar datos entre interfaces
    
    public static int id_user = 0;
    public static String nombre = "";
    public static String correo = "";
    public static String contrasenia = "";
    public static String telefono = "";
    
    public boolean registrarCliente(Cliente cliente){
        boolean registrado = false;
        
        String sql = "INSERT into cliente (nombre,correo,contrasenia,telefono) VALUES(?,?,?,?)";
        try {
            con = conn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCorreo());
            ps.setString(3, cliente.getContrasenia());
            ps.setString(4, cliente.getTelefono());
            registrado = (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return registrado;
        
    }


    public Cliente loginQuery(String user, String password) {
        String query = "SELECT *FROM cliente WHERE correo = ? AND contrasenia = ?";
        Cliente cliente = new Cliente();
        try {
            con = conn.getConexion();
            ps = con.prepareStatement(query);
            //Enviar parametros
            ps.setString(1, user);
            ps.setString(2, password);
            rs = ps.executeQuery();

            
            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                nombre = cliente.getNombre();
                
                cliente.setCorreo(rs.getString("correo"));
                correo = cliente.getCorreo();
                
                cliente.setContrasenia(rs.getString("contrasenia"));
                contrasenia = cliente.getContrasenia();
                
                cliente.setTelefono(rs.getString("telefono"));
                telefono = cliente.getTelefono();

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener Empleado" + e);
        }
        return cliente;
    }
    }
