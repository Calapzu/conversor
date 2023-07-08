package org.conversor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


public class Ventana extends JFrame implements ActionListener, ItemListener, KeyListener {

    Validaciones v = new Validaciones();
    Listas listas = new Listas();
    MonedaApiClient apiClient = new MonedaApiClient();
    ConvertidorMonedaUtil convertidorMonedaUtil = new ConvertidorMonedaUtil();
    OperacionTemperaturas operacionTemperaturas = new OperacionTemperaturas();

    ImageIcon iconobtnIr = new ImageIcon("src/img/ir.png");
    ImageIcon iconobtnConvertir = new ImageIcon("src/img/convertir.png");
    ImageIcon iconobtnLimpiar = new ImageIcon("src/img/limpiar.png");
    ImageIcon iconobtnAtras = new ImageIcon("src/img/atras.png");

    JPanel pt, pNorte, pCentro, pSur, pCentro1, pCentro2, ptC, pNorteC, pCentroC, pSurC, pCentro1C, pCentro2C;
    JButton botonIr, botonConvertir, botonLimpiar, botonAtras;
    JTextField textCantidadMonedaActual;
    JComboBox comboDeMoneda, comboAMoneda, comboDeTemperatura, comboATemperatura;
    ButtonGroup bg;
    JRadioButton radConversorMoneda, radConversorTemperatura;

    TitledBorder tValor, tDe, tA;
    Border borblack, borred;

    float parsear = 0;

    double temperatura = 0, resultado;

    boolean bandera;

    String monedaActual = "", monedaConvertir = "", codigoActual = "", codigoConvertir = "";
    BigDecimal cantidad, rate, result;

    Ventana() {
        setTitle("Conversor");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        agregarComponentes();
    }

    private void agregarComponentes() {
        setSize(350, 150);

        pt = new JPanel();
        pt.setLayout(new BoxLayout(pt, BoxLayout.Y_AXIS));
        pt.setBackground(Color.white);

        pNorte = new JPanel();
        pNorte.setLayout(new BoxLayout(pNorte, BoxLayout.Y_AXIS));
        pNorte.setBackground(Color.white);

        pCentro = new JPanel();
        pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
        pCentro.setBackground(Color.white);

        pSur = new JPanel();
        pSur.setBackground(Color.white);

        pCentro1 = new JPanel();
        pCentro1.setLayout(new BoxLayout(pCentro1, BoxLayout.X_AXIS));
        pCentro1.setBackground(Color.RED);

        pCentro2 = new JPanel();
        pCentro2.setLayout(new BoxLayout(pCentro2, BoxLayout.X_AXIS));
        pCentro2.setBackground(Color.RED);

        bg = new ButtonGroup();
        radConversorMoneda = new JRadioButton("Conversor de moneda");
        radConversorMoneda.setFont(new Font("", Font.PLAIN, 20));
        radConversorMoneda.setBackground(Color.white);
        radConversorMoneda.addActionListener(this);

        radConversorTemperatura = new JRadioButton("Conversor de temperatura");
        radConversorTemperatura.setFont(new Font("", Font.PLAIN, 20));
        radConversorTemperatura.setBackground(Color.white);
        radConversorTemperatura.addActionListener(this);

        bg.add(radConversorMoneda);
        bg.add(radConversorTemperatura);

        botonIr = new JButton("Ir");
        botonIr.setIcon(new ImageIcon(iconobtnIr.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        botonIr.addActionListener(this);

        add(pt);

        pt.add(pNorte, BorderLayout.NORTH);
        pNorte.add(radConversorMoneda);

        pt.add(pCentro, BorderLayout.CENTER);
        pCentro.add(radConversorTemperatura);

        pt.add(pSur, BorderLayout.SOUTH);
        pSur.add(botonIr);
    }

    private void componentesConversor() {
        setSize(850, 200);
        tValor = new TitledBorder("Valor");
        tDe = new TitledBorder("De");
        tA = new TitledBorder("A");

        borblack = BorderFactory.createLineBorder(Color.BLACK);

        ptC = new JPanel();
        ptC.setLayout(new BoxLayout(ptC, BoxLayout.Y_AXIS));

        pNorteC = new JPanel();
        pNorteC.setLayout(new BoxLayout(pNorteC, BoxLayout.Y_AXIS));
        pNorteC.setBackground(Color.ORANGE);

        pCentroC = new JPanel();
        pCentroC.setLayout(new BoxLayout(pCentroC, BoxLayout.Y_AXIS));

        pSurC = new JPanel();
        pSurC.setBackground(Color.white);

        pCentro1C = new JPanel();
        pCentro1C.setLayout(new BoxLayout(pCentro1C, BoxLayout.X_AXIS));
        pCentro1C.setBackground(Color.RED);

        pCentro2C = new JPanel();
        pCentro2C.setLayout(new BoxLayout(pCentro2C, BoxLayout.X_AXIS));
        pCentro2C.setBackground(Color.RED);


        textCantidadMonedaActual = new JTextField();
        textCantidadMonedaActual.setHorizontalAlignment(JTextField.CENTER);
        textCantidadMonedaActual.setFont(new Font("", Font.PLAIN, 22));
        textCantidadMonedaActual.setBorder(BorderFactory.createTitledBorder(borblack, tValor.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        textCantidadMonedaActual.addKeyListener(this);

        comboDeMoneda = new JComboBox(listas.listaMonedas().toArray());
        comboDeMoneda.setFont(new Font("", Font.PLAIN, 18));
        ((JLabel) comboDeMoneda.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        comboDeMoneda.setBorder(BorderFactory.createTitledBorder(borblack, tDe.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        comboDeMoneda.setBackground(Color.white);
        comboDeMoneda.addItemListener(this);

        comboAMoneda = new JComboBox(listas.listaMonedas().toArray());
        comboAMoneda.setFont(new Font("", Font.PLAIN, 18));
        ((JLabel) comboAMoneda.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        comboAMoneda.setBorder(BorderFactory.createTitledBorder(borblack, tA.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        comboAMoneda.setBackground(Color.white);
        comboAMoneda.addItemListener(this);

        comboDeTemperatura = new JComboBox(listas.listaTemperaturas().toArray());
        comboDeTemperatura.setFont(new Font("", Font.PLAIN, 18));
        ((JLabel) comboDeTemperatura.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        comboDeTemperatura.setBorder(BorderFactory.createTitledBorder(borblack, tDe.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        comboDeTemperatura.setBackground(Color.white);
        comboDeTemperatura.setVisible(false);
        comboDeTemperatura.addItemListener(this);

        comboATemperatura = new JComboBox(listas.listaTemperaturas().toArray());
        comboATemperatura.setFont(new Font("", Font.PLAIN, 18));
        ((JLabel) comboATemperatura.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        comboATemperatura.setBorder(BorderFactory.createTitledBorder(borblack, tDe.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        comboATemperatura.setBackground(Color.white);
        comboATemperatura.setVisible(false);
        comboATemperatura.addItemListener(this);

        botonConvertir = new JButton("Convertir");
        botonConvertir.setIcon(new ImageIcon(iconobtnConvertir.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        botonConvertir.addActionListener(this);

        botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setIcon(new ImageIcon(iconobtnLimpiar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        botonLimpiar.setEnabled(false);
        botonLimpiar.addActionListener(this);

        botonAtras = new JButton("Atras");
        botonAtras.setIcon(new ImageIcon(iconobtnAtras.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        botonAtras.addActionListener(this);

        add(ptC);

        ptC.add(pNorteC, BorderLayout.NORTH);
        pNorteC.add(textCantidadMonedaActual);


        ptC.add(pCentroC, BorderLayout.CENTER);
        pCentroC.add(pCentro1C);

        pCentroC.add(pCentro2C);
        pCentro2C.add(comboDeMoneda);
        pCentro2C.add(comboAMoneda);
        pCentro2C.add(comboDeTemperatura);
        pCentro2C.add(comboATemperatura);


        ptC.add(pSurC, BorderLayout.SOUTH);
        pSurC.add(botonConvertir);
        pSurC.add(botonLimpiar);
        pSurC.add(botonAtras);
    }


    public void validarVacios() {
        v.validarText(textCantidadMonedaActual, tValor);
        v.validarCombos(comboDeMoneda, tDe);
        v.validarCombos(comboAMoneda, tA);
        v.validarCombos(comboDeTemperatura, tDe);
        v.validarCombos(comboATemperatura, tA);
    }

    public void separarPalabras(List<String> lista, JComboBox de, JComboBox a) {
        monedaActual = lista.get(de.getSelectedIndex());
        monedaConvertir = lista.get(a.getSelectedIndex());

        String[] arreglo1 = monedaActual.split(" ");
        String[] arreglo2 = monedaConvertir.split(" ");

        codigoActual = arreglo1[0];
        codigoConvertir = arreglo2[0];
    }

    public void limpiarComponentes() {
        textCantidadMonedaActual.setText(null);
        textCantidadMonedaActual.setEditable(true);
        comboDeMoneda.setSelectedIndex(0);
        comboAMoneda.setSelectedIndex(0);
        comboDeTemperatura.setSelectedIndex(0);
        comboATemperatura.setSelectedIndex(0);
        botonLimpiar.setEnabled(false);
        botonConvertir.setEnabled(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (botonIr == e.getSource()) {
            pt.setVisible(false);
            componentesConversor();
            if (radConversorTemperatura.isSelected()) {
                comboAMoneda.setVisible(false);
                comboDeMoneda.setVisible(false);
                comboATemperatura.setVisible(true);
                comboDeTemperatura.setVisible(true);
                bandera = true;
            }
        }

        if (botonConvertir == e.getSource()) {
            if (bandera) {
                validarVacios();
                separarPalabras(listas.listaTemperaturas(), comboDeTemperatura, comboATemperatura);
                if (comboDeTemperatura.getSelectedIndex()==comboATemperatura.getSelectedIndex()){
                    JOptionPane.showMessageDialog(null, "ERROR, no puede escoger la misma" +
                            " temperatura");
                }else{
                    try {
                        temperatura = Double.parseDouble(textCantidadMonedaActual.getText());
                        resultado = operacionTemperaturas.convertirTemperatura(codigoActual, codigoConvertir, temperatura);

                        textCantidadMonedaActual.setText(resultado + " " + codigoConvertir + "°");
                        textCantidadMonedaActual.setEditable(false);

                        JOptionPane.showMessageDialog(null, "La conversion es: " + resultado);
                    } catch (NumberFormatException exception) {
                        System.out.println(exception.getMessage());
                    }
                }

            } else {
                validarVacios();
                separarPalabras(listas.listaMonedas(), comboDeMoneda, comboAMoneda);
                tValor.setTitle("Valor moneda");
                if (comboDeMoneda.getSelectedIndex() == comboAMoneda.getSelectedIndex()){
                    JOptionPane.showMessageDialog(null, "ERROR, no puede escoger la misma" +
                            " moneda");
                }else{
                    try {
                        parsear = Float.parseFloat(textCantidadMonedaActual.getText());
                        cantidad = BigDecimal.valueOf(parsear);

                        rate = apiClient.obtenerTipoDeCambio(codigoActual, codigoConvertir);
                        result = convertidorMonedaUtil.convertirMoneda(cantidad, rate);

                        result = result.setScale(2, RoundingMode.HALF_UP);

                        textCantidadMonedaActual.setText(result + "$");
                        textCantidadMonedaActual.setEditable(false);

                        JOptionPane.showMessageDialog(null, "La conversion es: " + result);
                    } catch (NumberFormatException exception) {
                        System.out.println(exception.getMessage());
                    }
                }

            }
            botonLimpiar.setEnabled(true);
            botonConvertir.setEnabled(false);
        }

        if (botonLimpiar == e.getSource()) {
            limpiarComponentes();
        }

        if (botonAtras == e.getSource()){
            setSize(350, 150);
            ptC.setVisible(false);
            pt.setVisible(true);
        }
    }


    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        v.validarNumeros(e, textCantidadMonedaActual);

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
