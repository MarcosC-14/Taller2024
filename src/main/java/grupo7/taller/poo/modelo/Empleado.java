
package grupo7.taller.poo.modelo;
import java.util.ArrayList;
//relaciones listas
public class Empleado extends Persona {
    private Rol rol;
    private ArrayList<Cliente> recibe;
    private ArrayList<Mesa> atiende;
    private ArrayList<Administrador> administrador;
    

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
