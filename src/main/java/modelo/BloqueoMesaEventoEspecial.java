/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * la clase BloqueoMesaEventoEspecial se encarga de la reserva forzada por el
 * administrador cuando existe un evento especial en el restaurante.
 *
 * @author Marcos Ramon Caraballo
 * @version 27/10/2024
 */
public class BloqueoMesaEventoEspecial {

    private int numMesa;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fecha;
    private int id;

    /**
     *
     * @return el numero de la mesa
     */
    public int getNumMesa() {
        return numMesa;
    }

    /**
     *
     * @param numMesa es el numero correspondiente a la mesa
     */
    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    /**
     *
     * @return horario en que se inicia la reserva
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     *
     * @param horaInicio es la hora en la que se inicia la reserva
     */

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     *
     * @return horario en el que finaliza la reserva
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     *
     * @param horaFin horario en el que finaliza la reserva
     */
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
/**
 * 
 * @return fecha en la que se genera la reserva 
 */
    public LocalDate getFecha() {
        return fecha;
    }
/**
 * 
 * @param fecha es la fecha con el formato dd/mm/aaaa en la que se genera la reserva 
 */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
/**
 * 
 * @return codigo que se genera en la reserva 
 */
    public int getId() {
        return id;
    }
/**
 * 
 * @param id, codigo generado en la reserva 
 */
    
    public void setId(int id) {
        this.id = id;
    }
/**
 * Constructor predeterminado que da valor 0 o null a sus atributos
 */
    public BloqueoMesaEventoEspecial() {
        this.numMesa = 0;
        this.horaInicio = null;
        this.horaFin = null;
        this.fecha = null;
    }
/**
 * Constructor parametrizado de la clase, donde los valores son pasados por parametro
 * @param numMesa, es el numero de mesa que se requiere reservar
 * @param horaInicio, horario de inicio de la reserva
 * @param horaFin, horario de finalizacion de la reserva
 * @param fecha, fecha de la reserva
 * @param id , codigo generado por la reserva
 */
    public BloqueoMesaEventoEspecial(int numMesa, LocalTime horaInicio, LocalTime horaFin, LocalDate fecha, int id) {
        this.numMesa = numMesa;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.id = id;
    }

}
