import java.sql.*;

public class ClavePrimaria {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo?serverTimezone=Europe/Madrid", "anchanDB", "AnchanDB2025");
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet pk = dbmd.getPrimaryKeys(null, "ejemplo", "departamentos");
            String pkDep = "", separador="";
            while (pk.next()) {
                pkDep = pkDep + separador + pk.getString("COLUMN_NAME");
                separador = "+";
            }
            System.out.println("Clave primaria: " + pkDep);
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }
}
