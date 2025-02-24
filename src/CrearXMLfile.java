import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.*;
import java.io.*;

public class CrearXMLfile {

    static void CrearElemento(String datoEmple, String valor, Element raiz, Document document){
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }
    public static void main(String[] args) throws IOException {
        File file = new File("AleatorioEmple.dat");
        RandomAccessFile fileR = new RandomAccessFile(file, "r");
        int id, dep, posicion=0;
        Double salario;
        char[] apellido = new char[10];
        char aux;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");
            for (;;) { //bucle infinito
                fileR.seek(posicion);
                id = fileR.readInt();
                for (int i = 0; i < apellido.length; i++) {
                    aux = fileR.readChar();
                    apellido[i] = aux;
                }
                String apellidoS = new String(apellido);
                dep = fileR.readInt();
                salario = fileR.readDouble();
                if (id>0) {
                    Element raiz = document.createElement("empleado");
                    document.getDocumentElement().appendChild(raiz);

                    CrearElemento("id", Integer.toString(id), raiz, document);
                    CrearElemento("apellido", apellidoS.trim(), raiz, document);
                    CrearElemento("dep", Integer.toString(dep), raiz, document);
                    CrearElemento("salario", Double.toString(salario), raiz, document);
                }
                posicion += 36;
                if (fileR.getFilePointer() == fileR.length()) break;
            } // fin del for que recorre el fichero
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Empleados.xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        fileR.close();


    }
}
