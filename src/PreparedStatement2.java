import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class PreparedStatement2 {
    public static void main(String[] args) {
        String dep = args[0];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");
            // Construir orden INSERT
            String sql = "SELECT e.apellido, e.salario, e.oficio, d.dnombre FROM empleados e JOIN departamentos d ON e.dept_no = d.dept_no WHERE dept_no = ?";
            // Preparamos la sentencia
            java.sql.PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, Integer.parseInt(dep));
            ResultSet rs = sentencia.executeQuery();
            System.out.println("============================================");
            System.out.printf("DEPARTAMENTO: %s ==> %s", dep, rs.getString("dnombre"));
            System.out.println("============================================");
            while (rs.next()) {
                DecimalFormat format = new DecimalFormat("##,##0.00");
                float valorFormateado = rs.getFloat("salario");
                System.out.printf("%s, %f, %s %n", rs.getString("apellido"), valorFormateado, rs.getString("oficio"));
            }
            System.out.println("-----------------------------------------------");
            System.out.printf("Salario Medio: %d", rs.get);
            rs.close();
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
