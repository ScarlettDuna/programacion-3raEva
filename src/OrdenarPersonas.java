import java.util.Scanner;

public class OrdenarPersonas {
    // Desarrollar la clase OrdenarPersonas, que realice las siguientes operaciones
    // Pedir por consola el nombre y edad de 5 personas
    // Presentar los datos ordenados por edad.

    public static void main(String[] args) {
        // Iniciamos los arrays para almacenar la información inicial
        int[] edadesDesordenadas = new int[5];
        String[] nombresDesordenados = new String[5];

        int[] ordenado  = new int[5];
        // Utilizamos un bucle con un scanner para pedir los datos
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.println("Introduce un nombre");
            nombresDesordenados[i] = sc.next();
            System.out.println("Introduce la edad");
            edadesDesordenadas[i] = sc.nextInt();
        }

    }

}
