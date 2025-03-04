import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class Transformer2 {
    public static void main(String[] args) throws IOException {
        String hojaEstilo = "alumnos2Plantilla.xsl";
        String datosAlumnos = "alumnos2.xml";
        File pagHTML = new File("mipagina2.html");

        // crear fichero HTML
        FileOutputStream os = new FileOutputStream(pagHTML);
        Source estilos = new StreamSource(hojaEstilo);
        Source datos = new StreamSource(datosAlumnos);

        // resultado transformacion
        Result result = new StreamResult(os);
        try {
            javax.xml.transform.Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos, result);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        os.close();
    }
}