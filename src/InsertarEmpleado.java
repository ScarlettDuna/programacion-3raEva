import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertarEmpleado {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");
            // recuperar argumentos de main
            String emp_no = args[0];
            String apellido = args[1];
            String oficio = args[2];
            String dir = args[3];
            String fecha_alta = args[4];
            String salario = args[5];
            String comision = args[6];
            String dept_no = args[7];
            // Construir orden "insertar"
            // INSERT INTO empleados VALUES (7369, 'SANCHEZ', 'EMPLEADO', 7902, '1990-10-26', 2500, NULL, 10);
            String sql = String.format("INSERT INTO empleados VALUES (%s, '%s', '%s', %s, '%s', %s, %s, %s);", emp_no, apellido, oficio, dir, fecha_alta, salario, comision, dept_no);
            System.out.println(sql);
            Statement sentencia = conexion.createStatement();
            // Comprobación de requisitos
            // Existe el departamento
            String sqlDep = String.format("Select %s FROM departamentos", dept_no);
            Boolean valorDep = sentencia.execute(sqlDep);

            // NO existe ya el número de empleado
            String sqlEmp_no = String.format("Select %s FROM empleados", emp_no);
            Boolean valorEmp_no = sentencia.execute(sqlDep);

            // Salario es superior a 0
            boolean salario_mayor0 = Integer.parseInt(salario) > 0;

            // Dir existe
            String sqlDir = String.format("Select %s FROM empleados", dir);
            Boolean valorDir = sentencia.execute(sqlDir);

            int filas = sentencia.executeUpdate(sql);
            System.out.printf("Filas afectadas: %d %n", filas);
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
