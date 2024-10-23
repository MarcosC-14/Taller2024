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
 *
 * @author Uvenk
 */
public class LoginControl implements ActionListener {

    private Cliente cliente;
    private ClienteDAO cliente_dao;
    private Inicio inicio;
    private ClRegisterView registro;
    private Empleado empleado;
    private EmpleadoDAO empleadoDao;
    
    public LoginControl(Cliente cliente, ClienteDAO cliente_Dao) {
        this.cliente = cliente;
        this.cliente_dao = cliente_Dao;
    }
    public LoginControl(Empleado empleado, EmpleadoDAO empleadoDao){
        this.empleado= empleado;
            System.out.println(empleado);

        this.empleadoDao= empleadoDao;
            System.out.println(empleadoDao);

    }

    public void setInicio(Inicio i){
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
