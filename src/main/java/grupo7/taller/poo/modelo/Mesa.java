/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo7.taller.poo.modelo;

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
