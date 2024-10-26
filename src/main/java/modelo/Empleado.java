package modelo;

import java.util.ArrayList;

/**
 * La clase empleado crea la estructura necesaria para aplicar luego herencia
 * con los tipos de empleados existentes.Hereda atributos y metodos de Persona.
 * Se definen los atributos de Rol y de ID con sus respectivos Getter y Setter.
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class Empleado extends Persona {

    private Rol rol;
    private int id;
/**
 * Metodo Getter de id
 * @return id del empleado
 */
    
    public int getId() {
        return id;
    }
/**
 * Metodo setter de id
 * @param id codigo del empleado
 */
    public void setId(int id) {
        this.id = id;
    }
    private ArrayList<Cliente> clientes;
    private ArrayList<Mesa> mesas;
/**
 * Constructor por defecto de Empleado
 */
    public Empleado() {
        super();
        rol = Rol.Cocinero;
        this.clientes = new ArrayList<Cliente>();
        this.mesas = new ArrayList<Mesa>();
    }
/**
 * Constructor parametrizado de empleado
 * @param nombre nombre del empleado
 * @param correo correo del empleado
 * @param contrasenia contrasela del empleado
 * @param rol rol del empleado
 * @param clientes clientes que atiende el empleado
 * @param mesas atendidas por el empleado
 */
    public Empleado(String nombre, String correo, String contrasenia, Rol rol, ArrayList<Cliente> clientes, ArrayList<Mesa> mesas) {
        super(nombre, correo, contrasenia);
        this.rol = rol;
        this.clientes = clientes;
        this.mesas = mesas;
    }
/**
 * Metodo Getter de clientes
 * @return listado de clientes que atiende el empleado
 */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
/**
 * Metodo Setter de clientes
 * @param clientes listado de clientes que atiende el empleado
 */
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
/**
 * Metodo Getter de mesas
 * @return listado de mesas que atiende el empleado
 */
    public ArrayList<Mesa> getMesas() {
        return mesas;
    }
/**
 * Metodo Setter de mesas
 * @param mesas listado de mesas que atiende el empleado
 */
    public void setMesas(ArrayList<Mesa> mesas) {
        this.mesas = mesas;
    }
/**
 * Metodo Getter de rol
 * @return rol del empleado
 */
    public Rol getRol() {
        return rol;
    }
/**
 * Metoddo Setter de rol
 * @param rol es el rol del empleado
 */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
/**
 * 
 * @param cliente cliente que se agrega al listado de clientes atendidos por el empleado
 */
    public void agregarCliente(Cliente cliente) {
        if (clientes.size() == 0) {
            this.clientes = new ArrayList<Cliente>();
        }
        clientes.add(cliente);
    }
/**
 * 
 * @param mesa objeto mesa que se agrega al listado de mesas atendidas por empleado 
 */
    public void agregarMesa(Mesa mesa) {
        if (mesas.size() == 0) {
            this.mesas = new ArrayList<Mesa>();
        }
        mesas.add(mesa);
    }

    /**
     * Metodo toString que devuelve los datos del empleado
     *
     * @return cadena con los datos del empleado
     */
    @Override
    public String toString() {

        return "Empleado{nombre=" + super.getNombre() + ", correo=" + super.getCorreo() + ", contrasenia=" + super.getContrasenia() + " rol=" + rol + '}';
    }

}
