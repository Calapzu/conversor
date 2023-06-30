package org.conversor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class Ventana extends JFrame {

    JPanel pt, pNorte, pCentro, pSur, pCentro1, pCentro2;
    JButton botonRegistrar, botonLimpiar;
    JTextField textCantidadMonedaActual, textCantidadMonedaConvertir;
    JComboBox comboMonedaActual, comboMonedaAConvertir;

    TitledBorder tMonedaActual, tMonedaConvertir;
    Border borblack, borred;

    int ancho = 500;
    int alto = 500;

    Ventana() {
        setTitle("Converso de moneda");
        setSize(ancho, alto);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        agregarComponentes();
    }

    private void agregarComponentes() {

        tMonedaActual = new TitledBorder("Desde la moneda");
        tMonedaConvertir = new TitledBorder("A la moneda");

        borblack = BorderFactory.createLineBorder(Color.BLACK);

        pt = new JPanel();
        pt.setLayout(new BoxLayout(pt, BoxLayout.Y_AXIS));

        pNorte = new JPanel();
        pNorte.setBackground(Color.ORANGE);
        //pNorte.setBorder(BorderFactory.createTitledBorder(borblack, "", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));

        pCentro = new JPanel();
        pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
        pCentro.setBackground(Color.BLACK);
        //pCentro.setBorder(BorderFactory.createTitledBorder(borblack, "", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
       //pCentro.setPreferredSize(new DimensionUIResource(ancho, 627));

        pSur = new JPanel();
        pSur.setBackground(Color.BLACK);
        //pSur.setBorder(BorderFactory.createTitledBorder(borblack, "", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));

        pCentro1 = new JPanel();
        pCentro1.setLayout(new BoxLayout(pCentro1, BoxLayout.X_AXIS));
        pCentro1.setBackground(Color.RED);

        pCentro2 = new JPanel();
        pCentro2.setLayout(new BoxLayout(pCentro2, BoxLayout.X_AXIS));
        pCentro2.setBackground(Color.RED);


        textCantidadMonedaActual = new JTextField();
        textCantidadMonedaActual.setHorizontalAlignment(JTextField.CENTER);
        textCantidadMonedaActual.setFont(new Font("", Font.PLAIN, 22));
        textCantidadMonedaActual.setBorder(BorderFactory.createTitledBorder(borblack, tMonedaActual.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        //textCantidadMonedaActual.addKeyListener(this);

        textCantidadMonedaConvertir = new JTextField();
        textCantidadMonedaConvertir.setHorizontalAlignment(JTextField.CENTER);
        textCantidadMonedaConvertir.setFont(new Font("", Font.PLAIN, 22));
        textCantidadMonedaConvertir.setBorder(BorderFactory.createTitledBorder(borblack, tMonedaConvertir.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        //textCantidadMonedaActual.addKeyListener(this);

        String[] monedaActual = {"     ---------",
                "Tarjeta de Identidad",
                "Cedula de Ciudadania",
                "Cedula de Extranjeria"};
        comboMonedaActual = new JComboBox(monedaActual);
        comboMonedaActual.setFont(new Font("", Font.PLAIN, 18));
        ((JLabel) comboMonedaActual.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        comboMonedaActual.setBorder(BorderFactory.createTitledBorder(borblack, tMonedaActual.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        //comboMonedaActual.addItemListener(this);

        String[] monedaAConvertir = {"     ---------",
                "Tarjeta de Identidadd",
                "Cedula de Ciudadania",
                "Cedula de Extranjeria"};
        comboMonedaAConvertir = new JComboBox(monedaAConvertir);
        comboMonedaAConvertir.setFont(new Font("", Font.PLAIN, 18));
        ((JLabel) comboMonedaAConvertir.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        comboMonedaAConvertir.setBorder(BorderFactory.createTitledBorder(borblack, tMonedaConvertir.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        //comboMonedaAConvertir.addItemListener(this);

        botonRegistrar = new JButton("Registrar");
        //botonRegistrar.addActionListener(this);

        botonLimpiar = new JButton("Limpiar");
        //botonLimpiar.addActionListener(this);
        botonLimpiar.setEnabled(false);

        add(pt);

        pt.add(pNorte, BorderLayout.NORTH);
        pNorte.add(comboMonedaActual);

        pt.add(pCentro, BorderLayout.CENTER);
        pCentro.add(pCentro1);
        pCentro1.add(textCantidadMonedaActual);
        pCentro1.add(textCantidadMonedaConvertir);

        pCentro.add(pCentro2);
        pCentro2.add(comboMonedaActual);
        pCentro2.add(comboMonedaAConvertir);


        pt.add(pSur, BorderLayout.SOUTH);
        pSur.add(botonRegistrar);
        pSur.add(botonLimpiar);





    }

}
