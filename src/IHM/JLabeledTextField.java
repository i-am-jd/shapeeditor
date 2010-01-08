package IHM;

import java.awt.GridLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Control made of a title and numetic text field
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class JLabeledTextField extends JPanel {

    /** Title of the panel */
    private String title;
    /** Text field */
    private JNumTextField textField;
    /** Reference to the draw panel of the window */
    private DrawPanel drawZone;

    /**
     * Returns a labeled text field
     * @param title The title of the panel
     * @param max The maximum number of characters that a user can type
     * @param drawZone The reference of the draw panel of the window
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


