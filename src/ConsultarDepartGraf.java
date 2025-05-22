import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ConsultarDepartGraf extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    // JTextFields para "Código Departamento", "Nombre Departamento", "Localidad Departamento"
    JTextField codDepartamento = new JTextField(10);
    JTextField nomDepartamento = new JTextField(10);
    JTextField localidadDept = new JTextField(10);

    // Declara tus JLabels (para el texto estático) y JButtons como variables de instancia.
    JLabel l_codDepartamento = new JLabel("Código Departamento");
    JLabel l_nomDepartamento = new JLabel("Nombre Departamento");
    JLabel l_localidadDept = new JLabel("Localidad Departamento");

    JButton button_primero = new JButton("Primero");
    JButton button_siguiente = new JButton("Siguiente");
    JButton button_anterior = new JButton("Anterior");
    JButton button_ultimo = new JButton("Último");

    // Variables JDBC
    private Connection conexion = null;
    private Statement sentencia = null;
    private ResultSet rs = null; // ResultSet que se usará para navegar

    public ConsultarDepartGraf(){
        setTitle("Consulta de Departamentos");
        setSize(1200, 1600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Establece un LayoutManager para el JFrame principal (por ejemplo, BorderLayout o GridLayout o BoxLayout).
        setLayout(new BorderLayout());

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        JPanel panelTitulo = new JPanel();
        JLabel titulo = new JLabel("Datos de los departamentos");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);
        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.add(l_codDepartamento);
        panelDatos.add(codDepartamento);
        panelDatos.add(l_nomDepartamento);
        panelDatos.add(nomDepartamento);
        panelDatos.add(l_localidadDept);
        panelDatos.add(localidadDept);
        // Para que no sean editables
        codDepartamento.setEditable(false);
        nomDepartamento.setEditable(false);
        localidadDept.setEditable(false);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(button_primero);
        panelBotones.add(button_siguiente);
        panelBotones.add(button_anterior);
        panelBotones.add(button_ultimo);

        button_primero.addActionListener(this);
        button_siguiente.addActionListener(this);
        button_anterior.addActionListener(this);
        button_ultimo.addActionListener(this);

        pack();
        setLocationRelativeTo(null);

        // ... después de añadir panelBotones al JFrame ...

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Asegúrate de tener el driver JDBC de MySQL
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "Anchan24", "Anchan24");

            sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = sentencia.executeQuery("SELECT dept_no, dnombre, loc FROM dept ORDER BY dept_no");

            // Muestra el primer registro al iniciar
            if (rs.first()) { // Mueve el cursor al primer registro
                mostrarRegistro(); // Llama a un método para mostrar el registro actual
            } else {
                // Manejar caso donde no hay departamentos
                System.out.println("No se encontraron departamentos.");
                // Opcional: deshabilitar botones o mostrar mensaje en la interfaz
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error: No se encontró el driver JDBC de MySQL.", "Error de Driver", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        pack();
        setLocationRelativeTo(null);

    }

    public void actionPerformed(ActionEvent e) {
        // Lógica
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarDepartGraf ventana = new ConsultarDepartGraf();
            ventana.setVisible(true);
        });
    }
}
