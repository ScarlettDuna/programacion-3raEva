import java.sql.*;

public class EjemploExecuteConsulta {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        // Establecer la conexión con la BD
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");
        // String sql = "SELECT * FROM departamentos";
        String sql = "SELECT * FROM totales";
        Statement sentencia = conexion.createStatement();
        Boolean valor = sentencia.execute(sql);

        if (valor) {
            ResultSet result = sentencia.getResultSet();
            while (result.next()) {
                System.out.printf("número dep: %d, nombre dep: %s, número de empleados: %d, media salario %s %n",
                        result.getInt(1), result.getString(2), result.getInt(3), result.getString(4));
            }
            result.close();
        } else {
            int f = sentencia.getUpdateCount();
            System.out.printf("Filas afectadas: %d %n", f);
        }
        sentencia.close();
        conexion.close();
    }
}
