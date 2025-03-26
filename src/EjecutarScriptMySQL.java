import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EjecutarScriptMySQL {

    public static void main(String[] args) {
        File scriptFile = new File("scriptmysql.sql");
        System.out.println("\n\n Fichero de consulta: " + scriptFile.getName());
        System.out.println("Convirtiendo fichero a cadena...");
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(scriptFile));
        } catch (FileNotFoundException e) {
            System.out.println("Error: no hay archivo: " + e.getMessage());
        }
        String linea = null;
        StringBuilder stringBuilder = new StringBuilder();
        String salto = System.getProperty("line.separator");
        try {
            while ((linea = entrada.readLine()) != null) {
                stringBuilder.append(linea);
                stringBuilder.append(salto);
            }
        } catch (IOException e) {
            System.out.println("Error de E/S, al operar: " + e.getMessage());
        }
        String consulta = stringBuilder.toString();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error del Driver: " + e.getMessage());
        }
        try {
            Connection conMysql = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo?allowMultiQueries=true", "root", "Anchan24");
            Statement sents = conMysql.createStatement();
            int res = sents.executeUpdate(consulta);
            System.out.println("Script creado con éxito, res = " + res);
            conMysql.close();
            sents.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el script: " + e.getMessage());
        }
    }
}
