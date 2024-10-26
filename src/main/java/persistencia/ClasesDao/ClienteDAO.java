/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.ClasesDao;
import Controladores.ClienteController;
import modelo.Cliente;
import persistencia.SQLiteManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Arrays;
/**
 * La clase ClienteDAO ofrece los metodos necesarios para realizar las operaciones
 * necesarias con la base de datos que afecten a los clientes como registrar a un 
 * cliente, permitir que el cliente se registre, ofrecerle un metodo para que recupere
 * su contraseña, permitir que actualice su numero de telefono y su correo electronico
 * y obtener los datos del cliente.
 * Se utiliza la clase SQLiteManager para concectarse a la base de datos.
 *@author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024 
 */
public class ClienteDAO{
    
    private SQLiteManager conn = new SQLiteManager();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Cliente cliente= new Cliente();
    
    /**
     *Constructor por defecto de la clase ClienteDAO.
     */
    public ClienteDAO(){
    }
    
    /**
     * Este metodo se encarga de registrar a un nuevo cliente en la base de datos.
     * @param   cliente, representa a un objeto cliente, el cual contiene los datos a registrar.
     * @return  es true si se pudo registrar el cliente, false en caso contrario.
     */
    public boolean registrarCliente(Cliente cliente){
        boolean registrado = false;
        String buscarCorreo="SELECT * FROM cliente WHERE correo=?";
        String correo= cliente.getContrasenia();
        //que revise si el correo ya esta ingresado
        String sql = "INSERT into cliente (nombre,correo,contrasenia,telefono) VALUES(?,?,?,?)";
        try {
            System.out.println("Holi");
            con = conn.getConexion();
            ps= con.prepareStatement(buscarCorreo);
            ps.setString(1, correo);
            rs=ps.executeQuery();
             System.out.println("Holi2");
            if(!rs.next()){
                 System.out.println("Holi3");
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCorreo());
            ps.setString(3, cliente.getContrasenia());
            ps.setString(4, cliente.getTelefono());
            registrado = (ps.executeUpdate() > 0);
            }
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
     * @param   user, representa el correo electronico del cliente.
     * @param   password, representa la contraseña del cliente.
     * @return  devuelve un objeto de tipo Cliente con la informacion del cliente 
     * en caso de que de que el usuario y la contraseña sean validos, si no devuelve
     * un objeto vacio.
     */
    public Cliente loginQuery(String user, String password){
        String query = "SELECT *FROM cliente WHERE correo = ? AND contrasenia = ?";
        
        try {
            con = conn.getConexion();
            ps = con.prepareStatement(query);
            //Enviar parametros
            ps.setString(1, user);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setContrasenia(rs.getString("contrasenia"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setId(rs.getInt("id"));
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
     * @param   correo, es el correo electronico del cliente.
     * @return  Devuelve la contraseña de un cliente en caso de que el correo electronico
     * sea valido o un mensaje avisando que no encontro el correo.
     */
     public String recuperarContraseña(String correo){ 
         String contraseña="";
         String recuperaC="SELECT * FROM cliente WHERE correo = ?";
         try {
             con=conn.getConexion();
             ps=con.prepareStatement(recuperaC);
             ps.setString(1,correo);
             rs=ps.executeQuery();
            if (rs.next()) {
                contraseña = rs.getString("contrasenia");
            } else{
                contraseña= "Correo no encontrado";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener Cliente" + e);
        } finally {
            conn.cerrarConexion();
        }
         return contraseña;
    }
     
     /**
      * Actualiza el correo del cliente en la base de datos.
      * Verifica que el correo electronico actual este en la base de datos y el 
      * nuevo correo electronico tenga un @ y no este en la base de datos.
      * @param  correoNuevo representa el nuevo correo electronico.
      * @param  id representa el numero de identificacion del cliente.
      * @return true en caso de se haya podido actualizar correctamente el correo,
      * false en caso contrario.
      */
     public boolean actualizarCorreo (int id, String correoNuevo){
        boolean actualizacion= false;
         String buscaId ="SELECT * FROM cliente WHERE id = ?";
         String sql="UPDATE cliente SET correo = ? WHERE id = ?";
         String correoBuscar="SELECT * FROM cliente WHERE correo= ?";
         try{
             con = conn.getConexion(); //asegura que estas conectado
             ps=con.prepareStatement(buscaId);
             ps.setInt(1, id);
             rs=ps.executeQuery();
             if(rs.next()){    
                 if(ClienteController.esCorreoElectronicoValido(correoNuevo)){
                     System.out.println("Hola2");
                        ps=con.prepareStatement(correoBuscar);
                        ps.setString(1,correoNuevo);
                        rs=ps.executeQuery();
                        if(!rs.next()){
                            ps=con.prepareStatement(sql);
                        ps.setString(1, correoNuevo);
                        ps.setInt(2, id);
                        actualizacion =(ps.executeUpdate()>0);
                        if(actualizacion){
                            cliente.setCorreo(correoNuevo);
                        }
                        }
                        }else{
                     JOptionPane.showMessageDialog(null,"INGRESE UN CORREO ELECTRONICO VALIDO","Error", JOptionPane.ERROR_MESSAGE);
                 }}
             
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null,"Error al actualizar la informacion: "+e);
             System.out.println(e.getMessage());
         }finally {
            conn.cerrarConexion();
        }
         return actualizacion;
     }
     
     
     /**
      * Actualiza el numero de telefono del cliente en la base de datos.
      * Verifica que el correo este en la base de datos y que el nuevo telefono 
      * no coincida con el numero de telefono de alguien más.
      * @param  id representa el numero que identifica a un cliente.
      * @param  telefono representa el nuevo número de telefono.
      * @return true en caso de se haya podido actualizar correctamente el correo, false en 
      * caso contrario.
      */
     public boolean actualizarTelefono(int id, String telefono){
     boolean actualizacion= false;
         String verificaId ="SELECT *  FROM cliente WHERE id= ?";
         String verificaTelefono ="SELECT *  FROM cliente WHERE telefono= ?";
         String sql="UPDATE cliente SET telefono = ? WHERE id = ?";
         try{
             con = conn.getConexion(); //asegura que estas conectado
             ps=con.prepareStatement(verificaId);
             ps.setInt(1, id);
             rs=ps.executeQuery();
             if(rs.next()){
                  ps=con.prepareStatement(verificaTelefono);
                      ps.setString(1,telefono);
                      rs=ps.executeQuery();
                 if(!(rs.next())){
                        ps=con.prepareStatement(sql);
                        ps.setString(1, telefono);
                        ps.setInt(2, id);
                        actualizacion =(ps.executeUpdate()>0);
                        if (actualizacion) {
                            cliente.setTelefono(telefono);
                        }
                 }else{
                     JOptionPane.showMessageDialog(null, "El telefono ya se encuentra registrado", "Error", JOptionPane.ERROR_MESSAGE);
                 }
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
    * Este método se encarga de buscar en la base de datos el nombre, correo y teléfono
    * de un cliente a través de su id, devolviendo un arreglo de String con esos valores.
    * @param    id es el id que corresponde al cliente.
    * @return   un arreglo de String con la siguiente información:
    *           datosCliente[0]: nombre del cliente.
    *           datosCliente[1]: correo electronico del cliente.
    *           datosCliente[2]: teléfono del cliente.
    */
     public String[] mostrarDatos(int id){
         String datosCliente[]=new String[3];
         String sql = "SELECT nombre, correo, telefono FROM cliente WHERE id=?";
         try{
             con= conn.getConexion();
             ps=con.prepareStatement(sql);
             ps.setInt(1,id);
             ResultSet rs = ps.executeQuery();
             datosCliente[0]=rs.getString("nombre");
             datosCliente[1]=rs.getString("correo");
             datosCliente[2]=rs.getString("telefono");            
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null,"Error al buscar la informacion: "+e);
             System.out.println(e);
         }finally {
             conn.cerrarConexion();
         }
         return datosCliente;
     }
     /**
      * Se encarga de obtener una lista de todos los clientes en la base de datos.
      * @return un ArrayList de todos los clientes.
      */
    public ArrayList<Cliente> obtenerClientes() {
        Connection con = conn.getConexion();
        ResultSet rs;
        PreparedStatement ps;
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        String sql = "SELECT * FROM cliente";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setContrasenia(rs.getString("contrasenia"));
                c.setCorreo(rs.getString("correo"));
                c.setNombre(rs.getString("nombre"));
                c.setId(rs.getInt("id"));
                c.setTelefono(rs.getString("telefono"));
                clientes.add(c);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            conn.cerrarConexion();
        }
        return clientes;
    }
    /**
     * Retorna un cliente de la base de datos con un correo
     * @param   correo del cliente.
     * @return un objeto cliente en caso de que este en la base de datos.
     */
    public Cliente obtenerCliente(String correo){
        Cliente cliente = new Cliente();
        Connection con = conn.getConexion();
        ResultSet rs;
        PreparedStatement ps;
        String sql="SELECT * FROM cliente WHERE correo=?";
        try{
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1,correo);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
               
                cliente.setContrasenia(rs.getString("contrasenia"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setId(rs.getInt("id"));
                cliente.setTelefono(rs.getString("telefono"));
               
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            conn.cerrarConexion();
        }
        
        return cliente;
    }
}
