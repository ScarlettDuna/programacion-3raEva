import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionContenido extends DefaultHandler {
    public GestionContenido() {
        super();
    }
    public void startDocument(){
        System.out.println("Comienzo del Documento XML");
    }
    public void endDocument(){
        System.out.println("Final del documento XML");
    }
    public void startElement(String uri, String nombre, String nombreC, Attributes atts){
        System.out.printf("\t Principio Elemento: %s %n", nombre);
    }
    public void endElement(String uri, String nombre, String nombreC){
        System.out.printf("\t Fin Elemento: %s %n", nombre);
    }
    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
        String car = new String(ch, inicio, longitud);
        car = car.replaceAll("[\t\n]", ""); // quita los saltos de línea
        System.out.printf("\tCaracteres: %s %n", car);
    }
}
