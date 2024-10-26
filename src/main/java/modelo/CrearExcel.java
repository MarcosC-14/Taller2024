/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Uvenk
 */ 
public class CrearExcel {
    private static String FILE = "Excel.xlsx";
    
    public static void hacerExcelReserva(ArrayList<Reserva> reservas) throws Exception {
        FILE = "ExcelExportados/ReservasExcelFile.xlsx";
       
        HSSFWorkbook workBook = new HSSFWorkbook();
       
       HSSFSheet Sheet = workBook.createSheet("Reservas");

    // Crea los valores de las tablas
    Row header = Sheet.createRow(0);
    header.createCell(0).setCellValue("Mesa");
    header.createCell(1).setCellValue("Fecha");
    header.createCell(2).setCellValue("Hora");
    header.createCell(3).setCellValue("Tiempo de Ocupacion");
    header.createCell(4).setCellValue("Tiempo de Finalizacion");
    header.createCell(5).setCellValue("Cliente");
    header.createCell(6).setCellValue("Comensales");
    
    
  
    for(int i = 0; i < reservas.size(); i++){
      
        HSSFRow row = Sheet.createRow((short) i + 1);

        row.createCell(0).setCellValue(reservas.get(i).getMesa().getNumero());

        row.createCell(1).setCellValue(reservas.get(i).getFecha().toString());

        row.createCell(2).setCellValue(reservas.get(i).getHora().toString());
        if(!(reservas.get(i).getTiempoOcupacion()==null)){
            row.createCell(3).setCellValue(reservas.get(i).getTiempoOcupacion().toString());
        }
        
        
        if(!(reservas.get(i).getTiempoFinalizacion()==null)){
          row.createCell(4).setCellValue(reservas.get(i).getTiempoFinalizacion().toString());  
        }
  
        row.createCell(5).setCellValue(reservas.get(i).getCliente().getNombre());
        
        row.createCell(6).setCellValue(reservas.get(i).getMesa().getCapacidad().toString());
    }
   
    // Escribe el archivo
        FileOutputStream fos = new FileOutputStream(FILE);
        workBook.write(fos);
        workBook.close();

    }
    
    
    public static void hacerExcelCliente(ArrayList<Cliente> cliente) throws Exception {
         FILE = "ExcelExportados/ClientesExcelFile.xlsx";
       
        HSSFWorkbook workBook = new HSSFWorkbook();
       
       HSSFSheet Sheet = workBook.createSheet("Reservas");

    // Crea los valores de las tablas
    Row header = Sheet.createRow(0);
    header.createCell(0).setCellValue("Nombre       ");
    header.createCell(1).setCellValue("Telefono       ");
    header.createCell(2).setCellValue("Correo     ");
    
    
    
   
    for(int i = 0; i < cliente.size(); i++){
        
        HSSFRow row = Sheet.createRow((short) i + 1);

        row.createCell(0).setCellValue(cliente.get(i).getNombre());

        row.createCell(1).setCellValue(cliente.get(i).getTelefono());
        
        row.createCell(2).setCellValue(cliente.get(i).getCorreo());
        
    }
   
        // Escribe el archivo
        FileOutputStream fos = new FileOutputStream(FILE);
        workBook.write(fos);
        workBook.close();
        
        
    }
    
    
     
    
}
