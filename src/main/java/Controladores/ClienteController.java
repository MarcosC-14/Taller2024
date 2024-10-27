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
import javax.swing.JOptionPane;

/**
 * La clase ClienteController se encarga de manejar el correcto registro de los
 * clientes, brindando metodos y controles de seguridad para que todos los datos
 * que este ingresen sean validos
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle, Valentin Rebechi, Ian
 * Caraballo.
 * @version 27/10/2024.
 */
public class ClienteController implements ActionListener {

    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private ClRegisterView view;
    private ClView view2;

    /**
     * Constructor por defecto de la clase ClienteController.
     */
    public ClienteController() {
    }

    /**
     * Este metodo se encarga de registrar a un nuevo cliente en la base de
     * datos.
     *
     * @param   cliente representa a un objeto de tipo cliente, el cual 
     * contendrá los datos a registrar.
     * @param   clienteDAO contiene los metodos que interactuan entre cliente y
     * la base de datos.
     */
    public ClienteController(Cliente cliente, ClienteDAO clienteDAO) {
        this.cliente = cliente;
        this.clienteDAO = clienteDAO;

    }

    /**
     * Guarda la referencia de la ventana ClRegisterView y permite que al 
     * presionar en el boton Registrarse, se ejecute el metodo actionPerformed 
     * de esta clase.
     * @param   view representa a la ventana donde se registra el cliente 
     * ClRegisterView
     *
     */
    public void setClRegisterView(ClRegisterView view) {
        this.view = view;
        this.view.jButton_confirmarRegistroCliente.addActionListener(this);
    }

    /**
     * Es el metodo que se ejecuta cuando se aprieta el boton Registrar.
     * Controla que los datos ingresados sean validos, si es asi lo registra, si
     * no muestra un mensaje pidiendo que complete los datos.
     *
     * @param e es el evento que activa el boton de registrar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
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
     * Se encarga de revisar que el correo electronico tenga un @.
     * @param   correo es el correo ingresado por el usuario.
     * @return  true si el correo ingresado contiene un @, de lo contrario
     * retorna false.
     */
    public static boolean esCorreoElectronicoValido(String correo) {
        return correo.contains("@");
    }

    /**
     * Revisa que la fecha ingresada coincida con el formato de fecha 
     * establecida.
     *
     * @param   fecha representa la fecha que debe revisar.
     * @return  true en caso de que sea un formato valido, false en caso 
     * contrario.
     */
    public static boolean esFormatoFechaValido(String fecha) {
        // Definimos el patrón del formato de fecha esperado (dd/mm/aa)
        String formatoFecha = "\\d{2}/\\d{2}/\\d{4}";

        // Comprobamos si la fecha ingresada coincide con el formato esperado
        return fecha.matches(formatoFecha);
    }
}
