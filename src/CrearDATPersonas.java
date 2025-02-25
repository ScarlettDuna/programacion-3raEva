import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CrearDATPersonas {
    public static void main(String[] args) throws IOException {
        File fichero = new File("Personas1.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");
        // arrays con los datos
        String[] DNIs = {"21155578V","12345678F","87654321T","65498721W", "14725836Q", "14685836G"};
        String[] nombres = {"Pepe","Jose","Eustaquio","Pepa", "Pepito", "Laura"};
        String[] apellidos = {"Suarez","Sanchez","De la Rosa","Algota", "Logrosan", "Gallego"};
        int[] edades = {19, 28, 35, 49, 30, 41};
        Boolean[] estaCasado = {true, false, true, true, false, false};
        StringBuffer buffer = null;
        String[] telefonos = {"98765432","123456789","147258369","369852147", "963852741", "914765239"};
        String[] direcciones = {"Calle Madrid 5","Calle Asturias 8","Calle Toledo 4","Calle Barcelona 6", "Calle Cuenca 13", "Avenida de la Imaginación, 23"};
        int n = apellidos.length; // número de elementos del array
        for (int i = 0; i < n; i++) {
            // DNI
            buffer = new StringBuffer ( DNIs[i]);
            buffer.setLength(10);
            file.writeChars(buffer.toString());
            // Nombre
            buffer = new StringBuffer ( nombres[i]);
            buffer.setLength(15);
            file.writeChars(buffer.toString());
            // Apellido
            buffer = new StringBuffer ( apellidos[i]);
            buffer.setLength(30);
            file.writeChars(buffer.toString());
            file.writeInt(edades[i]);
            file.writeBoolean(estaCasado[i]);
            // Teléfono
            buffer = new StringBuffer ( telefonos[i]);
            buffer.setLength(15);
            file.writeChars(buffer.toString());
            // Dirección
            buffer = new StringBuffer ( direcciones[i]);
            buffer.setLength(60);
            file.writeChars(buffer.toString());
        }
        System.out.println(file.length());
        file.close();

    }
}
