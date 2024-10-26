/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import modelo.Cliente;
import persistencia.ClasesDao.ClienteDAO;
import java.awt.event.ActionListener;
import Views.ClRegisterView;
import Views.ClView;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 * La clase ClienteController se encarga de manejar el correcto registro de los
 * clientes, brindando metodos y controles de seguridad para que todos los datos
 * que este ingresen sean validos
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class ClienteController implements ActionListener {

    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private ClRegisterView view;
    private ClView view2;

    /**
     * *Constructor por defecto de la clase ClienteController.
     */
    public ClienteController() {
    }

    /**
     * Este metodo se encarga de registrar a un nuevo cliente en la base de
     * datos.
     *
     * @param cliente, representa a un objeto cliente, el cual contendrá los
     * datos a registrar.
     * @param clienteDao, contiene los metodos que interactuan entre cliente y
     * la base de datos.
     */
    public ClienteController(Cliente cliente, ClienteDAO clienteDAO) {
        this.cliente = cliente;
        this.clienteDAO = clienteDAO;

    }

    /**
     *
     * @param view hace referencia a la interfaz grafica que se le muestra al
     * cliente al momento de registrarse Tambien se pone el boton de confirmar
     * en escucha para que cuando sea oprimido ejecute su metodo
     * correspondiente.
     *
     */
    public void setClRegisterView(ClRegisterView view) {
        this.view = view;
        this.view.jButton_confirmarRegistroCliente.addActionListener(this);
    }

    /**
     * actionPerformed es el metodo que se ejecuta cuando se aprieta el boton de
     * registrar
     *
     * @param e es el evento que activa el boton de registrar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * En este if se controla que todos los datos ingresados dentro de los
         * campos mostrados sean validos, si es asi confirma el registro, de lo
         * contrario muestra una alerta solicitando que se completen todos los
         * campos solicitados de forma adecuada.
         */
        if (e.getSource().equals(view.jButton_confirmarRegistroCliente)) {
            String nombre = view.jTextField_registroNombreCliente.getText();
            String correo = view.jTextField_correoRegistroCliente.getText();
            String contrasenia = String.valueOf(view.jPasswordField_registroCliente.getPassword());
            String telefono = view.jTextField_telefonoRegistroCliente.getText();
            if (nombre.equals("")
                    || correo.equals("")
                    || contrasenia.equals("")
                    || telefono.equals("")) {
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos");
            } else {

                if (esCorreoElectronicoValido(correo)) {
                    cliente.setNombre(nombre);
                    cliente.setCorreo(correo);
                    cliente.setContrasenia(contrasenia);
                    cliente.setTelefono(telefono);
                    if (clienteDAO.registrarCliente(cliente)) {
                        JOptionPane.showMessageDialog(null, "Registrado exitosamente");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un correo electronico valido");
                }

            }
        } else if (e.getSource().equals(view2.jButton_nuevaReserva)) {

        }
    }

    /**
     *
     * @param correo es el correo ingresado por el usuario
     * @return true si el correo ingresado contiene un @, de lo contrario
     * retorna false
     */
    public static boolean esCorreoElectronicoValido(String correo) {
        return correo.contains("@");
    }

    /**
     * Ni idea que hace el metodo()
     *
     * @param fecha
     * @return
     */
    public static boolean esFormatoFechaValido(String fecha) {
        // Definimos el patrón del formato de fecha esperado (dd/mm/aa)
        String formatoFecha = "\\d{2}/\\d{2}/\\d{4}";

        // Comprobamos si la fecha ingresada coincide con el formato esperado
        return fecha.matches(formatoFecha);
    }
}
