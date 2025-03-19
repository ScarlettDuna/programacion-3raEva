import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ModificarSalario {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");
            // recuperar argumentos de main
            String NewSalario = args[0];
            String emp_no = args[1];
            // Construir orden "insertar"
            String sql = String.format("UPDATE empleados SET salario = %s WHERE emp_no = %s;", NewSalario, emp_no);
            System.out.println(sql);
            Statement sentencia = conexion.createStatement();
            int filas = sentencia.executeUpdate(sql);
            System.out.printf("Filas afectadas: %d %n", filas);
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
