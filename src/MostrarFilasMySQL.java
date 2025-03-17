import java.sql.*;

public class MostrarFilasMySQL {
    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo?serverTimezone=Europe/Madrid", "anchanDB", "AnchanDB2025");
            // Preparar la consulta
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM departamentos";
            ResultSet result = sentencia.executeQuery(sql);
            // Nos situamos en el último registro
            result.last();
            System.out.println("Número de filas: " + result.getRow());
            // Nos situamos antes del primer registro
            result.beforeFirst();
            while (result.next()){
                System.out.printf("Fila %d: %d, %s, %s %n",
                        result.getRow(),
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3));
            }
            result.close(); // Cerrar ResultSet
            sentencia.close(); // Cerrar Statements
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
