/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;
import modelo.Reserva;
import persistencia.ClasesDao.ReservaDAO;
import java.util.ArrayList;
/**
 *
 * @author Uvenk
 */
public class main {
    public static void main(String[] args) {
       Inicio login = new Inicio();
        login.setVisible(true);
        ReservaDAO reservaDao = new ReservaDAO();
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        reservas= reservaDao.obtenerReservasPasadas();
        
        boolean bandera= reservaDao.cobrarMulta(reservas);
        System.out.println("Cobro multa: "+bandera);
        }
       //ClRegisterView registro = new ClRegisterView();
      // registro.setVisible(true);
}
        

