import java.sql.*;

public class PreparedStatement {
    public static void main(String[] args) {
        String dep = args[0];
        String oficio = args[1];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");
            // Construir orden INSERT
            String sql = "SELECT apellido, salario FROM empleados WHERE dept_no = ? AND oficio = ? ORDER BY 1";
            // Preparamos la sentencia
            java.sql.PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, Integer.parseInt(dep));
            sentencia.setString(2, oficio);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                System.out.printf("%s => %d %n", rs.getString("apellido"), rs.getInt("salario"));
            }
            rs.close();
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
