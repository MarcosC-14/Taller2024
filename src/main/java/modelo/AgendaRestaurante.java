/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
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
    private ArrayList<Reserva> reservas;
    private ArrayList<Administrador> registra;

    public AgendaRestaurante() {
    }
    
    
    public AgendaRestaurante(ArrayList<Date> fechasEspecial, Date horaApertura, Date horaCierre, Reserva reservas) {
        this.fechasEspecial = fechasEspecial;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.reservas.add(reservas);
    }
    
    
    
    public AgendaRestaurante(ArrayList<Date> fechasEspecial, Date horaApertura, Date horaCierre, ArrayList<Reserva> reservas) {
        this.fechasEspecial = fechasEspecial;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.reservas = reservas;
    }
    
    
    
    
    
    
    
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
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
