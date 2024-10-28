
package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * La clase BloqueoMesaEventoEspecial se encarga del bloque de de la/s mesa/s
 * por el administrador cuando existe un evento especial en el restaurante.
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class BloqueoMesaEventoEspecial {

    private int numMesa;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fecha;
    private int id;

    /**
     * Constructor por defecto de la clase que da valor 0 o null a sus
     * atributos.
     */
    public BloqueoMesaEventoEspecial() {
        this.numMesa = 0;
        this.horaInicio = null;
        this.horaFin = null;
        this.fecha = null;
    }

    /**
     * Constructor parametrizado de la clase, donde los valores son pasados por
     * parametro.
     *
     * @param numMesa es el numero de mesa que se requiere bloquear
     * @param horaInicio horario de inicio del bloqueo.
     * @param horaFin horario de finalizacion del bloqueo.
     * @param fecha fecha del bloque.
     * @param id codigo generado a partir del bloqueo de la mesa/s.
     */
    public BloqueoMesaEventoEspecial(int numMesa,
            LocalTime horaInicio,
            LocalTime horaFin,
            LocalDate fecha,
            int id) {
        this.numMesa = numMesa;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.id = id;
    }

    /**
     * Se encarga de retornar el numero de la mesa que se bloquea.
     *
     * @return el numero de la mesa.
     */
    public int getNumMesa() {
        return numMesa;
    }

    /**
     * Se esncarga de asignarle el valor al numero de mesa.
     *
     * @param numMesa es el numero correspondiente a la mesa.
     */
    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    /**
     * Se encarga de retornar la hora de inicio del bloqueo.
     *
     * @return horario en que se inicia wl bloqueo.
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Se encarga de asignarle la hora a la hora de inicio del bloqueo.
     *
     * @param horaInicio es la hora en la que se inicia el bloqueo.
     */
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Retorna la hora de finalizacion del bloqueo.
     *
     * @return horario en el que finaliza el bloqueo.
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     * Asigna la hora de finalizacion al bloqueo.
     *
     * @param horaFin horario en el que finaliza el bloqueo.
     */
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * Retorna la fecha en la que se realiza el bloqueo.
     *
     * @return fecha en la que se genera el bloqueo.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Asigna la fecha al bloqueo
     *
     * @param fecha es la fecha con el formato dd/mm/aaaa en la que se genera el
     * bloqueo.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Retorna codigo que identifica al bloqueo.
     *
     * @return codigo que identifica al bloqueo.
     */
    public int getId() {
        return id;
    }

    /**
     * Asigna el id al bloqueo.
     *
     * @param id codigo generado en el bloqueo.
     */
    public void setId(int id) {
        this.id = id;
    }

}
