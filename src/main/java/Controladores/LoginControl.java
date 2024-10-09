/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Views.AdView;
import Views.Inicio;
import modelo.Cliente;
import modelo.ClienteDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Uvenk
 */
public class LoginControl implements ActionListener{
     private Cliente cliente;
     private ClienteDao cliente_dao;
     private Inicio inicio;

    public LoginControl(Cliente cliente, ClienteDao cliente_Dao, Inicio inicio) {
        this.cliente = cliente;
        this.cliente_dao = cliente_Dao;
        this.inicio = inicio;
        this.inicio.jButton_ingreso.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        // Obtener datos de la vista
        String user = inicio.txt_username.getText().trim();
        String pass = String.valueOf(inicio.txt_password.getPassword());

        if (e.getSource() == inicio.jButton_ingreso) {
            // validar que los campos no esten vacios 

            if (!user.equals(" ") || !pass.equals("")) {
                // pasar parametros al metodo login
                cliente = cliente_dao.loginQuery(user, pass);
                //verficar existencia de usuario
                if (cliente.getNombre() != null) {
                        AdView aux = new AdView();
                        aux.setVisible(true);
                    }
                    this.inicio.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña invalido");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    

