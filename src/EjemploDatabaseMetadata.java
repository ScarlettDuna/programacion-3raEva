import java.sql.*;

public class EjemploDatabaseMetadata {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establecemos conexión a la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo?serverTimezone=Europe/Madrid", "anchanDB", "AnchanDB2025");
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet result = null;
            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
            System.out.println("=====================================");
            System.out.printf("Nombre: %s %n", nombre);
            System.out.printf("Driver: %s %n", driver);
            System.out.printf("URL: %s %n", url);
            System.out.printf("Usuario: %s %n", usuario);

            // Obtener información de las tablas y vistas que hay
            result = dbmd.getTables("ejemplo", null, null, null);
            while (result.next()) {
                String catalogo = result.getString(1); // columna 1
                String esquema = result.getString(2); // columna 2
                String tabla = result.getString(3);
                String tipo = result.getString(4);
                System.out.printf("%s - Catagolo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo, esquema, tabla);
            }
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
