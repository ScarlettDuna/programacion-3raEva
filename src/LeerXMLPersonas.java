import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class LeerXMLPersonas {
    private static String getNodo(String etiqueta, Element elem) {
        NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node valornodo = (Node) nodo.item(0);
        return valornodo.getNodeValue();
    }

    public static void main(String[] args) throws IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("Personas.xml"));
            document.getDocumentElement().normalize();
            System.out.println("Elemento raíz: " + document.getDocumentElement().getNodeName());
            NodeList personas = document.getElementsByTagName("persona");
            // recorrer la lista
            for (int i  = 0; i < personas.getLength(); i++) {
                Node person = personas.item(i);
                if (person.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) person;
                    System.out.println("DNI: " + getNodo("DNI", elemento));
                    System.out.println("Nombre: " + getNodo("nombre", elemento));
                    System.out.println("Apellido: " + getNodo("apellido", elemento));
                    System.out.println("Edad: " + getNodo("edad", elemento));
                    System.out.println("Casado: " + getNodo("casado", elemento));
                    System.out.println("Teléfono: " + getNodo("telefono", elemento));
                    System.out.println("Dirección: " + getNodo("direccion", elemento));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
