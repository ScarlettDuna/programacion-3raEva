import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class IndMasCorp extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    JTextField i_estatura = new JTextField(10);
    JTextField i_peso = new JTextField(10);
    JTextField m_estatura = new JTextField(10);
    JTextField m_peso = new JTextField(10);

    JLabel l_iest = new JLabel("Estatura en pulgadas");
    JLabel l_ipeso = new JLabel("Peso en libras");
    JLabel l_mest = new JLabel("Estatura en centímetros");
    JLabel l_mpeso = new JLabel("Peso en kilogramos");

    JButton button_ingles = new JButton("Calcular unidades en sistema imperial");
    JButton button_imc = new JButton("Calcula índice de masa corporal");

    JLabel l_resultado = new JLabel("Resultado");
    JTextField resultado_text = new JTextField(10);

    public IndMasCorp() {
        setTitle("Cálculo de índice de masa corporal");
        // Establece el tamaño de la ventana
        setSize(1200, 1600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Superior para el Título General
        JPanel panelTituloGeneral = new JPanel();
        JLabel mainTitle = new JLabel("Para calcular su índice de masa corporal, ingrese su estatura y peso.");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 16));
        panelTituloGeneral.add(mainTitle);
        add(panelTituloGeneral, BorderLayout.NORTH);

        // Panel Central para los Sistemas de Entrada (inglés y métrico)
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(1, 2, 10, 10)); // Una fila, dos columnas, con espaciado

        // Panel para el Sistema Inglés
        JPanel panelIngles = new JPanel();
        panelIngles.setLayout(new BoxLayout(panelIngles, BoxLayout.Y_AXIS)); // Apilar verticalmente
        panelIngles.setBorder(BorderFactory.createTitledBorder("Sistema inglés")); // Borde con título
        panelIngles.add(l_iest);
        panelIngles.add(i_estatura);
        panelIngles.add(l_ipeso);
        panelIngles.add(i_peso);
        // Alinear componentes al centro
        l_iest.setAlignmentX(Component.CENTER_ALIGNMENT);
        i_estatura.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_ipeso.setAlignmentX(Component.CENTER_ALIGNMENT);
        i_peso.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel para el Sistema Métrico
        JPanel panelMetrico = new JPanel();
        panelMetrico.setLayout(new BoxLayout(panelMetrico, BoxLayout.Y_AXIS)); // Apilar verticalmente
        panelMetrico.setBorder(BorderFactory.createTitledBorder("Sistema métrico")); // Borde con título
        panelMetrico.add(l_mest);
        panelMetrico.add(m_estatura);
        panelMetrico.add(l_mpeso);
        panelMetrico.add(m_peso);
        // Alinear componentes al centro
        l_mest.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_estatura.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_mpeso.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_peso.setAlignmentX(Component.CENTER_ALIGNMENT);


        panelCentral.add(panelIngles);
        panelCentral.add(panelMetrico);
        add(panelCentral, BorderLayout.CENTER);


        // Panel para Botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centrar botones con espacio
        panelBotones.add(button_ingles);
        panelBotones.add(button_imc);
        add(panelBotones, BorderLayout.SOUTH);

        // Panel para Resultado
        JPanel panelResultado = new JPanel();
        panelResultado.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelResultado.add(l_resultado);
        panelResultado.add(resultado_text);
        // Hacer el campo de resultado no editable
        resultado_text.setEditable(false);
        // Ajustar el tamaño de la barra verde del ejemplo
        resultado_text.setPreferredSize(new Dimension(400, 30));
        // Establecer un color de fondo
        resultado_text.setBackground(new Color(153, 204, 0));

        JPanel southPanelWrapper = new JPanel(new BorderLayout());
        southPanelWrapper.add(panelBotones, BorderLayout.NORTH);
        southPanelWrapper.add(panelResultado, BorderLayout.SOUTH);
        add(southPanelWrapper, BorderLayout.SOUTH);


        // Añadir listeners a los botones
        button_ingles.addActionListener(this);
        button_imc.addActionListener(this);

        pack();
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_imc) {
            // Lógica para Calcular IMC (Sistema Métrico)
            try {
                double estaturaCm = Double.parseDouble(m_estatura.getText());
                double pesoKg = Double.parseDouble(m_peso.getText());

                // Validaciones básicas
                if (estaturaCm <= 0 || pesoKg <= 0) {
                    resultado_text.setText("Error: Estatura y peso deben ser positivos.");
                    resultado_text.setBackground(Color.RED);
                    return;
                }

                // Convertir estatura de cm a metros para la fórmula
                double estaturaM = estaturaCm / 100.0;
                double imc = pesoKg / (estaturaM * estaturaM);

                String composicionCorporal = "";
                if (imc < 18.5) {
                    composicionCorporal = "Bajo peso";
                } else if (imc >= 18.5 && imc <= 24.9) {
                    composicionCorporal = "Peso normal";
                } else if (imc >= 25 && imc <= 29.9) {
                    composicionCorporal = "Sobrepeso";
                } else { // imc >= 30
                    composicionCorporal = "Obesidad";
                }
                resultado_text.setText(String.format("Tu IMC es %.2f - %s", imc, composicionCorporal));
                resultado_text.setBackground(new Color(153, 204, 0)); // Volver al verde
            } catch (NumberFormatException ex) {
                resultado_text.setText("Error: Ingresa números válidos en estatura y peso.");
                resultado_text.setBackground(Color.RED);
            }
        }
        // Lógica para Calcular IMC en sistema imperial
        if (e.getSource() == button_ingles) {
            try {
                double estaturaInch = Double.parseDouble(i_estatura.getText());
                double pesoLibras = Double.parseDouble(i_peso.getText());

                // Validaciones básicas
                if (estaturaInch <= 0 || pesoLibras <= 0) {
                    resultado_text.setText("Error: Estatura y peso deben ser positivos.");
                    resultado_text.setBackground(Color.RED);
                    return;
                }

                double imc = (pesoLibras / Math.pow(estaturaInch, 2)) * 703;

                String composicionCorporal = "";
                if (imc < 18.5) {
                    composicionCorporal = "Bajo peso";
                } else if (imc >= 18.5 && imc <= 24.9) {
                    composicionCorporal = "Peso normal";
                } else if (imc >= 25 && imc <= 29.9) {
                    composicionCorporal = "Sobrepeso";
                } else {
                    composicionCorporal = "Obesidad";
                }
                resultado_text.setText(String.format("Tu IMC es %.2f - %s", imc, composicionCorporal));
                resultado_text.setBackground(new Color(153, 204, 0)); // Volver al verde
            } catch (NumberFormatException ex) {
                resultado_text.setText("Error: Ingresa números válidos en estatura y peso.");
                resultado_text.setBackground(Color.RED);
            }
        }
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            IndMasCorp ventana = new IndMasCorp();
            ventana.setVisible(true);
        });
    }
}
