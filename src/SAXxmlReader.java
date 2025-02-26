import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SAXxmlReader {
    public static void main(String[] args) throws SAXException, IOException, FileNotFoundException {
        XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
        GestionContenido gestor = new GestionContenido();
        procesadorXML.setContentHandler(gestor);
        InputSource fileXML = new InputSource("Empleados.xml");
        procesadorXML.parse(fileXML);
    }
}
