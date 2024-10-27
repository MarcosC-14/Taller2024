package modelo;

import java.util.ArrayList;

/**
 * La clase empleado representa a un empleado tiene sus constructores, ademas de
 * metodos para obtener y guardar los datos de los atributos.
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle, Valentin Rebechi, Ian
 * Caraballo
 * @version 27/10/2024.
 */
public class Empleado extends Persona {

    private Rol rol;
    private int id;
    private ArrayList<Reserva> reservas;

    /**
     * Permite obtener las reservas asociadas a un empleado.
     *
     * @return un ArrayList de Reservas.
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Permite guardas una lista de reservas .
     *
     * @param reservas representa las reservas que atiende un empleado.
     */
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * Metodo Getter de id
     *
     * @return id del empleado
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo setter de id
     *
     * @param id codigo del empleado
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Constructor por defecto de Empleado
     */
    public Empleado() {
        super();
        rol = null;
        this.reservas = new ArrayList<Reserva>();
    }

    /**
     * Constructor parametrizado de empleado.
     *
     * @param nombre nombre del empleado.
     * @param correo correo del empleado.
     * @param contrasenia contraseña del empleado.
     * @param rol rol del empleado.
     * @param reservas representa las reservas asociadas a un empleado.
     */
    public Empleado(String nombre,
            String correo,
            String contrasenia,
            Rol rol,
            ArrayList<Reserva> reservas) {
        super(nombre,
                correo,
                contrasenia);
        this.rol = rol;
        this.reservas = reservas;
    }

    /**
     * Constructor Parametrizado de empleado.
     *
     * @param nombre representa el nombre del empleado.
     * @param correo representa el correo electronico del empleado.
     * @param contrasenia representa la contrasenia del empleado.
     * @param rol representa el rol que tiene el empleado.
     * @param reserva representa la reserva que atiende el empleado.
     */
    public Empleado(String nombre,
            String correo,
            String contrasenia,
            Rol rol,
            Reserva reserva) {
        super(nombre,
                correo,
                contrasenia);
        this.rol = rol;
        this.reservas.add(reserva);
    }

    /**
     * Metodo Getter de rol
     *
     * @return rol del empleado
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Metoddo Setter de rol
     *
     * @param rol es el rol del empleado
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Metodo toString que devuelve los datos del empleado
     *
     * @return cadena con los datos del empleado
     */
    @Override
    public String toString() {

        return "Empleado{nombre=" + super.getNombre() + ", correo=" + super.
                getCorreo() + ", contrasenia=" + super.getContrasenia() +
                " rol=" + rol + '}';
    }

}
