
package modelo;
import java.util.ArrayList;
//tiene que toString traer datos de clientes y mesas?
public class Empleado extends Persona {
    private Rol rol;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private ArrayList<Cliente> clientes;
    private ArrayList<Mesa> mesas;
   
    public Empleado() {
        super();
        rol= Rol.Cocinero;
        this.clientes= new ArrayList<Cliente>();
        this.mesas= new ArrayList<Mesa>();
    }

    public Empleado (String nombre, String correo, String contrasenia, Rol rol, ArrayList<Cliente> clientes,ArrayList<Mesa> mesas) {
        super(nombre, correo, contrasenia);
        this.rol = rol;
        this.clientes=clientes;
        this.mesas=mesas;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(ArrayList<Mesa> mesas) {
        this.mesas = mesas;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void agregarCliente(Cliente cliente){
        if(clientes.size()==0){
            this.clientes= new ArrayList<Cliente>();
        }
        clientes.add(cliente);
    }
    public void agregarMesa(Mesa mesa){
        if(mesas.size()==0){
            this.mesas= new ArrayList<Mesa>();
        }
        mesas.add(mesa);
    }
            
    @Override
    public String toString() {
        
        return  "Empleado{nombre=" +super.getNombre()+ ", correo=" + super.getCorreo() + ", contrasenia=" + super.getContrasenia() + " rol=" + rol + '}';
    }
    


}
