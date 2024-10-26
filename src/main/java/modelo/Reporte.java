/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;
import java.util.ArrayList;

/**
 * La clase Reporte da base a todos los metodos necesarios para los reportes
 * requeridos. estos metodos se llevan a cabo en clases que tienen vinculacion
 * con la base de datos
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class Reporte {

    private ArrayList Reserva;

    /**
     *
     * @param d fecha del dia
     * @return reservas para el dia
     */
    public int ReservasDia(Date d) {
        return 0;
    }

    /**
     * Metodo para saber las reservas de un determinado mes
     *
     * @param M mes de la reserva
     * @return
     */

    public int ReservasMes(Date M) {
        return 0;
    }

    /**
     * Metodo para saber las reservas de un determinado año
     *
     * @param a año de la reserva
     * @return año de la reserva
     */
    public int ReservasAnio(Date a) {
        return 0;
    }

    /**
     * Metodo para saber las reservas futuras de un cliente
     *
     * @param c cliente
     */
    public void ReservasFuturas(Cliente c) {

    }

    /**
     * Metodo para saber todas las reservas de un cliente
     *
     * @param c cliente
     */
    public void ReservasClienteVida(Cliente c) {

    }

    /**
     * Metodo para obtener el mejor cliente(con mayor asistencia)
     */
    public void MejorCliente() {

    }

    /**
     * Metodo para obtener el peor cliente(con menor asistencia)
     */
    public void PeoresClientes() {

    }

    /**
     * obtener todas las reservas dentro de determinadas fechas
     *
     * @param inicio fecha desde cuando se quiere saber
     * @param fin fecha hasta cuando se quiere saber
     */
    public void ReservaDetallada(Date inicio, Date fin) {

    }

    /**
     * Se obtiene informacion sobre la concurrencia de acuerdo a la estacion del
     * año
     */
    public void ComensalesConcurrenciaEstacion() {

    }
}
