import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearVistaSQL {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");
            // construir orden Create VIEW
            StringBuilder sql = new StringBuilder();
            sql.append("CREATE OR REPLACE VIEW totales ");
            sql.append("(dep, dnombre, nemp, media) AS ");
            sql.append("SELECT d.dept_no, dnombre, COUNT(emp_no), AVG(salario) ");
            sql.append("FROM departamentos d LEFT JOIN empleados e ");
            sql.append("ON e.dept_no = d.dept_no ");
            sql.append("GROUP BY d.dept_no, dnombre ");
            System.out.println(sql);
            Statement sentencia = conexion.createStatement();
            int filas = sentencia.executeUpdate(sql.toString());
            System.out.printf("Resultado de la ejecución: %d %n", filas);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
