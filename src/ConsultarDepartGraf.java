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
    private ResultSet rs = null;

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

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JPanel rowCod = new JPanel(new FlowLayout(FlowLayout.LEFT)); // FlowLayout para JLabel y JTextField en la misma línea
        rowCod.add(l_codDepartamento);
        rowCod.add(codDepartamento);
        panelDatos.add(rowCod);

        JPanel rowNom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowNom.add(l_nomDepartamento);
        rowNom.add(nomDepartamento);
        panelDatos.add(rowNom);

        JPanel rowLoc = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowLoc.add(l_localidadDept);
        rowLoc.add(localidadDept);
        panelDatos.add(rowLoc);

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
        // añadir los paneles de los datos y los botones al central
        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);

        try {
            Class.forName("com.mysql.jdbc.Driver"); // Asegúrate de tener el driver JDBC de MySQL
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "Anchan24");

            sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = sentencia.executeQuery("SELECT dept_no, dnombre, loc FROM departamentos ORDER BY dept_no");

            // Muestra el primer registro al iniciar
            if (rs.first()) {
                mostrarRegistro();
            } else {
                // Manejar caso donde no hay departamentos
                System.out.println("No se encontraron departamentos.");
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
    private void mostrarRegistro() throws SQLException {
        codDepartamento.setText(String.valueOf(rs.getInt("dept_no")));
        nomDepartamento.setText(rs.getString("dnombre"));
        localidadDept.setText(rs.getString("loc"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == button_primero) {
                if (rs.first()) {
                    mostrarRegistro();
                } else {
                    JOptionPane.showMessageDialog(this, "Ya estás en el primer departamento.", "Navegación", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (e.getSource() == button_siguiente) {
                if (!rs.isLast()) {
                    if (rs.next()) {
                        mostrarRegistro();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ya estás en el último departamento.", "Navegación", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (e.getSource() == button_anterior) {
                if (!rs.isFirst()) { // Comprueba si NO estamos en el primer registro
                    if (rs.previous()) {
                        mostrarRegistro();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ya estás en el primer departamento.", "Navegación", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (e.getSource() == button_ultimo) {
                if (rs.last()) {
                    mostrarRegistro();
                } else {
                    JOptionPane.showMessageDialog(this, "Ya estás en el último departamento.", "Navegación", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de base de datos durante la navegación: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarDepartGraf ventana = new ConsultarDepartGraf();
            ventana.setVisible(true);
        });
    }
}
