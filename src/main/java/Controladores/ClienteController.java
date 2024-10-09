/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import modelo.Cliente;
import persistencia.ClasesDao.ClienteDAO;
import java.awt.event.ActionListener;
import Views.ClRegisterView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class ClienteController implements ActionListener{
    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private ClRegisterView view;
    
    public ClienteController(Cliente cliente, ClienteDAO clienteDAO, ClRegisterView view){
        this.cliente = cliente;
        this.clienteDAO = clienteDAO;
        this.view = view;
        this.view.jButton_confirmarRegistroCliente.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(view.jButton_confirmarRegistroCliente)){
            String nombre = view.jTextField_registroNombreCliente.getText();
            String correo = view.jTextField_correoRegistroCliente.getText();
            String contrasenia = String.valueOf(view.jPasswordField_registroCliente.getPassword());
            String telefono = view.jTextField_telefonoRegistroCliente.getText();
            if(nombre.equals("")
                    || correo.equals("")
                    || contrasenia.equals("")
                    || telefono.equals("")){
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos");
            }else{
               
                if(esCorreoElectronicoValido(correo)){
                    cliente.setNombre(nombre);
                    cliente.setCorreo(correo);
                    cliente.setContrasenia(contrasenia);
                    cliente.setTelefono(telefono);
                    if(clienteDAO.registrarCliente(cliente)){
                        JOptionPane.showMessageDialog(null,"Registrado exitosamente");
                    }
                } else{
                    JOptionPane.showMessageDialog(null,"Ingrese un correo electronico valido");
                }
                
            }
        }
    }
    public static boolean esCorreoElectronicoValido(String correo) {
        return correo.contains("@");
    }
}
