import com.thoughtworks.xstream.XStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class LeerPersonas {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        XStream xstream = new XStream();
        xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
        xstream.alias("DatosPerson", Persona.class);
        xstream.addImplicitCollection(ListaPersonas.class, "lista");

        ListaPersonas listadoTodas = (ListaPersonas) xstream.fromXML(new FileInputStream("Personas-xstream.xml"));
        System.out.println("Número de personas: " + listadoTodas.getListaPersonas().size());

        List<Persona> listaPersonas = listadoTodas.getListaPersonas();
        Iterator iterator = listaPersonas.listIterator();
        while (iterator.hasNext()){
            Persona p = (Persona) iterator.next();
            System.out.printf("Nombre: %s, edad %d, %n", p.getNombre(), p.getEdad());
        }
        System.out.println("Fin del listado...");
    }
}
