package modelo;

/**
 * La clase tarjeta se encarga de almacenar la informacion de la tarjeta con la
 * que el usuario realiza una reserva
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class Tarjeta {

    private String nombre;
    private String emisor;
    private String numero;
    private String codSeguridad;

    /**
     * Constructor por defecto de Tarjeta
     */
    public Tarjeta() {
        this.nombre = "";
        this.emisor = "";
        this.numero = "";
        this.codSeguridad = "";
    }

    /**
     * Constructor parametrizado de Tarjeta
     *
     * @param nombre Es el nombre del dueño de la tarjeta
     * @param emisor Es el emisor de la tarjeta
     * @param numero Es numero de la tarjeta
     * @param codSeguridad Es el codigo de seguridad de la tarjeta
     */
    public Tarjeta(String nombre,
            String emisor,
            String numero,
            String codSeguridad) {
        this();
        this.nombre = nombre;
        this.numero = numero;
        this.emisor = emisor;
        this.codSeguridad = codSeguridad;
    }

    /**
     * Getter del atributo nombre
     *
     * @return devuelve el atributo nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     *
     * @param nombre Se asigna en el atributo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo emisor
     *
     * @return devuelve el atributo emisor
     */
    public String getEmisor() {
        return emisor;
    }

    /**
     * Setter del atributo emisor
     *
     * @param emisor Se asigna en el atributo emisor
     */
    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    /**
     * Getter del atributo numero
     *
     * @return devuelve el atributo numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Setter del atributo numero
     *
     * @param numero Se asigna en el atributo numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Getter del atributo codSeguridad
     *
     * @return devuelve el atributo codSeguridad
     */
    public String getCodSeguridad() {
        return codSeguridad;
    }

    /**
     * Setter del atributo codSeguridad
     *
     * @param codSeguridad Se asigna en el atributo codSeguridad
     */
    public void setCodSeguridad(String codSeguridad) {
        this.codSeguridad = codSeguridad;
    }

}
