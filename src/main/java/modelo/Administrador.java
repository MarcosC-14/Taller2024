/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author Valentin
 */
public class Administrador extends Empleado {
    private ArrayList<Empleado> gestiona;
    private AgendaRestaurante administra;
    
    public Administrador(Empleado e){
        super();
        this.setNombre(e.getNombre());
        this.setCorreo(e.getCorreo());
        this.setContrasenia(e.getContrasenia());
        this.setId(e.getId());
    }
    public void CrearCuentaEmpleado(Empleado e){    
    }
    public void DenifirHoraAperturayCierre(Date a, Date c){
        
    }
    public void AgregarFechaEspecial(Date a){
        
    }
    public void EliminarFechaEspecial(Date a){

        }
    public void BloquearMesa(int numeroMesa){

    }
}

