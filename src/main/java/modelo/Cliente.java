/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
/**
 *
 * @author marco
 */
public class Cliente extends Persona {

    // Este atributo representa el número de teléfono del cliente
    private String telefono;
    private ArrayList<Reserva> reservas;

    /**
     * Constructor predeterminado de Cliente
     */
    public Cliente() {
        super();
        this.telefono = "";
        this.reservas=new ArrayList <Reserva>();
    }

    /**
     * Constructor por defecto de Cliente
     *
     * @param n Es el nombre del cliente
     * @param c Es el correo del cliente
     * @param cn Es la contrasenia del cliente
     * @param tel Es el telefono del cliente
     */
    public Cliente(String n, String c, String cn, String tel, ArrayList<Reserva> reservas) {
        super(n, c, cn);
        this.telefono = tel;
        this.reservas=reservas;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    /**
     * Método getter del atributo telefono
     * @return Devuelve el telefono almacenado
     */
    public String getTelefono() {
        return telefono;
    }
    
    /**
     * Método setter del atributo telefono
     * @param telefono Se guardará el String ingresado por parámetro en telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void agregarReserva(Reserva reserva){
        if(reservas.size()==0){
            this.reservas= new ArrayList<Reserva>();
        }
        this.reservas.add(reserva);
    }
    /**
     * Método para que el usuario inicie sesión en el sistema
     * @param con Representa la contraseña ingresada
     * @param correo Representa el correo ingresado
     */
    public void iniciarSesion(String con, String correo) {

    }
    
    /**
     * Método para actualizar los atributos del cliente
     * @param cor Correo
     * @param tel Telefono
     */
    public void actualizarInformacion(String cor, String tel) {
        this.setCorreo(cor);
        this.setTelefono(tel);
    }
    
    /**
     * Método para recuperar la contrasenia del usuario
     * @param correo Ingresa el correo por parámetro
     * @return Devuelve la contrasenia 
     */
    public String restablecerContrasenia(String correo) { 
        if(this.getContrasenia().equalsIgnoreCase(correo)){
            return this.getContrasenia();
        }
        return "Correo Invalido, intente de nuevo";
    }
    public String toString(){
        return "Cliente: (Nombre: "+super.getNombre()+", Correo: "+super.getCorreo()+", Contrasenia: "+super.getContrasenia()+", Telefono: "+telefono+"y datos de Reservas: "+reservas+")";
    }
}
