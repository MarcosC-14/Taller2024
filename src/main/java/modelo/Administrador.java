/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;
import java.util.ArrayList;

/**
 * Clase Administrador es un persona tipo empleado. Tiene un listado de todos
 * los empleados(mozos,recepcionistas,administradores) y la agenda del
 * restaurante Tambien se encuentra los metodos que este realiza, aunque estan
 * definidos en clases terminadas en Dao, ya que estos metodos tienen relacion
 * directa con la base de datos.
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle, Valentin Rebechi, Ian
 * Caraballo
 * @version 27/10/2024
 */
public class Administrador extends Empleado {

    private ArrayList<Empleado> gestiona;
    private AgendaRestaurante administra;
    private ArrayList<Cliente> clientes;

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

}
