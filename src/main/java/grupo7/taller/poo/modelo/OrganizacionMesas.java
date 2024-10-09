
package grupo7.taller.poo.modelo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.ArrayList; 
public class OrganizacionMesas {
    private ArrayList<Mesa> mesas;
    private ArrayList<Reserva> reservas;
    
    public ArrayList <Mesa> filtroUbi(Ubicacion ubicacion){
        ArrayList<Mesa> nuevaM = new ArrayList();
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
    public ArrayList <Mesa> filtroCapa(Capacidad capacidad){
        ArrayList<Mesa> nuevaM = new ArrayList();
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
    public boolean disponible(int numero, LocalDate dia, LocalTime hora){
        boolean disponible=true;
        /**    for(Iterator it = reservas.iterator();it.hasNext();){
    *        Object o = it.next();
    *        Reserva r= (Reserva) o;
    *       va air numero de mesa o la mesa entera?
    *           if(r.getHora().equals(hora)&& r.r.getFecha().equals(dia)){
    *           disponible=false;  
    * }
    */    
        return disponible;
    }
    /**
    *public ArrayList<Mesa> mesasDisponibles(LocalDate dia, LocalTime hora){
    *    ArrayList<Mesa> nuevaM = new ArrayList();
    *    for(Iterator it = mesas.iterator();it.hasNext();){
    *        Object o = it.next();
    *        Mesa m= (Mesa) o;
    *      
    *    }
    *    return nuevaM;
    *}
    * 
    */
     
}
