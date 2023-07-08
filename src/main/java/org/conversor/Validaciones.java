package org.conversor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Validaciones {

    Border borblack = BorderFactory.createLineBorder(Color.BLACK);
    Border borred = BorderFactory.createLineBorder(Color.RED);
    Border borwhite = BorderFactory.createLineBorder(Color.WHITE);

    public void validarNumeros(KeyEvent e, JTextField t) {
        char c = e.getKeyChar();
        if (e.getSource() == t) {
            if ((Character.isLetter(c))) {
                e.consume();
            }
            if (t.getText().trim().length() == 10) {
                e.consume();
            }
        }
    }

    public void validarText(JTextField t, TitledBorder titulo) {
        if (t.getText().trim().length() == 0) {
            t.setBorder(BorderFactory.createTitledBorder(borred, titulo.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));
        } else {
            t.setBorder(BorderFactory.createTitledBorder(borblack, titulo.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        }
    }
    public void validarCombos(JComboBox c, TitledBorder titulo) {
        if (c.getSelectedIndex() == 0) {
            c.setBorder(BorderFactory.createTitledBorder(borred, titulo.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));

        } else {
            c.setBorder(BorderFactory.createTitledBorder(borblack, titulo.getTitle(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        }
    }
}
