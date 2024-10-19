/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Views.AdView;
import Views.ClRegisterView;
import Views.ClView;
import Views.Inicio;
import modelo.Cliente;
import persistencia.ClasesDao.ClienteDAO;
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

    public LoginControl(Cliente cliente, ClienteDAO cliente_Dao) {
        this.cliente = cliente;
        this.cliente_dao = cliente_Dao;
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
        String correo = inicio.txt_username.getText().trim();
        String pass = String.valueOf(inicio.txt_password.getPassword());

        if (e.getSource() == inicio.jButton_ingreso) {
            // validar que los campos no esten vacios 
                
            if (!correo.equals("") || !pass.equals("")) {
                // pasar parametros al metodo login
                cliente = cliente_dao.loginQuery(correo, pass);
                //verficar existencia de usuario
                if (cliente.getCorreo().equals(correo)) {
                    ClView aux = new ClView(cliente);
                    aux.setVisible(true);
                    this.inicio.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña invalido");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña invalido");
            }
        } else if (e.getSource() == inicio.jButton_registro) {
            this.registro = new ClRegisterView();
            this.registro.setVisible(true);
            this.inicio.dispose();

        }
    }
}
