/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 * Clase cliente que hereda de Persona, contiene sus constructores por defecto y
 * parametrizados, ademas de los metodos necesarios para obtener y guardar los datos
 * de los atributos.
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle, Ian Franco Caraballo, Valentin Rebechi.
 * @version 27/10/2024
 */
public class Cliente extends Persona {

    private int id;
    private String telefono;
    private ArrayList<Reserva> reservas;

    /**
     * Constructor por defecto de Cliente
     */
    public Cliente() {
        super();
        this.telefono = "";
        this.reservas = new ArrayList<Reserva>();
    }

    /**
     * Devuelve el id del cliente.
     *
     * @return  numero de identificacion del cliente
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo Setter del Id
     *
     * @param   id es el numero de identificacion del cliente
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
     * @return  devuelve el telefono almacenado
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método setter del atributo telefono
     *
     * @param   telefono representa un numero de telefono
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
     * Método para actualizar los atributos del cliente
     *
     * @param   correo representa el correo de un cliente.
     * @param   telefono representa el telefono de un cliente.
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
