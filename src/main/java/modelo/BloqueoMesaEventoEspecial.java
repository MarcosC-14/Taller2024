/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author marco
 */
public class BloqueoMesaEventoEspecial {
    private int numMesa;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fecha;
    private int id;

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BloqueoMesaEventoEspecial() {
        this.numMesa = 0;
        this.horaInicio = null;
        this.horaFin = null;
        this.fecha = null;        
    }
    

    public BloqueoMesaEventoEspecial(int numMesa, LocalTime horaInicio, LocalTime horaFin, LocalDate fecha, int id) {
        this.numMesa = numMesa;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.id = id;
    }
    
    
    
}
