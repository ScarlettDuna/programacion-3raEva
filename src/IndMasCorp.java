import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class IndMasCorp {
    private static final long serialVersionUID = 1L;
    JTextField i_estatura = new JTextField(10);
    JTextField i_peso = new JTextField(10);
    JTextField m_estatura = new JTextField(10);
    JTextField m_peso = new JTextField(10);

    JLabel mensaje = new JLabel("Sistema inglés");
    JLabel titulo = new JLabel("Sistema métrico");

    JLabel l_iest = new JLabel("Estatura en pulgadas");
    JLabel l_ipeso = new JLabel("Peso en libras");
    JLabel l_mest = new JLabel("Estatura en centímetros");
    JLabel l_mpeso = new JLabel("Peso en kilogramos");

    JButton button_ingles = new JButton("Calcular unidades en sistema imperial");
    JButton button_imc = new JButton("Calcula índice de masa corporal");

    JTextField resultado = new JTextField(10);

}
