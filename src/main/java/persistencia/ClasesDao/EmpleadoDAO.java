
package persistencia.ClasesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}}
