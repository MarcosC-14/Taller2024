/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo7.taller.poo.modelo;

/**
 *
 * @author marco
 */
public class Cliente extends Persona {

    // Este atributo representa el número de teléfono del cliente
    private String telefono;

    /**
     * Constructor predeterminado de Cliente
     */
    public Cliente() {
        sape;
    }

    /**
     * Constructor por defecto de Cliente
     *
     * @param n Es el nombre del cliente
     * @param c Es el correo del cliente
     * @param cn Es la contrasenia del cliente
     * @param tel Es el telefono del cliente
     */
    public Cliente(String n, String c, String cn, String tel) {
        super(n, c, cn);
        this.telefono = tel;
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
    
    /**
     * Método para que el usuario inicie sesión en el sistema
     * @param con Representa la contraseña ingresada
     * @param correo Representa el correo ingresado
     */
    public void iniciarSesion(String con, String correo) {

    }
    
    /**
     * Método para actualizar los atributos del cliente
     * @param nom Nombre
     * @param cor Correo
     * @param con Contrasenia
     * @param tel Telefono
     */
    public void actualizarInformacion(String nom, String cor, String con, String tel) {
        this.setNombre(nom);
        this.setCorreo(cor);
        this.setContrasenia(con);
        this.setTelefono(tel);
    }
    
    /**
     * Método para restablecer la contrasenia del usuario
     * @param correo Ingresa el correo por parámetro
     * @return Devuelve la nueva contrasenia
     */
    public String restablecerContrasenia(String correo) {
        String nuevaCon = "";
        return nuevaCon;
    }
}
