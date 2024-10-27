package modelo;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.ArrayList; 
/**
 * 
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class OrganizacionMesasReservas {
    private ArrayList<Mesa> mesas;
    private ArrayList<Reserva> reservas;
   /**
    * 
    * @param ubicacion
    * @return 
    */ 
    public ArrayList <Mesa> filtroUbi(Ubicacion ubicacion){
        ArrayList<Mesa> nuevaM = new ArrayList<Mesa>();
        //si la ubicacion es igual, lo guarda en otro arrayList
        for(Iterator it=mesas.iterator();it.hasNext();){
            Object objeto = it.next();
            Mesa mesa = (Mesa) objeto;
            if(ubicacion.equals(mesa.getUbicacion())){
                nuevaM.add(mesa);
            }
        }
        return nuevaM;
    }
   /**
    * 
    * @param capacidad
    * @return 
    */
    public ArrayList <Mesa> filtroCapa(Capacidad capacidad){
        ArrayList<Mesa> nuevaM = new ArrayList<Mesa>();
        //si la capacidad es igual, lo guarda en otro arraylist
        for(Iterator it = mesas.iterator();it.hasNext();){
            Object o = it.next();
            Mesa m= (Mesa) o;
            if(capacidad.equals(m.getCapacidad())){
                nuevaM.add(m);
            }
        }
        return nuevaM;
    }
  /**
   * 
   * @param numero
   * @param dia
   * @param hora
   * @return 
   */
    public boolean mesaDisponible(int numero, LocalDate dia, LocalTime hora){
        boolean disponible=true;
            for(Iterator it = reservas.iterator();it.hasNext();){
                Object o = it.next();
                Reserva r= (Reserva) o;
               if(r.getMesa().getNumero()==numero&&r.getHora().equals(hora)&& r.getFecha().equals(dia)){
                disponible=false;  
                }
            }  
        return disponible;
    }

    /**
     * 
     * @param dia
     * @param hora
     * @return 
     */
    public ArrayList<Mesa> mesasDisponibles(LocalDate dia, LocalTime hora){
        ArrayList<Mesa> nuevaM = new ArrayList<Mesa>();
        for(Iterator it = mesas.iterator();it.hasNext();){
            Object o = it.next();
            Mesa m= (Mesa) o;
           
            for (Iterator itR= m.getReservas().iterator();itR.hasNext(); ){
                Object obR = itR.next();
                Reserva r = (Reserva) obR;
                if(!r.getFecha().equals(dia)&&!r.getHora().equals(hora)){
                    nuevaM.add(m);
                }
            }
        }
        return nuevaM;
    }
     
    
}  

