/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class Reserva {
    private LocalDate fecha;
    private LocalTime hora;
    private String comentario;
    private boolean asistencia;
    private LocalTime tiempoOcupacion;
    private LocalTime tiempoFinalizacion;
    private Mesa mesa;
    private Cliente cliente;
    private Tarjeta tarjeta;    //necesita estar en el metodo realizar reserva de cliente?
    private AgendaRestaurante utiliza;

    /**
     * Constructor por defecto de Reserva
     */
    public Reserva() {
        this.fecha = null;
        this.hora = null;
        this.comentario = "";
        this.asistencia = false;
        this.tiempoOcupacion = null;
        this.tiempoFinalizacion = null;
        
        this.tarjeta = tarjeta;
    }

    /**
     * Constructor parametrizado de Reserva
     * 
     * @param fecha Fecha de la reserva
     * @param hora Hora de la reserva
     * @param comentario Comentario del cliente
     * 
     */
    public Reserva(LocalDate fecha, LocalTime hora, String comentario,Cliente cliente, Tarjeta tarjeta) {
        this.fecha = fecha;
        this.hora = hora;
        this.comentario = comentario;
        this.asistencia = false;
        this.cliente=cliente;
        this.tarjeta = tarjeta;
        this.tiempoOcupacion = null;
        this.tiempoFinalizacion = null;
        
    }
    
    /**
     * public Reserva(LocalDate fecha, LocalTime hora, String comentario, Cliente cliente, Tarjeta tarjeta) {
        this.fecha = fecha;
        this.hora = hora;
        this.comentario = comentario;
        this.asistencia = false;
        this.cliente = cliente;
        this.tarjeta = tarjeta;
        this.tiempoOcupacion = null;
        this.tiempoFinalizacion = null;
        
    }
    */
    

    /**
     * Getter del atributo fecha
     * @return 
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Setter del atributo fecha
     * @param fecha 
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Getter del atributo hora
     * @return 
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Setter del atributo hora
     * @param hora Se asigna al atributo hora 
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * Getter del atributo comentario
     * @return devuelve el atributo comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Setter del atributo comentario
     * @param comentario Se asigna al atributo comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Getter del atributo asistencia
     * @return devuelve el atributo asistencia
     */
    public boolean getAsistencia() {
        return asistencia;
    }

    /**
     * Setter del atributo asistencia
     * @param asistencia Se asigna al atributo asistencia
     */
    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    /**
     * Getter del atributo tiempoOcupacion
     * @return devuelve el atributo asistencia
     */
    public LocalTime getTiempoOcupacion() {
        return tiempoOcupacion;
    }

    /**
     * Setter del atributo tiempoOcupacion
     * @param tiempoOcupacion Se asigna al atributo tiempoOcupacion
     */
    public void setTiempoOcupacion(LocalTime tiempoOcupacion) {
        this.tiempoOcupacion = tiempoOcupacion;
    }

    /**
     * Getter del atributo tiempoFinalizacion
     * @return devuelve el atributo tiempoFinalizacion
     */
    public LocalTime getTiempoFinalizacion() {
        return tiempoFinalizacion;
    }

    /**
     * Setter del atributo tiempoFinalizacion
     * @param tiempoFinalizacion Se asigna al atributo tiempoFinalizacion
     */
    public void setTiempoFinalizacion(LocalTime tiempoFinalizacion) {
        this.tiempoFinalizacion = tiempoFinalizacion;
    }
    
    /**
     * Método para enviar recordatorio al cliente de su reserva 
     */
    public void enviarRecordatorio(){
        //envía recordatorio al cliente de la reserva
    }
    
    public void editarReserva(Reserva reserva){
        this.fecha = reserva.getFecha();
        this.hora = reserva.getHora();
        this.comentario = reserva.getComentario();
        
        //falta modificar la mesa
    }
        
    
    public void muestraResMesa(){
        //Si muestra reservaciones de mesas, debería estar en cliente?
        
    }
    
    
}
