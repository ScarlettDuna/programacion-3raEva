import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.Connection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GenerarReportJasper {
    public static void main(String[] args) {
        String reportSource = "C:\\Users\\Usuario_Mañana\\Documents\\Programación\\3. Tercera_Evaluación\\Entregas\\Entrega2.18\\plantilla.jrxml";
        String reportHTML = "./informes/Informe.html";
        String reportPDF = "./informes/Informe.pdf";
        String reportXML = "./informes/Informe.xml";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("titulo", "LISTADO DE DEPARTAMENTOS.");
        params.put("autor", "ARM");
        params.put("fecha", (new java.util.Date()).toString());

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");
            JasperPrint MiInforme = JasperFillManager.fillReport(jasperReport, params, conn);
            // Visualizar en pantalla
            JasperViewer.viewReport(MiInforme);
            // Convertir a HTML
            JasperExportManager.exportReportToHtmlFile(MiInforme, reportHTML);
            // Convertir a PDF
            JasperExportManager.exportReportToPdfFile(MiInforme, reportPDF);
            // Convertir a XML
            JasperExportManager.exportReportToXmlFile(MiInforme, reportXML, false);
            System.out.println("ARCHIVOS CREADOS");

        } catch (CommunicationsException e) {
            System.out.println("Error de comunicación con la BD. No está arrancada");
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
