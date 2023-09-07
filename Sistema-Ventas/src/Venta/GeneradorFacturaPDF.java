package Venta;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import javax.mail.Transport;



public class GeneradorFacturaPDF {

    public static void main(String[] args) {
        String nombreDueno = "Daviel Sanchez";
        String nombreArchivo = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "Factura_" + nombreDueno + ".pdf";

        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();
            
            // Agregar el logo en la esquina superior izquierda
            Image logo = Image.getInstance("src\\imgnew\\ParadaLogo.png");
            logo.scaleToFit(50, 50);
            logo.setAlignment(Element.ALIGN_LEFT);
            document.add(logo);

            // Crear título
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.UNDERLINE);
            Paragraph titulo = new Paragraph("Factura", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            
            // Información de la factura
            document.add(new Paragraph("Número de factura: 12345"));
            document.add(new Paragraph("Fecha: 21 de agosto de 2023"));
            document.add(new Paragraph("Cliente: Daviel Sanchez"));

            // Crear tabla de detalles de productos
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);

            PdfPCell cell;

            cell = new PdfPCell(new Phrase("ID Producto"));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nombre Producto"));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Tamaño"));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Precio"));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Cantidad"));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Costo Total"));
            table.addCell(cell);

            // Agregar productos a la tabla (esto es solo un ejemplo)
            for (int i = 1; i <= 5; i++) {
                table.addCell("ID-" + i);
                table.addCell("Producto " + i);
                table.addCell("Tamaño " + i);
                table.addCell("$" + (i * 10));
                table.addCell(String.valueOf(i));
                table.addCell("$" + (i * 10 * i));
            }

            document.add(table);

            // Crear tabla de totales y detalles de pago
            PdfPTable totalesTable = new PdfPTable(2);
            totalesTable.setWidthPercentage(50);
            totalesTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalesTable.setSpacingBefore(20f);

            cell = new PdfPCell(new Phrase("Total Recibido:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalesTable.addCell(cell);

            cell = new PdfPCell(new Phrase("$100.00"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalesTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Total Devuelto:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalesTable.addCell(cell);

            cell = new PdfPCell(new Phrase("$0.00"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalesTable.addCell(cell);

            document.add(totalesTable);

            document.close();
            writer.close();

            // Abrir la factura automáticamente
            abrirFactura(nombreArchivo);

            // Enviar factura por correo electrónico
            String remitente = "devsamil26@gmail.com";
            String contraseña = "devsamil26";
            String destinatario = "samilq26@gmail.com";
            String asunto = "Factura Adjunta";
            String mensaje = "Estimado cliente, adjuntamos la factura en PDF de su compra.";

            enviarFacturaPorCorreo(remitente, contraseña, destinatario, asunto, mensaje, nombreArchivo);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void abrirFactura(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                if (!archivo.canWrite()) {
                    Desktop.getDesktop().open(archivo);
                } else {
                    Desktop.getDesktop().open(archivo);
                }
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void enviarFacturaPorCorreo(String remitente, String contraseña, String destinatario,
                                              String asunto, String mensaje, String archivoAdjunto) {
        final String host = "smtp.gmail.com";
        final int port = 587;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Iniciar sesión de correo
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, contraseña);
            }
        });

        try {
            // Crear mensaje de correo
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);

            // Crear parte de texto del correo
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(mensaje);

            // Crear parte del archivo adjunto
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(archivoAdjunto);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(new File(archivoAdjunto).getName());

            // Combinar partes en un multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            // Configurar contenido del mensaje
            message.setContent(multipart);

            // Enviar el mensaje
            Transport.send(message);
        }catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
