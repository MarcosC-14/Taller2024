
package Views;

import modelo.Reserva;
import persistencia.ClasesDao.ReservaDAO;
import java.util.ArrayList;

/**
 * La clase Main es donde se ejecuta todo el programa.
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle, Valentin Rebechi, Ian
 * Caraballo
 */
public class Main {

    public static void main(String[] args) {
        Inicio login = new Inicio();
        login.setVisible(true);
        ReservaDAO reservaDao = new ReservaDAO();
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        reservas = reservaDao.obtenerReservasPasadas();
        reservaDao.cobrarMulta(reservas);
    }

}
