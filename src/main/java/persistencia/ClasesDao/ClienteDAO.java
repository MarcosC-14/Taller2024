/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.ClasesDao;
import persistencia.SQLiteManager;
import Controladores.ClienteController;
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
     public String recuperarContraseña(String correo) { 
         String contraseña="";
         String query = "SELECT *FROM cliente WHERE correo = ?";
         try {
            con = conn.getConexion();
            ps = con.prepareStatement(query);
            //Enviar parametros
            ps.setString(1, correo);
            rs = ps.executeQuery();

            if (rs.next()) {
                contrasenia = rs.getString("contrasenia");
            } else{
                contrasenia= "Correo no encontrado";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener Cliente" + e);
        
    }
         return contrasenia;
    }
     public boolean actualizarInformacion(String correo, String telefono, String correoActual){
         
         boolean actualizacion= false;
         String verificaCorreo ="SELECT *  FROM cliente WHERE correo= ?";
         String sql="UPDATE cliente SET correo = ?, telefono = ? WHERE correo = ?";
         try{
             
             con = conn.getConexion(); //asegura que estas conectado
             ps=con.prepareStatement(verificaCorreo);
             ps.setString(1, correoActual);
             rs=ps.executeQuery();
             if(rs.next()){
                 
                 if(ClienteController.esCorreoElectronicoValido(correo)){
                     
                      ps=con.prepareStatement(verificaCorreo);
                      ps.setString(1,correo);
                      rs=ps.executeQuery();
                      if(!(rs.next())){
                          ps=con.prepareStatement(sql);
                        ps.setString(1, correo);
                        ps.setString(2, telefono);
                        ps.setString(3, correoActual);
                        actualizacion =(ps.executeUpdate()>0);
                 }else{
                     JOptionPane.showMessageDialog(null,"El nuevo correo coincide con un correo registrado", "Error", JOptionPane.ERROR_MESSAGE);
                 }}else{
                     JOptionPane.showMessageDialog(null,"INGRESE UN CORREO ELECTRONICO VALIDO","Error", JOptionPane.ERROR_MESSAGE);
                 }
             }else{
                 JOptionPane.showMessageDialog(null,"El correo no se encuentra registrado", "Error", JOptionPane.ERROR_MESSAGE);
             }
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null,"Error al actualizar la informacion: "+e);
             System.out.println(e);
         }finally {
             try {
                conn.cerrarConexion();
            }catch (Exception e) {
                System.out.println(e.toString());
            }
         }
         return actualizacion;
     
    }
}
