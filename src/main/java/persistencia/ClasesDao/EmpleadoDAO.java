
package persistencia.ClasesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.Rol;
import persistencia.SQLiteManager;

/**
 *
 * @author Charly Cimino
 * Aprendé más Java en mi canal: https://www.youtube.com/c/CharlyCimino
 * Encontrá más código en mi repo de GitHub: https://github.com/CharlyCimino
 */
public class EmpleadoDAO {
     private SQLiteManager conn = new SQLiteManager();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Empleado empleado = new Empleado();

/**
 * Se encarga de verificar que el usuario y contraseña ingresados sean los de un 
 * empleado.
 * @param   usuario es el correo electronico del empleado, con el cual puede ingresar
 * al sistema.
 * @param   contraseña es la contraseña que usa el empleado para ingresar.
 * @return  devuelve un objeto de tipo Empleado con la informacion del empleado. 
 * En caso de que no encuentre un empleado en la base de datos con esas credenciales,
 * devuelve un objeto nulo.
 */
    public Empleado loginQuery(String usuario, String contraseña){
        System.out.println("angelina");
        String query = "SELECT * FROM empleado WHERE correo = ? AND contraseña = ?";
        
        try {
            con = conn.getConexion();
            ps = con.prepareStatement(query);
            //Enviar parametros
            ps.setString(1, usuario);
            System.out.println(usuario);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            if (rs.next()) {
                empleado=new Empleado();
                empleado.setNombre(rs.getString("nombre"));
                empleado.setCorreo(rs.getString("correo"));
                empleado.setContrasenia(rs.getString("contraseña"));
                Rol rol =  Rol.valueOf(rs.getString("rol"));
                empleado.setRol(rol);
                empleado.setId(rs.getInt("id"));
                System.out.println("Entre");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener Empleado" + e);
        }finally {
            conn.cerrarConexion();
        }
        return empleado;
}
    
    public ArrayList<Empleado> obtenerEmpleados(){
        Connection con = conn.getConexion();
        ResultSet rs;
        PreparedStatement ps;
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        String sql = "SELECT * FROM empleado";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Empleado e = new Empleado();
                e.setContrasenia(rs.getString("contraseña"));
                e.setCorreo(rs.getString("correo"));
                e.setNombre(rs.getString("nombre"));
                e.setId(rs.getInt("id"));
                e.setRol(Rol.valueOf(rs.getString("rol")));
                empleados.add(e);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            conn.cerrarConexion();
        }
        return empleados;
    }

    public boolean registrarEmpleado(Empleado emp) {
        boolean registrado = false;
        //que revise si el correo ya esta ingresado
        String sql = "INSERT into empleado (nombre,correo,contraseña,rol) VALUES(?,?,?,?)";
        try {
            if(!existeCorreo(empleado.getCorreo())){
            con = conn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getCorreo());
            ps.setString(3, emp.getContrasenia());
            ps.setString(4, emp.getRol().toString());
            registrado = (ps.executeUpdate() > 0);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            conn.cerrarConexion();
        }
        return registrado; 
    }
    
    public boolean existeCorreo(String correoNuevo){
        String buscarCorreo="SELECT * FROM empleado WHERE correo=?";
        boolean bandera= false;
        try{
            con= conn.getConexion();
            ps=con.prepareStatement(buscarCorreo);
            ps.setString(1,correoNuevo);
            rs=ps.executeQuery();
            if(rs.next()){
                bandera=true;
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
             conn.cerrarConexion();
        } 
        return bandera;
     }



}
