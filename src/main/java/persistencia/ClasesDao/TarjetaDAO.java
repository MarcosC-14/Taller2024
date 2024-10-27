/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.ClasesDao;

import persistencia.SQLiteManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Tarjeta;
import java.sql.SQLException;

/**
 * Esta clase se encarga de gestionar la conexión con la base de datos de las
 * tarjetas. Tiene una funcion que se encarga de guardar las tarjetas en la base
 * de datos, una funcion que revisa si una tarjeta se encuentra en la base de
 * datos por su numero de tarjeta
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class TarjetaDAO {

    private SQLiteManager conn = new SQLiteManager();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Se encarga de guardar los datos de las tarjetas de crédito en la base de
     * datos.
     *
     * @param tarjeta es un objeto de tipo Tarjeta que representa la tarjeta del
     * cliente.
     * @return true en caso de que se haya realizado el guardado con éxito,
     * false en caso contrario.
     */
    public boolean guardarTarjeta(Tarjeta tarjeta) {
        boolean realizado = false;
        con = conn.getConexion();
        String sql = "INSERT into tarjeta "
                + " (nombre, emisor, numero, cod_seguridad) "
                + " VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,
                    tarjeta.getNombre());
            ps.setString(2,
                    tarjeta.getEmisor());
            ps.setString(3,
                    tarjeta.getNumero());
            ps.setString(4,
                    tarjeta.getCodSeguridad());
            realizado = (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println("hola" + e.toString());
        } finally {
            conn.cerrarConexion();
        }
        return realizado;

    }

    /**
     * Se encarga de revisar si un numero de tarjeta esta en la base de datos.
     *
     * @return true en caso de que encuentre el numero de la tarjeta, false si
     * no lo encontro.
     */
    public boolean existeTarjeta(String numero) {
        boolean bandera = false;
        String buscarNumTarjeta = "SELECT * FROM tarjeta WHERE numero = ?";
        con = conn.getConexion();
        if (!convertToLong(numero)) {
            return false;
        }
        long numeroTarjeta1 = Long.parseLong(numero);
        try {
            ps = con.prepareStatement(buscarNumTarjeta);

            ps.setLong(1,
                    numeroTarjeta1);
            rs = ps.executeQuery();
            if (rs.next()) {
                bandera = true;
            }
        } catch (SQLException e) {
            System.out.println("Error buscar tarjeta: " + e.getMessage());
        } finally {
            conn.cerrarConexion();
        }
        return bandera;
    }

    /**
     * Revisa si todos los datos de la tarjeta coinciden con los que estan en la
     * base de datos.
     *
     * @return true en caso de que coincidan, false en caso contrario.
     */
    public boolean tarjetaValida(Tarjeta tarjeta) {
        boolean enBase = true;
        con = conn.getConexion();
        String sql = "SELECT * FROM tarjeta WHERE numero=?";

        long numeroTarjeta = Long.parseLong(tarjeta.getNumero());
        try {
            con = conn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1,
                    numeroTarjeta);
            ResultSet rs = ps.executeQuery();
            if (!(tarjeta.getCodSeguridad().
                    equals(rs.getString("cod_seguridad")))) {
                enBase = false;
            }
            if (!(tarjeta.getNombre().equals(rs.getString("nombre")))) {
                enBase = false;
            }
            if (!(tarjeta.getEmisor().equals(rs.getString("emisor")))) {
                enBase = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conn.cerrarConexion();
        }
        return enBase;

    }

    /**
     * Se encarga de revisar si el String ingresado se puede convertir a long
     *
     * @param n String a convertir.
     * @return true en caso que se pueda convertir, false en caso contrario.
     */
    public static boolean convertToLong(String n) {
        boolean retorno = true;
        long valor;
        try {
            valor = Long.parseLong(n);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return retorno;
    }
}
