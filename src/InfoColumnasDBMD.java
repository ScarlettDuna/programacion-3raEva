import java.sql.*;

public class InfoColumnasDBMD {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        // Establecemos conexión a la BD
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo?serverTimezone=Europe/Madrid", "anchanDB", "AnchanDB2025");
        DatabaseMetaData dbmd = conexion.getMetaData();
        ResultSet columnas = dbmd.getColumns("ejemplo", null, "departamentos", null);
        while (columnas.next()) {
            String nombCol = columnas.getString(4); // "COLUMN_NAME"
            String tipoCol = columnas.getString(6); // "TYPE_NAME"
            String tamCol = columnas.getString(7); //"COLUMN_SIZE"
            String nula = columnas.getString(18); //"IS_NULLABLE"
            System.out.printf("Columna: %s, Tipo: %s, Tamaño: %s, ¿Puede ser nula?: %s. %n", nombCol, tipoCol, tamCol, nula);

        }
    }
}
