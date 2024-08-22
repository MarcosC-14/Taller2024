/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo7.taller.poo.modelo;

/**
 *
 * @author marco
 */
class Persona {
    private String nombre, correo, contrasenia;
    
    
    /**
    * Constructor predeterminado de Persona
    */
    public Persona() {
    }
    
    /**
    * Constructor parametrizado de Cliente
    * @param n Es el nombre del cliente
    * @param c Es el correo del cliente
    * @param cn Es la contrasenia del cliente
    * @param tel Es el telefono del cliente
    */
    public Persona(String nombre, String correo, String contrasenia) {
        this.nombre = nombre;
        this.correo = correo;
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

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    
}
