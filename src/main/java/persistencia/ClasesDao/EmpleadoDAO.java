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
import modelo.AgendaRestaurante;
import modelo.BloqueoMesaEventoEspecial;

/**
 * Esta clase se encarga de relacionarse con la base de datos de empleado. Tiene
 * metodos que permiten: -Ingresar un empleado con sus correspondientes
 * credenciales(usuario y contraseña). -Devolver un lista de todos los empleados
 * en la base de datos. -Registrar a un empleado. -Revisa si un correo existe en
 * la tabla de empleados. -Revisar si el empleado es mesero o recepcionista.
 * -Guardar en la base de datos una mesa bloqueada o evento especial -Obtener
 * una lista de los bloqueos de mesa y los eventos especiales. -Eliminar un
 * bloqueo de mesa o evento especial -Guardar la hora de apertura y cierre
 * definida por un administrador. -Obtiener un objeto AgendaRestaurante con la
 * hora de apertura y cierre. -Revisa si el bloqueo de mesa o evento especial ya
 * se encuentran en la base de datos.
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo.
 * @version 27/10/2024.
 */
public class EmpleadoDAO {

    private SQLiteManager conn = new SQLiteManager();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Empleado empleado = new Empleado();

    /**
     * Se encarga de verificar que el usuario y contraseña ingresados sean los
     * de un empleado.
     *
     * @param usuario es el correo electronico del empleado, con el cual puede
     * ingresar al sistema.
     * @param contraseña es la contraseña que usa el empleado para ingresar.
     * @return devuelve un objeto de tipo Empleado con la informacion del
     * empleado. En caso de que no encuentre un empleado en la base de datos con
     * esas credenciales, devuelve un objeto nulo.
     */
    public Empleado loginQuery(String usuario,
            String contraseña) {
        String query = "SELECT * FROM empleado WHERE"
                + " correo = ? AND contraseña = ?";
        Connection con = conn.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,
                    usuario);
            ps.setString(2,
                    contraseña);
            rs = ps.executeQuery();
            if (rs.next()) {
                empleado = new Empleado();
                empleado.setNombre(rs.getString("nombre"));
                empleado.setCorreo(rs.getString("correo"));
                empleado.setContrasenia(rs.getString("contraseña"));
                Rol rol = Rol.valueOf(rs.getString("rol"));
                empleado.setRol(rol);
                empleado.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al obtener Empleado" + e);
        } finally {
            conn.cerrarConexion();
        }
        return empleado;
    }

    /**
     * Devuelve una lista de todos los empleados.
     *
     * @return ArrayList de todos los empleados con su contraseña, correo,
     * nombre, id y rol.
     */
    public ArrayList<Empleado> obtenerEmpleados() {
        Connection con = conn.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        String sql = "SELECT * FROM empleado";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setContrasenia(rs.getString("contraseña"));
                e.setCorreo(rs.getString("correo"));
                e.setNombre(rs.getString("nombre"));
                e.setId(rs.getInt("id"));
                e.setRol(Rol.valueOf(rs.getString("rol")));
                empleados.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            conn.cerrarConexion();
        }
        return empleados;
    }

    /**
     * Se encarga de guardar en la base de datos a un nuevo empleado.
     *
     * @param emp representa a un empleado.
     * @return false si no se pudo registrar, true en caso de que si se haya
     * podido guardar en la base de datos.
     */
    public boolean registrarEmpleado(Empleado emp) {
        boolean registrado = false;
        
        String sql = "INSERT into empleado"
                + " (nombre,correo,contraseña,rol) VALUES(?,?,?,?)";
        Connection con = conn.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        try {
            if (!existeCorreo(emp.getCorreo())) {
                ps = con.prepareStatement(sql);
                ps.setString(1,
                        emp.getNombre());
                ps.setString(2,
                        emp.getCorreo());
                ps.setString(3,
                        emp.getContrasenia());
                ps.setString(4,
                        emp.getRol().toString());
                registrado = (ps.executeUpdate() > 0);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            conn.cerrarConexion();
        }
        return registrado;
    }

    /**
     * Se encarga de revisar si el correo ingresado se encuentra en la base de
     * datos. Devuelve true si se encuentra en la base de datos, false si no
     * esta en la base de datos
     *
     * @param correoNuevo representa al correo del empleado a revisar
     * @return true si lo encuentra, false si no
     */
    private boolean existeCorreo(String correoNuevo) {
        String buscarCorreo = "SELECT correo FROM empleado WHERE correo = ? "
                + "UNION SELECT correo FROM cliente WHERE correo = ?";
        boolean bandera = false;
        try {
            ps = con.prepareStatement(buscarCorreo);
            ps.setString(1,
                    correoNuevo);
            ps.setString(2,
                    correoNuevo);
            rs = ps.executeQuery();
            if (rs.next()) {
                bandera = true;
                javax.swing.JOptionPane.showMessageDialog(null,
                        "Correo ya registrado",
                        "Advertencia",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bandera;
    }

    /**
     * Guarda en la base de datos la mesa bloqueada o un evento especial
     * dependiendo los datos ingresados.
     *
     * @param b representa el bloque o evento que se guardará en la base de
     * datos.
     * @return true si se pudo bloquear, false en caso de que no se pueda.
     */
    public boolean bloquearMesaEventoEspecial(BloqueoMesaEventoEspecial b) {
        boolean bloqueo = false;

        String sql = "INSERT into bloqueo_evento"
                + " (mesa,fecha,hora_inicio,hora_fin) VALUES(?,?,?,?)";
        Connection con = conn.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,
                    b.getNumMesa());
            ps.setString(2,
                    b.getFecha().format(DateTimeFormatter.
                            ofPattern("dd/MM/yyyy")));

            if (b.getHoraInicio() != null) {
                ps.setString(3,
                        b.getHoraInicio().format(DateTimeFormatter.ofPattern(
                                "HH:mm:ss")));
            } else {
                ps.setString(3,
                        "");
            }
            if (b.getHoraFin() != null) {
                ps.setString(4,
                        b.getHoraFin().format(DateTimeFormatter.ofPattern(
                                "HH:mm:ss")));
            } else {
                ps.setString(4,
                        "");
            }
            bloqueo = (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            conn.cerrarConexion();
        }
        return bloqueo;
    }

    /**
     * Obtiene el listado de bloqueos y eventos especiales de la base de datos
     *
     * @return ArrayList de todos los bloqueos y eventos
     */
    public ArrayList<BloqueoMesaEventoEspecial> obtenerBloqueosMesasEventosEspeciales() {
        Connection con = conn.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<BloqueoMesaEventoEspecial> bs = new ArrayList<BloqueoMesaEventoEspecial>();
        String sql = "SELECT * FROM bloqueo_evento";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BloqueoMesaEventoEspecial b = new BloqueoMesaEventoEspecial();
                b.setNumMesa(rs.getInt("mesa"));
                b.setFecha(LocalDate.parse(rs.getString("fecha"),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                if (!rs.getString("hora_inicio").equals("")) {
                    b.setHoraInicio(LocalTime.parse(rs.getString("hora_inicio"),
                            DateTimeFormatter.ofPattern("HH:mm:ss")));
                } else {
                    b.setHoraInicio(null);
                }
                if (!rs.getString("hora_fin").equals("")) {
                    b.setHoraFin(LocalTime.parse(rs.getString("hora_fin"),
                            DateTimeFormatter.ofPattern("HH:mm:ss")));
                } else {
                    b.setHoraFin(null);
                }
                b.setId(rs.getInt("id"));
                bs.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            conn.cerrarConexion();
        }
        return bs;
    }

    /**
     * Elimina un bloqueo de mesa o evento especial de la base de datos.
     *
     * @param bme representa el bloqueo o evento a eliminar.
     * @return true si se pudo eliminar, false si no se pudo eliminar.
     */
    public boolean eliminarBloqueoEvento(BloqueoMesaEventoEspecial bme) {
        boolean eliminado = false;
        Connection con = conn.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "DELETE FROM bloqueo_evento WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,
                    bme.getId());
            eliminado = (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            conn.cerrarConexion();
        }
        return eliminado;
    }

    /**
     * Guarda en la base de datos la hora de apertura y la hora de cierre
     * definidas por un administrador.
     *
     * @param horaAp representa la hora de apertura.
     * @param horaCi representa la hora de cierre.
     * @return true si se pudo guardar, false si no se pudo guardar.
     */
    public boolean definirHoraAperturaCierre(LocalTime horaAp,
            LocalTime horaCi) {
        boolean modificado = false;
        Connection con = conn.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "UPDATE agenda_restaurante SET hora_apertura = ?, "
                + " hora_cierre = ? WHERE id = 1";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,
                    horaAp.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            ps.setString(2,
                    horaCi.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            modificado = (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conn.cerrarConexion();
        }
        return modificado;
    }

    /**
     * Obtiene un objeto AgendaRestaurante con la hora de apertura y la hora de
     * cierre como atributos.
     *
     * @return un objeto AgendaRestaurante con los datos guardados en la base de
     * datos.
     */
    public AgendaRestaurante obtenerHoraAperturaCierre() {
        String sql = "SELECT * FROM agenda_restaurante WHERE id = 1";
        AgendaRestaurante aR = new AgendaRestaurante();
        Connection con = conn.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {

                aR.setHoraApertura(LocalTime.
                        parse(rs.getString("hora_apertura"),
                                DateTimeFormatter.ofPattern("HH:mm:ss")));
                aR.setHoraCierre(LocalTime.parse(rs.getString("hora_cierre"),
                        DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conn.cerrarConexion();
        }
        return aR;
    }

    /**
     * Se encarga de revisar si el bloqueo o evento ingresado se encuentra en la
     * base de datos. Devuelve true si se encuentra en la base de datos, false
     * si no esta en la base de datos.
     *
     * @param b representa al bloqueo o evento a revisar.
     * @return true si lo encuentra, false si no.
     */
    public boolean existeBloqueoEvento(BloqueoMesaEventoEspecial b) {
        String sql = "SELECT * FROM bloqueo_evento WHERE mesa =?"
                + "AND fecha = ? "
                + "AND hora_inicio = ?"
                + "AND hora_fin = ?";
        boolean bandera = false;
        Connection con = conn.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,
                    b.getNumMesa());
            ps.setString(2,
                    b.getFecha()
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            if (b.getHoraInicio() == null) {
                ps.setString(3,
                        "");
            } else {
                ps.setString(3,
                        b.getHoraInicio()
                                .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
            if (b.getHoraFin() == null) {
                ps.setString(4,
                        "");
            } else {
                ps.setString(4,
                        b.getHoraFin()
                                .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                bandera = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conn.cerrarConexion();
        }
        return bandera;
    }
}
