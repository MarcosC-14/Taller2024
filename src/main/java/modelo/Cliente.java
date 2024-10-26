/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 * Clase cliente que hereda atributos y metodos de la clase Persona, ademas de
 * aplicar los propios.
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class Cliente extends Persona {

    private int id;
    private String telefono;
    private ArrayList<Reserva> reservas;

    /**
     * Constructor predeterminado de Cliente
     */
    public Cliente() {
        super();
        this.telefono = "";
        this.reservas = new ArrayList<Reserva>();
    }

    /**
     * Constructor por defecto de Cliente
     *
     * @param n Es el nombre del cliente
     * @param c Es el correo del cliente
     * @param cn Es la contrasenia del cliente
     * @param tel Es el telefono del cliente
     */
    public Cliente(int id, String n, String c, String cn, String tel, ArrayList<Reserva> reservas) {
        super(n, c, cn);
        this.id = id;
        this.telefono = tel;
        this.reservas = reservas;
    }

    /**
     * Retorno del id del cliente
     *
     * @return codigo del cliente
     */
    public int getId() {
        return id;
    }

    /**
     * metodo Setter del Id
     *
     * @param id es el codigo de cliente
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Arraylist donde se almacenan las reservas del cliente
     *
     * @return reservas realizadas
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Metodo setter del arraylist reservas
     *
     * @param reservas es el arraylist donde se almacenan las reservas del
     * cliente
     */
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * Método getter del atributo telefono
     *
     * @return Devuelve el telefono almacenado
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método setter del atributo telefono
     *
     * @param telefono Se guardará el String ingresado por parámetro en telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Metodo para agregar una reserva nueva
     *
     * @param reserva es la nueva reserva que se agrega al arraylist
     */
    public void agregarReserva(Reserva reserva) {
        if (reservas.size() == 0) {
            this.reservas = new ArrayList<Reserva>();
        }
        this.reservas.add(reserva);
    }

    /**
     * Método para que el usuario inicie sesión en el sistema
     *
     * @param con Representa la contraseña ingresada
     * @param correo Representa el correo ingresado
     */
    public void iniciarSesion(String con, String correo) {

    }

    /**
     * Método para actualizar los atributos del cliente
     *
     * @param cor Correo
     * @param tel Telefono
     */
    public void actualizarInformacion(String correo, String telefono) {
        this.setCorreo(correo);
        this.setTelefono(telefono);
    }

    /**
     * Método para recuperar la contrasenia del usuario
     *
     * @param correo Ingresa el correo por parámetro
     * @return Devuelve la contrasenia
     */
    public String recuperarContrasenia(String correo) {
        if (this.getCorreo().equalsIgnoreCase(correo)) {
            return this.getContrasenia();
        }
        return "Correo Invalido, intente de nuevo";
    }

    /**
     * Metodo toString que muestra los datos del cliente
     *
     * @return retorna una cadena con los datos del cliente
     */
    public String toString() {
        return "Cliente: (Nombre: " + super.getNombre() + ", Correo: " + super.getCorreo() + ", Contrasenia: " + super.getContrasenia() + ", Telefono: " + telefono + "y datos de Reservas: " + reservas + ")";
    }
}
