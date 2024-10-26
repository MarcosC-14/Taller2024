/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Views.AdView;
import Views.ClRegisterView;
import Views.ClView;
import Views.EpView;
import Views.Inicio;
import modelo.Cliente;
import modelo.Empleado;
import persistencia.ClasesDao.ClienteDAO;
import persistencia.ClasesDao.EmpleadoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * La clase LoginControl se encarga de manejar el correcto ingreso de los
 * usuarios al programa, verificando si se encuentran registrados en la base de
 * datos y que rol cumplen, para asi dirigirlos a su jframe correspondiente
 * Incluye botones para dirigirlos a recuperar contraseña a un usuario que haya
 * olvidado la misma o al registro de un nuevo cliente
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class LoginControl implements ActionListener {

    private Cliente cliente;
    private ClienteDAO cliente_dao;
    private Inicio inicio;
    private ClRegisterView registro;
    private Empleado empleado;
    private EmpleadoDAO empleadoDao;

    /**
     *
     * @param cliente
     * @param cliente_Dao
     */

    public LoginControl(Cliente cliente, ClienteDAO cliente_Dao) {
        this.cliente = cliente;
        this.cliente_dao = cliente_Dao;
    }

    /**
     *
     * @param empleado, representa un objeto de tipo empleado
     * @param empleadoDao,contiene los metodos que interactuan entre el objeto
     * empleado y la base de datos.
     */
    public LoginControl(Empleado empleado, EmpleadoDAO empleadoDao) {
        this.empleado = empleado;
        System.out.println(empleado);

        this.empleadoDao = empleadoDao;
        System.out.println(empleadoDao);

    }

    /**
     *
     * @param i es la referencia al jframe mostrado al comienzo del programa Se
     * ponene todos los botones que tiene este frame en escucha para que al ser
     * presionados realicen su accion correspondiente
     */
    public void setInicio(Inicio i) {
        this.inicio = i;
        this.inicio.jButton_ingreso.addActionListener(this);
        this.inicio.jButton_recuperarContraseña.addActionListener(this);
        this.inicio.jButton_registro.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtener datos de la vista

    }
}
