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
 * La clase ClienteDAO ofrece los metodos necesarios para realizar las operaciones
 * necesarias con la base de datos que afecten a los clientes como Registrar a un 
 * cliente, permitir que el cliente se registre, ofrecerle un metodo para que recupere
 * su contraseña y permitir que actualice su numero de telefono y su correo electronico.
 * Se utiliza la clase SQLiteManager para concectarse a la base de datos.
 * @author Marcos Ramon Caraballo, Angelina María Vialle,
 * @version 27/10/2024 
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
    
    /**
     * Este metodo se encarga de registrar a un nuevo cliente en la base de datos.
     * @param cliente, representa a un objeto cliente, el cual contiene los datos a registrar
     * @return true si se pudo registrar el cliente, false en caso contrario
     */
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
        }finally {
            conn.cerrarConexion();
        }
        return registrado;
        
    }

    /**
     * Se encarga de revisar que el usuario y la contraseña ingresados sean validos,
     * en caso que sea asi, permite el acceso.
     * @param user, representa el correo electronico del cliente.
     * @param password, representa la contraseña del cliente
     * @return devuelve un objeto cliente con las informacion de cliente en caso 
     * de que el usuario y la contraseña sean validos, si no devuelve un objeto vacio
     */
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
        }finally {
            conn.cerrarConexion();
        }
        return cliente;
    }
    
    /**
     * Recupera la contraseña de un cliente a traves de su correo electronico.
     * @param correo, es el correo electronico del cliente.
     *@return Devuelve la contraseña de un cliente en caso de que el correo electronico
     * sea valido o un mensaje avisando que no encontro el correo.
     */
    
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
        } finally {
            conn.cerrarConexion();
        }
         return contrasenia;
    }
     
     /**
      * Actualiza el correo del cliente en la base de datos.
      * Verifica que el correo electronico actual este en la base de datos y el nuevo correo electronico tenga un @ y no este en la base de datos
      * @param correoActual, representa el correo electronico que se tiene actualmente
      * @param correoNuevo, representa el nuevo correo electronico
      * @return true en caso de se haya podido actualizar correctamente el correo, false en 
      * caso contrario.
      */
     public boolean actualizarCorreo( String correoActual, String correoNuevo){
        boolean actualizacion= false;
         String verificaCorreo ="SELECT *  FROM cliente WHERE correo= ?";
         String sql="UPDATE cliente SET correo = ? WHERE correo = ?";
         try{
             
             con = conn.getConexion(); //asegura que estas conectado
             ps=con.prepareStatement(verificaCorreo);
             ps.setString(1, correoActual);
             rs=ps.executeQuery();
             if(rs.next()){
                 
                 if(ClienteController.esCorreoElectronicoValido(correoNuevo)){
                     
                      ps=con.prepareStatement(verificaCorreo);
                      ps.setString(1,correoNuevo);
                      rs=ps.executeQuery();
                      if(!(rs.next())){
                        ps=con.prepareStatement(sql);
                        ps.setString(1, correoNuevo);
                        ps.setString(2, correoActual);
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
            conn.cerrarConexion();
        }
         return actualizacion;
     
     }
     
     /**
      * Actualiza el numero de telefono del cliente en la base de datos.
      * Verifica que el correo este en la base de datos y que el nuevo telefono no coincida con el numero de telefono de alguien más.
      * @param correo, representa el correo electronico del cliente.
      * @param telefono, representa el nuevo numero de telefono
      * @return true en caso de se haya podido actualizar correctamente el correo, false en 
      * caso contrario.
      */
     public boolean actualizarTelefono(String correo, String telefono){
     boolean actualizacion= false;
         String verificaCorreo ="SELECT *  FROM cliente WHERE correo= ?";
         String verificaTelefono ="SELECT *  FROM cliente WHERE telefono= ?";
         String sql="UPDATE cliente SET telefono = ? WHERE correo = ?";
         try{
             
             con = conn.getConexion(); //asegura que estas conectado
             ps=con.prepareStatement(verificaCorreo);
             ps.setString(1, correo);
             rs=ps.executeQuery();
             
             
             if(rs.next()){
                  ps=con.prepareStatement(verificaTelefono);
                      ps.setString(1,telefono);
                      rs=ps.executeQuery();
                 if(!(rs.next())){
                        ps=con.prepareStatement(sql);
                        ps.setString(1, telefono);
                        ps.setString(2, correo);
                        actualizacion =(ps.executeUpdate()>0);
                 }else{
                     JOptionPane.showMessageDialog(null, "El telefono ya se encuentra registrado", "Error", JOptionPane.ERROR_MESSAGE);
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
