package IHM;

import java.awt.GridLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Control containing a title and numetic text field
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class JLabeledTextField extends JPanel {

    /** Le titre */
    private String title;
    /** Le slider affichée*/
    private JNumTextField textField;

    private DrawPanel drawZone;

    /**
     * Constructeur
     * @param title le titre du panel
     * @param max
     * @param drawZone
     * @see #createImageIcon(String)
     */
    public JLabeledTextField(String title, int max, final DrawPanel drawZone) {
        super(new GridLayout(2, 1), true); // double buffered

        this.title = title;
        this.drawZone = drawZone;

        // Creates the title
        JLabel label = new JLabel(this.title, JLabel.CENTER);
        add(label);

        // Creates the Combobox
        this.textField = new JNumTextField(max);
     
        this.textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e)
            {
                //keyPressed(e);
            }
            @Override
             public void keyPressed(KeyEvent e)
            {
                //keyPressed(e);
            }

            @Override
             public void keyReleased(KeyEvent e)
            {
                try {
                    int nbSides = Integer.parseInt(textField.getText());
                    drawZone.setNbSides(nbSides);
                } catch (Exception ex) {
                }
            }
        });


        add(textField);
    }

    @Override
    public void setEnabled(boolean b) {
        textField.setEnabled(b);
    }
}


