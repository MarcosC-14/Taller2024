/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo7.taller.poo.modelo;

/**
 *Esta clase representa a una persona
 * 
 * @author nombre de todos
 */
 abstract public class Persona {
    //atributo que representa el nombre y apellido de una persona
    private String nombre;
    //atributo que representa el correo electronico de una persona
    private String correo;
    //atributo que representa la contraseña de una persona
    private String contrasenia;

    /**
     *Constructor por defecto de la clase Persona
     */
    public Persona() {
        nombre= "NN";
        correo= "correo@correo";
        contrasenia="contrasenia";
    }
    
    /**
     *Constructor parametrizado de la clase Persona 
     *@param nombre indica el nombre y apellido de la persona
     *@param correo indica el correo electronico de la persona
     *@param contrasenia indica la contrasenia que tiene la persona
     * El constructor solo permite correos electronicos que tengan @
     */
    public Persona(String nombre, String correo, String contrasenia) {
        this.nombre = nombre;
        this.setCorreo(correo);
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }
    /**
     *Setter del atributo correo electronico
     *Solo ingresa el valor del correo electronico controlando que tenga un @
     */
    public void setCorreo(String correo) {
        if((correo.indexOf('@'))!=-1){
            this.correo = correo;
        }
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", correo=" + correo + ", contrasenia=" + contrasenia + '}';
    }
    
}
