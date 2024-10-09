/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Valentin
 */
public class Mesa {
    private int numero;
    private Capacidad capacidad;
    private Ubicacion ubicacion;
    public String mapa;
    private ArrayList<Reserva> reservas;

    public Mesa() {
        this.reservas= new ArrayList<Reserva>();
    }
    
    public Mesa(int numero, Capacidad capacidad, Ubicacion ubicacion, Reserva reserva) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;   
        this.reservas.add(reserva);
    }

    public Mesa(int numero, Capacidad capacidad, Ubicacion ubicacion, ArrayList<Reserva> reservas) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.reservas = reservas;
    }
    
    public ArrayList<Reserva> getReservas(){
        return reservas;
    }
    
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    public void agregarReserva(Reserva reserva){
        if(reservas.size()==0){
            this.reservas= new ArrayList<Reserva>();
        }
        reservas.add(reserva);
    }
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero){
        this.numero=numero;
    }

    public Capacidad getCapacidad() {
        return capacidad;
    }

    public void setCapacidad( Capacidad capacidad){
        this.capacidad= capacidad;
    }
    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    
    public void setUbicacion(Ubicacion ubicacion){
        this.ubicacion= ubicacion;
    }
    
}
