/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo7.taller.poo.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Uvenk
 */
public class ConectorSql {
    private String database_name = "Restaurante";
    private String user="root";
    private String password="root";
    private String url = "jdbc:mysql://localhost:3306/" + database_name;
    Connection conn = null; 

    public Connection getConnection(){
        try{
         // obtener valor del Driver
         Class.forName("com.mysql.cj.jdbc.Driver");
        //Obtener Coneccion
         conn= DriverManager.getConnection(url,user,password);
        }catch(ClassNotFoundException e){
           System.err.print("Ha ocurrido un ClasssNotFoundException"+ e.getMessage());
         }catch(SQLException e){
        System.err.println("Ha ocurrido un SQLException" + e.getMessage());
        }
        return conn;
    }
}
