/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 */
import java.io.FileOutputStream;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
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

    public static void hacerPdfReserva(String titulo, ArrayList<Reserva> reservas) throws IOException {
        FILE = "PDFsExportados/" + titulo + ".pdf";
        Files.createDirectories(Paths.get("ExcelExportados"));
        try {
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
            e.printStackTrace();
        }
    }

    private static void addMetaData(Document document) {
        document.addTitle("Reporte");
    }

    private static void addTitlePage(Document document, String titulo) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(titulo, catFont));
        addEmptyLine(preface, 3);
        document.add(preface);
    }

    private static void createTableReservas(Document document, ArrayList<Reserva> reservas) throws BadElementException, DocumentException {
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

    private static void createTableClientes(Document document, ArrayList<Cliente> clientes) throws BadElementException, DocumentException {
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

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

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
