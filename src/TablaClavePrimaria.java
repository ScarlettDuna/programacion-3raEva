import java.sql.*;

public class TablaClavePrimaria {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo?serverTimezone=Europe/Madrid", "anchanDB", "AnchanDB2025");
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet fk = dbmd.getExportedKeys(null, "ejemplo", "departamentos");
            while (fk.next()) {
                String fk_name = fk.getString("FKCOLUMN_NAME");
                String pk_name = fk.getString("PKCOLUMN_NAME");
                String fk_tablename = fk.getString("FKTABLE_NAME");
                String pk_tablename = fk.getString("PKTABLE_NAME");
                System.out.printf("Table PK: %s, Clave Primaria: %s %n", pk_tablename, pk_name);
                System.out.printf("Table FK: %s, Clave Ajena: %s %n", fk_tablename, fk_name);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
