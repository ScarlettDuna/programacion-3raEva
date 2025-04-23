import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ejemplo2ventanas extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    JTextField num = new JTextField(10);
    JTextField nombre = new JTextField(25);
    JTextField direc = new JTextField(25);
    JTextField tlf = new JTextField(15);
    JTextField dep = new JTextField(5);

    JLabel mensaje = new JLabel("---------------------");
    JLabel titulo = new JLabel("ENTRADA DE DATOS DE EMPLEADOS:");

    JLabel lnum = new JLabel("Número de empleado");
    JLabel lnom = new JLabel("Nombre del empleado");
    JLabel ldir = new JLabel("Dirección del empleado");
    JLabel ltlf = new JLabel("Teléfono del empleado");
    JLabel ldep = new JLabel("Número de departamento");

    JButton balta = new JButton("Alta de empleado");
    JButton breset = new JButton("Limpiar datos");
    JButton fin = new JButton("Cerrar");
    Color c; // Para poner los colores

    public ejemplo2ventanas() {
        setTitle("Entrada de empleados. Tres.");

        JPanel p0 = new JPanel();
        c = Color.CYAN;
        p0.add(titulo);
        p0.setBackground(c);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(lnum);
        p1.add(num);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(lnom);
        p2.add(nombre);

        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        p3.add(ldir);
        p3.add(direc);

        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        p4.add(ltlf);
        p4.add(tlf);

        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout());
        p5.add(ldep);
        p5.add(dep);

        JPanel p6 = new JPanel();
        p6.setLayout(new FlowLayout());
        c = Color.YELLOW;
        p6.add(balta);
        p6.add(breset);
        p6.add(fin);
        p6.setBackground(c);

        JPanel p7 = new JPanel();
        p7.setLayout(new FlowLayout());
        p1.add(mensaje);

        // Para ver la ventana y colocar los paneles verticalmente
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        // Añadir los paneles al Jframe
        add(p0);add(p1);add(p2);add(p3);add(p4);add(p5);add(p6);add(p7);
        pack(); // hace que se coloquen alineados los elementos de cada JPanel

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Se ejecuta ActionListener al pulsar los botones
        balta.addActionListener(this);
        breset.addActionListener(this);
        fin.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == balta) {
            String nom = nombre.getText();
            String dir = direc.getText();
            mensaje.setText("Has pulsado el botón alta. " +
                    "El nombre tecleado es: " + nom +
                    "Su dirección es: " + dir);
        }
        if (e.getSource() == fin) {
            System.exit(0); // Sale de la aplicación
            // dispose(); // descarga la ventana actual
        }
        if (e.getSource() == breset){
            mensaje.setText("Has pulsado el botón de limpiar...");
            num.setText(" ");
            nombre.setText(" ");
            direc.setText(" ");
            tlf.setText(" ");
            dep.setText(" ");
        }
    }

    public static void main(String[] args) {
        ejemplo2ventanas v = new ejemplo2ventanas();
        v.setVisible(true);
    }
}
