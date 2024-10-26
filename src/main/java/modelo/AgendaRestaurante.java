/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.time.LocalTime;
import java.util.ArrayList;

/**
 * la clase AgendaRestaurtante se encarga del almacenamiento de
 * la hora de apertura y cierre, fechas especiales, horarios y reservas
 * @author Marcos Ramon Caraballo, Angelina María Vialle, Ian Caraballo, Valentin Rebechi
 */
public class AgendaRestaurante {

    private ArrayList<BloqueoMesaEventoEspecial> fechasEspecial;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private ArrayList<Reserva> reservas;

    /**
     * Constructor por defecto de la clase
     */
    public AgendaRestaurante() {
    }

    /**
     * Constructor Parametrizado de la clase
     *
     * @param fechasEspecial listado de fechas especiales del restaurante
     * @param horaApertura horario de apertura del restaurante
     * @param horaCierre horario de cierre del restaurante
     * @param reservas es un objeto de reserva que se añade al listado de
     * reservas
     */
    public AgendaRestaurante(ArrayList<BloqueoMesaEventoEspecial> fechasEspecial, LocalTime horaApertura, LocalTime horaCierre, Reserva reservas) {
        this.fechasEspecial = fechasEspecial;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.reservas.add(reservas);
    }

    /**
     * Constructor Parametrizado de la clase
     *
     * @param fechasEspecial listado de fechas especiales del restaurante
     * @param horaApertura horario de apertura del restaurante
     * @param horaCierre horario de cierre del restaurante
     * @param reservas listado de reservas del restaurante
     */
    public AgendaRestaurante(ArrayList<BloqueoMesaEventoEspecial> fechasEspecial, LocalTime horaApertura, LocalTime horaCierre, ArrayList<Reserva> reservas) {
        this.fechasEspecial = fechasEspecial;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.reservas = reservas;
    }

    /**
     * Metodo Getter donde se obtienen las reservas
     *
     * @return lista de las reservas realizadas
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Metodo Setter del arraylist reserva
     *
     * @param reservas es una lista de las reservas
     */
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * Metodo Getter donde se obtiene listado de las fechas especiales
     *
     * @return listado de las fechas especiales
     *
     */
    public ArrayList<BloqueoMesaEventoEspecial> getFechasEspecial() {
        return fechasEspecial;
    }
/**
 * Metodo Setter del arraylist fechasEspecial
 * @param fechasEspecial es una lista de las fechas especiales
 */
    public void setFechasEspecial(ArrayList<BloqueoMesaEventoEspecial> fechasEspecial) {
        this.fechasEspecial = fechasEspecial;
    }
/**
 * Metodo Getter donde se obtiene horario de apertura
 * @return el horario de apertura
 */
    public LocalTime getHoraApertura() {
        return horaApertura;
    }
/**
 * Metodo Setter de HorarioApertura
 * @param horaApertura es el horario de apertura
 */
    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }
/**
 * Metodo Getter donde se obtiene horario de cierre
 * @return el horario de cierre
 */
    public LocalTime getHoraCierre() {
        return horaCierre;
    }
/**
 * Metodo Setter HoraCierre 
 * @param horaCierre es el horario de cierre
 */
    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

}
