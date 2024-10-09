/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

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
    
    private ArrayList<Reserva> reservas;

    public Mesa() {
        this.reservas= new ArrayList<Reserva>();
    }
    
    public Mesa(int numero, Capacidad capacidad, Ubicacion ubicacion, Cliente cliente) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;   
       // this.clientes.add(cliente);
    }

    public Mesa(int numero, Capacidad capacidad, Ubicacion ubicacion, ArrayList<Cliente> clientes) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        //this.clientes = clientes;
    }
    
    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

   // public ArrayList<Cliente> getClientes() {
     //   return clientes;
    //}

    public void setClientes(ArrayList<Cliente> clientes) {
    //    this.clientes = clientes;
    }
    
    public void agregarCliente(Cliente cliente){
      //  if(clientes.size()==0){
        //    this.clientes= new ArrayList<Cliente>();
       // }
        //clientes.add(cliente);
    }
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero){
        this.numero=numero;
    }

    public Capacidad getCapacidad() {
        return capacidad;
    }

    public void setCapacidad( Capacidad capacidad){
        this.capacidad= capacidad;
    }
    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    
    public void setUbicacion(Ubicacion ubicacion){
        this.ubicacion= ubicacion;
    }
    
    public boolean dispo(int n, Date fecha){
        return false;
    }
    
    public void filtroUbi(Ubicacion u){
        
    }
    public void filtroCapa(Capacidad c){
        
    }
}
