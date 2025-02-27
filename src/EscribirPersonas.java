import java.io.*;
import com.thoughtworks.xstream.XStream;

public class EscribirPersonas {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File fichero = new File("FichPersona.dat");
        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        System.out.println("Comienza el proceso...");
        // Creando el objeto Lista de Personas
        ListaPersonas listaPersons = new ListaPersonas();
        try {
            while (true) {
                Persona persona = (Persona) dataIS.readObject();
                listaPersons.add(persona);
            }
        } catch (EOFException eo) {
        }
        dataIS.close();

        try {
            XStream xstream = new XStream();
            // cambiar el nombre de las etiquetas
            xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
            xstream.alias("DatosPerson", Persona.class);
            xstream.addImplicitCollection(ListaPersonas.class, "lista");
            xstream.toXML(listaPersons, new FileOutputStream("Personas-xstream.xml"));
            System.out.println("Creando fichero...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
