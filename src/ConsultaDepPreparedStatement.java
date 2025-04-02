import java.sql.*;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ConsultaDepPreparedStatement {
    public static void main(String[] args) {
        if (args.length !=1 ) {
            System.out.println("Error: debes proporcionar el número de departamento como argumento.");
            return;
        }

        String dep = args[0];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");

            // Verificar el departamento
            String sqlVerificar = "SELECT dnombre FROM departamentos WHERE dept_no = ?";
            PreparedStatement sentenciaVerifivar = conexion.prepareStatement(sqlVerificar);
            sentenciaVerifivar.setInt(1, Integer.parseInt(dep));
            ResultSet resultDep = sentenciaVerifivar.executeQuery();

            if (!resultDep.next()){
                System.out.println("Error: No existe el departamento.");
                resultDep.close();
                sentenciaVerifivar.close();
                return;
            }
            String nombreDepartamento = resultDep.getString("dnombre");
            resultDep.close();
            sentenciaVerifivar.close();

            // Construir orden PreparedStatement
            String sqlEmpleados = "SELECT apellido, salario, oficio FROM empleados WHERE dept_no = ?";
            // Preparamos la sentencia
            PreparedStatement sentenciaEmpleados = conexion.prepareStatement(sqlEmpleados);
            sentenciaEmpleados.setInt(1, Integer.parseInt(dep));
            ResultSet rsEmpleados = sentenciaEmpleados.executeQuery();
            // Formato para el salario
            DecimalFormat formatoSalario = new DecimalFormat("##,###.00", new DecimalFormatSymbols(Locale.getDefault()));


            System.out.println("============================================");
            System.out.printf("DEPARTAMENTO: %s ==> %s %n", dep, nombreDepartamento);
            System.out.println("============================================");

            double sumaSalarios = 0;
            int numeroEmpleados = 0;

            while (rsEmpleados.next()) {
                float salario = rsEmpleados.getFloat("salario");
                sumaSalarios += salario;
                numeroEmpleados ++;
                String salarioFormateado = formatoSalario.format(salario);
                System.out.printf("%s, %s, %s %n", rsEmpleados.getString("apellido"), salarioFormateado, rsEmpleados.getString("oficio"));
            }
            System.out.println("-----------------------------------------------");

            if (numeroEmpleados > 0) {
                double salarioMedio = sumaSalarios / numeroEmpleados;
                System.out.printf("Salario medio: %s%n", formatoSalario.format(salarioMedio));
                System.out.printf("Número de empleados: %d%n", numeroEmpleados);
            } else {
                System.out.println("No hay empleados en este departamento.");
            }

            rsEmpleados.close();
            sentenciaEmpleados.close();
            conexion.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Error: no se pudo cargar el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error, número de departamento no válido");
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Error: Deber proporcional el número de departamento como argumento.");
        }
    }
}
