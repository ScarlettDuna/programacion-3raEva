import java.sql.*;

public class AccesoBDMySQL {
    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");
            // Preparar la consulta
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM department";
            ResultSet result = sentencia.executeQuery(sql);
            // Recorremos el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros y se van mostrando
            while (result.next()){
                System.out.printf("%d, %s, %s %n",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3));
            }
            result.close(); // Cerrar ResultSet
            sentencia.close(); // Cerrar Statements
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
