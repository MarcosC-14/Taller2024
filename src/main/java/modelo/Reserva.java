
package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * La clase Reserva se encarga de generar y almacenar la reservacion de una mesa
 * especifica en fecha y horario especifico
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
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
    private Tarjeta tarjeta;
    private int id;
    private boolean multa;

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
    }

    /**
     * Constructor parametrizado de Reserva
     *
     * @param fecha Fecha de la reserva
     * @param hora Hora de la reserva
     * @param comentario Comentario del cliente
     * @param cliente los datos del cliente
     * @param tarjeta los datos de la tarjeta con la que se hace la reserva
     * @param mesa los datos de la mesa.
     * @param tiempoOcupacion la hora de inicio de la ocupacion de la mesa
     * @param tiempoFinalizacion la hora de finalizacion de la ocupacion de la
     * mesa
     */
    public Reserva(LocalDate fecha,
            LocalTime hora,
            String comentario,
            Cliente cliente,
            Tarjeta tarjeta,
            Mesa mesa,
            LocalDate tiempoOcupacion,
            LocalDate tiempoFinalizacion) {
        this.fecha = fecha;
        this.hora = hora;
        this.comentario = comentario;
        this.asistencia = false;
        this.cliente = cliente;
        this.tarjeta = tarjeta;
        this.tiempoOcupacion = null;
        this.tiempoFinalizacion = null;
        this.mesa = mesa;
    }

    /**
     * Se encarga de retornar el valor de la asistencia.
     *
     * @return  true en caso de que asista, false en caso contrario.
     */
    public boolean getAsistencia() {
        return asistencia;
    }

    /**
     * Devuelve la fecha.
     *
     * @return  la fecha de de la reserva.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Setter del atributo fecha.
     *
     * @param   fecha representa la fecha.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Getter del atributo hora.
     *
     * @return  la hora.
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Setter del atributo hora.
     *
     * @param   hora Se asigna al atributo hora.
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * Getter del atributo comentario
     *
     * @return  devuelve el atributo comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Setter del atributo comentario
     *
     * @param   comentario Se asigna al atributo comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Setter del atributo asistencia.
     *
     * @param   asistencia Se asigna al atributo asistencia.
     */
    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    /**
     * Getter del atributo tiempoOcupacion
     *
     * @return devuelve el atributo asistencia
     */
    public LocalTime getTiempoOcupacion() {
        return tiempoOcupacion;
    }

    /**
     * Setter del atributo tiempoOcupacion
     *
     * @param tiempoOcupacion Se asigna al atributo tiempoOcupacion
     */
    public void setTiempoOcupacion(LocalTime tiempoOcupacion) {
        this.tiempoOcupacion = tiempoOcupacion;
    }

    /**
     * Getter del atributo tiempoFinalizacion
     *
     * @return devuelve el atributo tiempoFinalizacion
     */
    public LocalTime getTiempoFinalizacion() {
        return tiempoFinalizacion;
    }

    /**
     * Setter del atributo tiempoFinalizacion
     *
     * @param tiempoFinalizacion Se asigna al atributo tiempoFinalizacion
     */
    public void setTiempoFinalizacion(LocalTime tiempoFinalizacion) {
        this.tiempoFinalizacion = tiempoFinalizacion;
    }

    /**
     * Permite cambiar el valor de mesa de la reserva.
     *
     * @param mesa un objeto de tipo mesa.
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    /**
     * Devuelve una mesa.
     *
     * @return devuelve la mesa relacionada con la reserva.
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * permite cambiar el atributo cliente de reserva
     *
     * @param cliente representa al cliente que esta relacionado con la reserva
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Metodo Getter de cliente
     *
     * @return  cliente representa el cliente asociado a la reserva.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Metodo Setter de tarjeta
     *
     * @param   tarjeta representa la tarjeta utilizada para realizar la 
     * reserva.
     */
    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    /**
     * Metodo Getter de tarjeta
     *
     * @return tarjeta con la que se realizó la reserva
     */
    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    /**
     * Método para enviar recordatorio al cliente de su reserva
     */
    public void enviarRecordatorio() {

    }

    /**
     * Metodo que permite modificar una reserva al cliente
     *
     * @param reserva que se quiere modificar
     */
    public void editarReserva(Reserva reserva) {
        this.fecha = reserva.getFecha();
        this.hora = reserva.getHora();
        this.comentario = reserva.getComentario();

    }

    //Este queda? 
    public void muestraResMesa() {
        //Si muestra reservaciones de mesas, debería estar en cliente? tal vez organizacionMEsas

    }

    /**
     * Metodo Getter de id
     *
     * @return codigo de reserva
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo Setter de id
     *
     * @param id codigo de reserva
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo Getter multa
     *
     * @return true si la multa fue realizada y false en caso contrario
     */
    public boolean getMulta() {
        return multa;
    }

    /**
     * Metodo Setter multa
     *
     * @param multa si se le aplica o no a un cliente
     */
    public void setMulta(boolean multa) {
        this.multa = multa;
    }

    /**
     * Metodo toString que retorna los datos de la reserva
     *
     * @return cadena con los datos de la reserva
     */
    @Override
    public String toString() {
        return "Reserva{" + "fecha=" + fecha + ", hora=" +
                hora + ", comentario=" + comentario + ", asistencia=" +
                asistencia + ", tiempoOcupacion=" + tiempoOcupacion +
                ", tiempoFinalizacion=" + tiempoFinalizacion + ", mesa=" +
                mesa + ", cliente=" + cliente + ", tarjeta=" +
                tarjeta + ", id=" + id + ", multa=" + multa + '}';
    }

}
