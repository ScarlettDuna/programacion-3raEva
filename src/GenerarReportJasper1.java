import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerarReportJasper1 {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");

            String sql = "SELECT d.dept_no, d.dnombre, d.loc, COUNT(e.emp_no) AS cuenta, round(AVG(coalesce(e.salario, 0)),2) AS media, SUM(coalesce(e.salario, 0)) AS suma FROM departamentos d LEFT JOIN empleados e ON d.dept_no = e.dept_no GROUP BY d.dept_no, d.dnombre, d.loc";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Map<String, Object>> dataList = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("dept_no", resultSet.getInt("dept_no"));
                row.put("dnombre", resultSet.getString("dnombre"));
                row.put("loc", resultSet.getString("loc"));
                row.put("cuenta", resultSet.getInt("cuenta"));
                row.put("media", resultSet.getDouble("media"));
                row.put("suma", resultSet.getDouble("suma"));
                dataList.add(row);
            }

            // Cargar la plantilla
            InputStream reportStream = new FileInputStream(new File("C:\\Users\\Usuario_Mañana\\Documents\\Programación\\3. Tercera_Evaluación\\Entregas\\Entrega2.18\\plantilla.jrxml"));
            JasperDesign jasperDesign = JRXmlLoader.load(reportStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Crear el origen de datos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

            // Parámetros del informe (opcional)
            Map<String, Object> parameters = new HashMap<>();

            // Generar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Exportar el informe (a PDF, por ejemplo)
            JasperExportManager.exportReportToPdfFile(jasperPrint, "reportDepartamentos.pdf");

            System.out.println("Informe generado con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}