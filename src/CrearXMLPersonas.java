import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.*;
import java.io.*;

public class CrearXMLPersonas {
    static void CrearElemento(String datoEmple, String valor, Element raiz, Document document){
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }
    public static void main(String[] args) throws IOException {
        File file = new File("Personas1.dat");
        RandomAccessFile fileR = new RandomAccessFile(file, "r");
        int edad, posicion=0;
        char[] dni = new char[10];
        char[] nombre = new char[15];
        char[] apellido = new char[30];
        char[] telefono = new char[15];
        char[] direccion = new char[60];
        char aux;
        boolean casado;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Personas", null);
            document.setXmlVersion("1.0");
            for (;;) { //bucle infinito
                fileR.seek(posicion);
                for (int i = 0; i < dni.length; i++) {
                    aux = fileR.readChar();
                    dni[i] = aux;
                }
                String dniF = new String(dni);
                for (int i = 0; i < nombre.length; i++) {
                    aux = fileR.readChar();
                    nombre[i] = aux;
                }
                String nombreF = new String(nombre);
                for (int i = 0; i < apellido.length; i++) {
                    aux = fileR.readChar();
                    apellido[i] = aux;
                }
                String apellidoF = new String(apellido);
                edad = fileR.readInt();
                casado = fileR.readBoolean();
                for (int i = 0; i < telefono.length; i++) {
                    aux = fileR.readChar();
                    telefono[i] = aux;
                }
                String telefonoF = new String(telefono);
                for (int i = 0; i < direccion.length; i++) {
                    aux = fileR.readChar();
                    direccion[i] = aux;
                }
                String direccionF = new String(direccion);

                Element raiz = document.createElement("persona");
                document.getDocumentElement().appendChild(raiz);

                CrearElemento("DNI", dniF.trim(), raiz, document);
                CrearElemento("nombre", nombreF.trim(), raiz, document);
                CrearElemento("apellido", apellidoF.trim(), raiz, document);
                CrearElemento("edad", Integer.toString(edad), raiz, document);
                CrearElemento("casado", Boolean.toString(casado), raiz, document);
                CrearElemento("telefono", telefonoF.trim(), raiz, document);
                CrearElemento("direccion", direccionF.trim(), raiz, document);

                posicion += 265;
                if (fileR.getFilePointer() == fileR.length()) break;
            } // fin del for que recorre el fichero
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Personas.xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        fileR.close();

    }
}
