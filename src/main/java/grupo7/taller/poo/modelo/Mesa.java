/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo7.taller.poo.modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Valentin
 */
public class Mesa {
    private int numero;
    private Capacidad capacidad;
    private Ubicacion ubicacion;
    public String mapa;
    private Empleado recepcionista;
    private ArrayList<Cliente> clientes;

    public Mesa() {
    }
    

    public Mesa(int numero, Capacidad capacidad, Ubicacion ubicacion, Empleado recepcionista, Cliente cliente) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.recepcionista = recepcionista;
        this.clientes.add(cliente);
    }

    public Mesa(int numero, Capacidad capacidad, Ubicacion ubicacion, Empleado recepcionista, ArrayList<Cliente> clientes) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.recepcionista = recepcionista;
        this.clientes = clientes;
    }
    
    
    
    
    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public Empleado getRecepcionista() {
        return recepcionista;
    }

    public void setRecepcionista(Empleado recepcionista) {
        this.recepcionista = recepcionista;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    
    
   

    public int getNumero() {
        return numero;
    }

    public Capacidad getCapacidad() {
        return capacidad;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    public boolean dispo(int n, Date fecha){
        return false;
    }
    
    public void filtroUbi(Ubicacion u){
        
    }
    public void filtroCapa(Capacidad c){
        
    }
}
