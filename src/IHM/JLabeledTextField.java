package IHM;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe contenant un titre et une liste d�roulante utilisant des JLabel avec
 * des icones
 */
public class JLabeledTextField extends JPanel {

    /** Le titre */
    private String title;
    /** Le slider affichée*/
    private JNumTextField textField;

    /**
     * Constructeur
     * @param title le titre du panel
     * @param captions les l�gendes des �l�ments de la liste
     * @param selectedIndex l'�l�ment s�lectionn� initialement
     * @param listener le listener � appeller quand l'�lement s�lectionn� de la
     *            liste change
     * @see #createImageIcon(String)
     */
    public JLabeledTextField(String title, int max) {
        super(new GridLayout(2, 1), true); // double buffered

        this.title = title;

        // Creates the title
        JLabel label = new JLabel(this.title, JLabel.CENTER);
        add(label);

        // Creates the Combobox
        this.textField = new JNumTextField(max);

        add(textField);
    }
}


