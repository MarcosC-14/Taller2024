/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 * La clase mesa crea la estructura necesaria para almacenar la informacion de
 * cada mesa dentro del restaurte.
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class Mesa {

    private int numero;
    private Capacidad capacidad;
    private Ubicacion ubicacion;
    public String mapa;
    private ArrayList<Reserva> reservas;

    public Mesa() {
        this.reservas = new ArrayList<Reserva>();
    }

    /**
     * Contructor de mesa para el numero, capacidad y ubicacion.
     *
     * @param numero el numero que identifica la mesa.
     * @param capacidad que indica cuantas personas maximo se pueden sentar en
     * esa mesa.
     * @param ubicacion que indica donde se ubica la mesa.
     */
    public Mesa(int numero, Capacidad capacidad, Ubicacion ubicacion) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
    }

    /**
     * Contructor de mesa para el numero, capacidad, ubicacion y reserva
     *
     * @param numero el numero que identifica la mesa.
     * @param capacidad que indica cuantas personas maximo se pueden sentar en
     * esa mesa.
     * @param ubicacion que indica donde se ubica la mesa.
     * @param reserva contiene la informacion de la reserva que se va a agredar.
     */
    public Mesa(int numero, Capacidad capacidad, Ubicacion ubicacion, Reserva reserva) {
        this();
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.reservas.add(reserva);
    }
/**
     * Contructor de mesa para el numero, capacidad, ubicacion y  listado de reservas
     *
     * @param numero el numero que identifica la mesa.
     * @param capacidad que indica cuantas personas maximo se pueden sentar en
     * esa mesa.
     * @param ubicacion que indica donde se ubica la mesa.
     * @param reservas contiene el listado de reservas
     */
    public Mesa(int numero, Capacidad capacidad, Ubicacion ubicacion, ArrayList<Reserva> reservas) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.reservas = reservas;
    }
/**
 * Metodo Getter de reservas
 * @return listado de las reservas de la mesa
 */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
/**
 * Metodo Setter de reservas
 * @param reservas es el listado de reservas 
 */
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
/**
 * Metodo que se encarga de crear un listado de reservas en caso de no existir y agrega una nueva reserva al listado
 * @param reserva es la reserva a agregar al listado
 */
    public void agregarReserva(Reserva reserva) {
        if (reservas.isEmpty()) {
            this.reservas = new ArrayList<Reserva>();
        }
        reservas.add(reserva);
    }
/**
 * Metodo Getter de numero
 * @return numero de la mesa
 */
    public int getNumero() {
        return numero;
    }
/**
 * Metodo Setter de numero
 * @param numero de la mesa
 */
    public void setNumero(int numero) {
        this.numero = numero;
    }
/**
 * Metodo Getter de Capacidad
 * @return cantidad de sillas que tiene la mesa
 */
    public Capacidad getCapacidad() {
        return capacidad;
    }
/**
 * Metodo Setter de capacidad
 * @param capacidad cantidad de sillas que tiene la mesa 
 */
    public void setCapacidad(Capacidad capacidad) {
        this.capacidad = capacidad;
    }
/**
 * Metodo Getter de Ubicacion
 * @return ubicacion de la mesa
 */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }
/**
 * Metodo Setter de Ubicacion
 * @param ubicacion de la mesa
 */
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    /**
     * Metodo que guarda la capacidad y la ubicacion en la mesa.
     * @return  La mesa con la nueva capacidad y ubicacion.
     */
    public Mesa obtenerCapUbi(){
        switch (this.getNumero()) {
            case 3:
                this.setCapacidad(Capacidad.dos);
                break;
            case 1, 6, 7, 8:
                this.setCapacidad(Capacidad.cuatro);
                break;
            case 4:
                this.setCapacidad(Capacidad.seis);
                break;
            case 2:
                this.setCapacidad(Capacidad.ocho);
                break;
            case 5:
                this.setCapacidad(Capacidad.diez);
                break;
        }
        switch (this.getNumero()) {
            case 1, 2, 3, 4, 5:
                this.setUbicacion(Ubicacion.Interior);
                break;
            case 6, 7, 8:
                this.setUbicacion(Ubicacion.Exterior);
                break;
        }
        return this;
    }

}
