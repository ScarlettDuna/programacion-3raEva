import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InsertarEmpleado {
    public static void main(String[] args) {

        //
        // CREATE TABLE `categories` (
        //   `category_id` int(11) AUTO_INCREMENT,
        //   `category_name` varchar(150) DEFAULT NULL,
        //   `remarks` varchar(500) DEFAULT NULL,
        //   PRIMARY KEY (`category_id`)
        // );
        //
        // INSERT INTO categories (category_name, remarks) VALUES ('Electrónica', 'Productos electrónicos y gadgets.');
        // INSERT INTO categories (category_name, remarks) VALUES ('Ropa', 'Indumentaria para todas las edades y estilos.');
        // INSERT INTO categories (category_name) VALUES ('Hogar'); -- Sin remarks
        // INSERT INTO categories (category_id, category_name, remarks) VALUES (10, 'Jardinería', 'Artículos para el cuidado del jardín.'); -- Especificando ID (si no es AUTO_INCREMENT o si quieres un valor específico)

        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");
            // recuperar argumentos de main
            String emp_no = args[0];
            String apellido = args[1];
            String oficio = args[2];
            String dir = args[3];
            String salario = args[4];
            String comision = args[5];
            String dept_no = args[6];

            Statement sentencia = conexion.createStatement();
            // Comprobación de requisitos
            // Existe el departamento
            String sqlDep = String.format("Select * FROM departamentos WHERE dept_no = %s", dept_no);
            ResultSet resultDep = sentencia.executeQuery(sqlDep);
            if (!resultDep.next()) {
                System.out.println("Error: El departamento no existe.");
                resultDep.close();
                return;
            }

            // NO existe ya el número de empleado
            String sqlEmp_no = String.format("Select * FROM empleados WHERE emp_no = %s", emp_no);
            ResultSet resultEmp_no = sentencia.executeQuery(sqlEmp_no);
            if (resultEmp_no.next()) {
                System.out.println("Error: El empleado ya existe.");
                resultEmp_no.close();
                return;
            }

            // Salario es superior a 0
            int salarioInt = Integer.parseInt(salario);
            if (salarioInt < 0) {
                System.out.println("Error: El salario no puede ser inferior a cero.");
                return;
            }

            // Dir existe
            String sqlDir = String.format("Select * FROM empleados WHERE emp_no = %s", dir);
            ResultSet resultDir = sentencia.executeQuery(sqlDir);
            if (!resultDir.next()) {
                System.out.println("Error: El director no existe.");
                resultDir.close();
                return;
            }

            // Apellido y Oficio no son null
            if (apellido == null || apellido.isEmpty() || oficio == null || oficio.isEmpty()) {
                System.out.println("Error: El apellido y el oficio no pueden ser nulos.");
                return;
            }

            //Si todos los requisitos se han cumplido
            // Construir orden "insertar"
            // INSERT INTO empleados VALUES (7369, 'SANCHEZ', 'EMPLEADO', 7902, '1990-10-26', 2500, NULL, 10);
            String sql;
            if (comision.equalsIgnoreCase("NULL")) {
                sql = String.format("INSERT INTO empleados VALUES (%s, '%s', '%s', %s, '%s', %s, NULL, %s);", emp_no, apellido, oficio, dir, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), salario, dept_no);
            } else {
                sql = String.format("INSERT INTO empleados VALUES (%s, '%s', '%s', %s, '%s', %s, %s, %s);", emp_no, apellido, oficio, dir, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), salario, comision, dept_no);
            }
            System.out.println(sql);

            int filas = sentencia.executeUpdate(sql);
            System.out.printf("Filas afectadas: %d %n", filas);

            sentencia.close();
            conexion.close();
        } catch (NumberFormatException | DateTimeParseException | ClassNotFoundException e) {
            System.out.println("Error con los datos");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de SQL.");
            e.printStackTrace();
        }
    }
}
