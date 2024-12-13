
package modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.time.format.DateTimeFormatter;

/**
 * La clase CrearExcel se encarga de crear los documentos tipo xlsx de:
 * -Reserva.
 * -Cliente.
 * -Reservas por estacion.
 * Ademas de disponer de un metodo para guardar cada archivo.
 * @author  Marcos Ramon Caraballo, Angelina María Vialle, Valentin Rebechi, Ian
 * Caraballo.
 * @version 27/10/2024
 */
public class CrearExcel {

    private static String FILE = "Excel.xlsx";
/**
 * Crea un archivo tipo xlsx de las Reservas, con un titulo y un ArrayList de 
 * reservas.
 * @param   titulo representa el nombre que tendra el documento.
 * @param   reservas representa la lista de reservas que produciran el reporte.
 * @throws  Exception puede generarla al crear el archivo o guadarla en la 
 * carpeta.
 */
    public static void hacerExcelReserva(String titulo, ArrayList<Reserva> reservas) throws Exception {
        FILE = "ExcelExportados/" + titulo + ".xlsx";
        Files.createDirectories(Paths.get("ExcelExportados"));
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet Sheet = workBook.createSheet("Reservas");
        Row header = Sheet.createRow(0);
        header.createCell(0).setCellValue("Mesa");
        header.createCell(1).setCellValue("Fecha");
        header.createCell(2).setCellValue("Hora");
        header.createCell(3).setCellValue("Tiempo de Ocupacion");
        header.createCell(4).setCellValue("Tiempo de Finalizacion");
        header.createCell(5).setCellValue("Cliente");
        header.createCell(6).setCellValue("Comensales");
        for (int i = 0; i < reservas.size(); i++) {
            HSSFRow row = Sheet.createRow((short) i + 1);
            row.createCell(0).setCellValue(reservas.get(i).getMesa().getNumero());
            row.createCell(1).setCellValue(reservas.get(i).getFecha().
                    format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            row.createCell(2).setCellValue(reservas.get(i).getHora().getHour());
            if (!(reservas.get(i).getTiempoOcupacion() == null)) {
                row.createCell(3).setCellValue(reservas.get(i).getTiempoOcupacion().
                        format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
            if (!(reservas.get(i).getTiempoFinalizacion() == null)) {
                row.createCell(4).setCellValue(reservas.get(i).getTiempoFinalizacion()
                        .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
            row.createCell(5).setCellValue(reservas.get(i).
                    getCliente().getNombre());
            row.createCell(6).setCellValue(reservas.get(i).
                    getMesa().getCapacidad().toString());
        }
        guardarArchivo(workBook, FILE);
    }
/**
 * Crea un archivo tipo xlsx de los clientes, con un titulo y un ArrayList de 
 * clientes.
 * @param   titulo representa el nombre que tendra el documento.
 * @param   cliente representa la lista de clientes que produciran el reporte.
 * @throws  Exception puede generarla al crear el archivo o guadarla en la 
 * carpeta. 
 */
    public static void hacerExcelCliente(String titulo, ArrayList<Cliente> cliente) throws Exception {
        FILE = "ExcelExportados/" + titulo + ".xlsx";
        Files.createDirectories(Paths.get("ExcelExportados"));
        HSSFWorkbook workBook = new HSSFWorkbook();

        HSSFSheet Sheet = workBook.createSheet("Cliente");

        // Crea los valores de las tablas
        Row header = Sheet.createRow(0);
        header.createCell(0).setCellValue("Nombre       ");
        header.createCell(1).setCellValue("Telefono       ");
        header.createCell(2).setCellValue("Correo     ");

        for (int i = 0; i < cliente.size(); i++) {

            HSSFRow row = Sheet.createRow((short) i + 1);

            row.createCell(0).setCellValue(cliente.get(i).getNombre());

            row.createCell(1).setCellValue(cliente.get(i).getTelefono());
            row.createCell(2).setCellValue(cliente.get(i).getCorreo());
        }
        guardarArchivo(workBook, FILE);
    }
  
/**
 * Crea un archivo tipo xlsx de las reservas por estacion, con una cadena de 
 * valores.
 * @param   valores representa la cantidad de reservas por estacion que hubo.
 * @throws  Exception puede generarla al crear el archivo o guadarla en la 
 * carpeta.
 */
    public static void HacerExcelEstaciones(String[] valores) throws Exception {
        FILE = "ExcelExportados/ConcurrenciasPorEstacion.xlsx";
        Files.createDirectories(Paths.get("ExcelExportados"));
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet Sheet = workBook.createSheet("Estacion");
        Row header = Sheet.createRow(0);
        header.createCell(0).setCellValue("Verano       ");
        header.createCell(1).setCellValue("Otoño       ");
        header.createCell(2).setCellValue("Invierno     ");
        header.createCell(3).setCellValue("Primavera     ");
        HSSFRow row = Sheet.createRow(1);
        row.createCell(0).setCellValue(valores[0]);
        row.createCell(1).setCellValue(valores[1]);
        row.createCell(2).setCellValue(valores[2]);
        row.createCell(3).setCellValue(valores[3]);
        guardarArchivo(workBook, FILE);
    }
/**
 * Se encarga de guardar el archivo.
 * @param   workBook representa el archivo actual.
 * @param   titulo representa el titulo que va a tener el archivo.
 * @throws  IOException puede generar un error al cerrar el archivo.
 */
    private static void guardarArchivo(HSSFWorkbook workBook, String titulo) throws IOException {
        try {
            workBook.write(new FileOutputStream(titulo));
        } catch (Exception e) {
        } finally {
            workBook.close();
        }
    }
}
