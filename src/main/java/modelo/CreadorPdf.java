/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * La clase CreadorPdf se encarga de 
 * @author Marcos Ramon Caraballo, Angelina María Vialle, Valentin Rebechi, Ian
 * Caraballo.
 * @version 27/10/2024.
 */
import java.io.FileOutputStream;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CreadorPdf {

    private static String FILE = "PruebaPDF.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
/**
 * Se encarga de crear un documento .pdf con un titulo y un ArrayList de 
 * reservas.
 * Se guarda en la carpeta PDFsExportados.
 * @param   titulo  representa el titulo del documento.
 * @param   reservas representa las reservas que conforman el reporte que va a 
 * ser exportado a pdf.
 * @throws  IOException ocurre cuando sucede un error al crear el directorio o 
 * al escribir en el archivo.
 */
    public static void hacerPdfReserva(String titulo, 
            ArrayList<Reserva> reservas) throws IOException {
        FILE = "PDFsExportados/" + titulo + ".pdf";
        
        try {
            Files.createDirectories(Paths.get("ExcelExportados"));
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document, titulo);
            createTableReservas(document, reservas);

            document.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
/**
 * Se encarga de crear un documento .pdf con un titulo y un ArrayList de 
 * clientes.
 * Se guarda en la carpeta PDFsExportados.
 * @param   titulo representa el tirulo del documento.
 * @param   clientes representa los clientes que conforman el reporte que sera 
 * exportado.
 * @throws  IOException IOException ocurre cuando sucede un error al crear el directorio o 
 * al escribir en el archivo.
 */
    public static void hacerPdfCliente(String titulo, ArrayList<Cliente> clientes) throws IOException {
        FILE = "PDFsExportados/" + titulo + ".pdf";
        Files.createDirectories(Paths.get("ExcelExportados"));
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document, titulo);
            createTableClientes(document, clientes);
            document.close();
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }
/**
 * Se encarga de ponerle un titulo en los metadatos.
 * @param   document representa el archivo que estamos creando.
 */
    private static void addMetaData(Document document) {
        document.addTitle("Reporte");
    }
/**
 * Se encarga de agregarle un titulo al inicio del documento.
 * @param   document representa el archivo que estamos creando.
 * @param   titulo representa el titulo que va a llevar el archivo.
 * @throws  DocumentException ocurre cuando hay un error al añadir el titulo al 
 * documento.
 */
    private static void addTitlePage(Document document, String titulo) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(titulo, catFont));
        addEmptyLine(preface, 3);
        document.add(preface);
    }
/**
 * Se encarga de crear la tabla de reservas
 * @param   document representa el documento en donde estamos trabajando.
 * @param   reservas representa todas las reservas que conforman el reporte.
 * @throws  DocumentException  ocurre cuando hay un error al añadir la tabla al 
 * documento.
 */
    private static void createTableReservas(Document document,
            ArrayList<Reserva> reservas) throws DocumentException {
        PdfPTable table = new PdfPTable(7);
        table.setWidths(new int[]{4, 6, 5, 6, 6, 9, 8});
        table.setWidthPercentage(100);
        PdfPCell c1 = new PdfPCell(new Phrase("Mesa"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Fecha"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Hora"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Ocupación"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Finalización"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Cliente"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Comensales"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        for (Reserva res : reservas) {
            String auxHoraInicio = "";
            String auxHoraFin = "";
            if (res.getTiempoOcupacion() != null) {
                auxHoraInicio = res.getTiempoOcupacion()
                        .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
            if (res.getTiempoFinalizacion() != null) {
                auxHoraFin = res.getTiempoFinalizacion()
                        .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
            Object[] o = {
                res.getMesa().getNumero(),
                res.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                res.getHora().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                auxHoraInicio,
                auxHoraFin,
                res.getCliente().getNombre(),
                res.getMesa().getCapacidad()
            };
            for (Object ob : o) {
                table.addCell(String.valueOf(ob));
            }
        }

        document.add(table);

    }
    
/**
 * Se encarga de crear la tabla de clientes
 * @param   document representa el documento donde estamos trabajando.
 * @param   clientes representa todos los clientes que conforman el reporte. 
 * @throws  DocumentException  ocurre cuando hay un error al añadir la tabla al
 * documento.
 */
    private static void createTableClientes(Document document,
            ArrayList<Cliente> clientes) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{3, 3, 3});
        table.setWidthPercentage(100);
        PdfPCell c1 = new PdfPCell(new Phrase("Nombre"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Telefono"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Correo"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        for (Cliente c : clientes) {

            Object[] o = {
                c.getNombre(),
                c.getTelefono(),
                c.getCorreo()
            };
            for (Object ob : o) {
                table.addCell(String.valueOf(ob));
            }
        }

        document.add(table);

    }

/**
 * Se encarga de agregar lineas vacias a los parrafors del documento.
 * @param   paragraph representa el parrafo.
 * @param   number representa la cantidad de espacios.
 */    
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
/**
 * 
 * @param titulo
 * @param valores
 * @throws IOException 
 */
    public static void hacerPdfEstaciones(String titulo, String[] valores) throws IOException {
        FILE = "PDFsExportados/" + titulo + ".pdf";
        Files.createDirectories(Paths.get("ExcelExportados"));

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document, titulo);
            createTableEstaciones(document, valores);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Se encarga de crear una tabla, 
     * @param   document
     * @param   valores
     * @throws  DocumentException 
     */
    private static void createTableEstaciones(Document document, String[] valores) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidths(new int[]{3, 3, 3, 3});
        table.setWidthPercentage(100);
        PdfPCell c1 = new PdfPCell(new Phrase("Verano"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Otoño"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Invierno"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Primavera"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        for (String v : valores) {
            table.addCell(v);
        }
        document.add(table);
    }
}
