/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.ClasesDao;

import modelo.Cliente;
import persistencia.SQLiteManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author marco
 */
public class ClienteDAO{
    
    private Connection con = SQLiteManager.getConexion();
    
    public boolean registrarCliente(Cliente cliente){
        boolean registrado = false;
        
        
        try {
            String sql = "INSERT into cliente (nombre,correo,contrasenia,telefono) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCorreo());
            ps.setString(3, cliente.getContrasenia());
            ps.setString(4, cliente.getTelefono());
            registrado = (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return registrado;
        
    }    
    }
