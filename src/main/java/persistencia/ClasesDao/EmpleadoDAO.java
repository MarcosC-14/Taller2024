
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase se encarga de relacionarse con la base de datos de empleado, tiene 
 * metodos que permitenel ingreso de un empleado con sus correspondientes credenciales
 * (usuario y contraseña), de vuelve un lista de todos los empleados en la base 
 * de datos, permite registrar a un empleado, revisa si un correo existe en la tabla
 * de empleados, ademas de revisar si el empleado es mesero o recepcionista.
 * @author Marcos Ramon Caraballo, Angelina María Vialle,
 * @version 27/10/2024
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
    /**
     *Devuelve una lista de todos los empleados
     * @return  ArrayList de todos los empleados con su contraseña, correo, nombre,
     * id y rol.
     */
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
    /**
     * Se encarga de guardar en la base de datos a un nuevo empleado.
     * @param   emp representa a un empleado.
     * @return  false si no se pudo registrar, true en caso de que si se haya podido
     * guardar en la base de datos
     */
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
    /**
     * Se encarga de revisar si el correo ingresado se encuentra en la base de datos.
     * Devuelve true si se encuentra en la base de datos, false si no esta en la base de datos
     * @param   correoNuevo representa al correo del empleado a revisar
     * @return  true si lo encuentra, false si no
     */
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

/**
 * Revisa si es un mesero, aun no terminado.
 * @return  true en caso que lo encuentre, false si no
 */
    public boolean esMesero(){
        boolean bandera=false;
        return bandera;
    }
 
/**
 * Revisa si es un recepcionista. Aun no terminado.
 * @return  true en caso que lo encuentre, false si no
 */
    public boolean esRecepcionista(){
        boolean bandera=false;
        return bandera;
    }

    
    /**
     *guardar en la base de datos las mesas bloqueadas o en evento especial
     * @param   numMesa representa el numero de mesa. 
     * @param   fecha representa la fecha en la que se bloque.   
     * @param   horaInicio representa la hora de inicio del bloqueo o evento especial.
     * @return  horaFin representa la hora en la que finaliza el bloque o evento 
     * especial.
     */
    public boolean bloquearMesaEventoEspecial(int numMesa, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        boolean bloqueo = false;
        
        String sql = "INSERT into bloqueo_evento (mesa,fecha,hora_inicio,hora_fin) VALUES(?,?,?,?)";
        try {
            
            con = conn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, numMesa);
            ps.setString(2, fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ps.setString(3, horaInicio.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            ps.setString(4, horaInicio.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            bloqueo = (ps.executeUpdate() > 0);
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            conn.cerrarConexion();
        }
        return bloqueo; 
    }
}
