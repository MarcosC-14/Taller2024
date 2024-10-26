/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Esta clase abstracta representa a una persona. Sirve para dar base a las
 * clases Empleado y cliente
 *
 * @author Ian Caraballo,Valentin Rebechi
 * @version 20/10/2027
 */
abstract public class Persona {

    //atributo que representa el nombre y apellido de una persona
    private String nombre;
    //atributo que representa el correo electronico de una persona
    private String correo;
    //atributo que representa la contraseña de una persona
    private String contrasenia;

    /**
     * Constructor por defecto de la clase Persona
     */
    public Persona() {
        nombre = "NN";
        correo = "correo@correo";
        contrasenia = "contrasenia";
    }

    /**
     * Constructor parametrizado de la clase Persona
     *
     * @param nombre indica el nombre y apellido de la persona
     * @param correo indica el correo electronico de la persona
     * @param contrasenia indica la contrasenia que tiene la persona El
     * constructor solo permite correos electronicos que tengan @
     */
    public Persona(String nombre, String correo, String contrasenia) {
        this.nombre = nombre;
        this.setCorreo(correo);
        this.contrasenia = contrasenia;
    }

    /**
     *Metodo getter de nombre
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *Metodo Setter de persona
     * @param nombre de la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo Getter de correo
     * @return correo de persona
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Metodo Setter de correo
     *
     * @param correo de la persona
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     *Metodo Getter de contrasenia
     * @return contrasenia de persona
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Metodo Setter de contrasenia
     *
     * @param contrasenia de la persona
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Metodo toString que retorna los datos de la persona
     *
     * @return cadena con los datos de la persona
     */
    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", correo=" + correo + ", contrasenia=" + contrasenia + '}';
    }

}
