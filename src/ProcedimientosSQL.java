import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ProcedimientosSQL {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet proc = dbmd.getProcedures("ejemplo", null, null);
            while (proc.next()) {
                String proc_name = proc.getString("PROCEDURE_NAME");
                String proc_type = proc.getString("PROCEDURE_TYPE");
                System.out.printf("Nombre Procedimiento: %s - Tipo: %s %n", proc_name, proc_type);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
