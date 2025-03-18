import java.sql.*;

public class EjemploExecute {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        // Establecer la conexión con la BD
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");
        // String sql = "SELECT * FROM departamentos";
        String sql = "UPDATE departamentos SET dnombre = 'INVESTIGACION' WHERE dept_no = 20";
        Statement sentencia = conexion.createStatement();
        Boolean valor = sentencia.execute(sql);

        if (valor) {
            ResultSet result = sentencia.getResultSet();
            while (result.next()) {
                System.out.printf("%d, %s, %s %n",
                        result.getInt(1), result.getString(2), result.getString(3));
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
