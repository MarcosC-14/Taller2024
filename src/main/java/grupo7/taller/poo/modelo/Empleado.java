
package grupo7.taller.poo.modelo;


public class Empleado extends Persona {
    private Rol rol;
    //Falta relacion con Cliente, Mesa y Administrador

    public Empleado() {
        super();
        rol= Rol.Cocinero;
    }

    public Empleado (String nombre, String correo, String contrasenia, Rol rol) {
        super(nombre, correo, contrasenia);
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        
        return  "Empleado{nombre=" + getNombre() + ", correo=" + getCorreo() + ", contrasenia=" + getContrasenia() + " rol=" + rol + '}';
    }
    


}
