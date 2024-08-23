/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo7.taller.poo.modelo;
import java.util.Date;

import java.util.ArrayList;

/**
 *
 * @author Uvenk
 */
public class AgendaRestaurante {
    private ArrayList<Date> fechasEspecial;
    private Date horaApertura;
    private Date horaCierre;

    public AgendaRestaurante() {
    }
    
    

    public ArrayList<Date> getFechasEspecial() {
        return fechasEspecial;
    }

    public void setFechasEspecial(ArrayList<Date> fechasEspecial) {
        this.fechasEspecial = fechasEspecial;
    }

    public Date getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(Date horaApertura) {
        this.horaApertura = horaApertura;
    }

    public Date getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Date horaCierre) {
        this.horaCierre = horaCierre;
    }
    
    
    
    
}
