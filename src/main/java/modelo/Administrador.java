/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;
import java.util.ArrayList;

/**
 * Clase Administrador es un usuario tipo empleado, hereda los atributos de
 * empleado ya que el administrador es un tipo particular de empleado . Tiene un
 * listado de todos los empleados(mozos,recepcionistas,administradores) y la
 * agenda del restaurante Tambien se encuentra los metodos que este realiza,
 * aunque estan definidos en clases terminadas en Dao, ya que estos metodos
 * tienen relacion directa con la base de datos.
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class Administrador extends Empleado {

    private ArrayList<Empleado> gestiona;
    private AgendaRestaurante administra;

    /**
     * Constructor parametrizado de la clase Administrador que recibe los datos
     * de empleado
     *
     * @param e Es el objeto de tipo Empleado pasado por parametro
     */
    public Administrador(Empleado e) {
        super();
        this.setNombre(e.getNombre());
        this.setCorreo(e.getCorreo());
        this.setContrasenia(e.getContrasenia());
        this.setId(e.getId());
    }

    public void CrearCuentaEmpleado(Empleado e) {
    }

    public void DenifirHoraAperturayCierre(Date a, Date c) {

    }

    public void AgregarFechaEspecial(Date a) {

    }

    public void EliminarFechaEspecial(Date a) {

    }

    public void BloquearMesa(int numeroMesa) {

    }
}
