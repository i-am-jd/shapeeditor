package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * Class instantiating the menu bar
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class MenuBar extends JMenuBar {

    /** "File" menu */
    private JMenu menuFile = new JMenu("File");
    /** "New" sub-menu */
    private JMenuItem optNew = new JMenuItem("New");
    /** "Exit" sub-menu */
    private JMenuItem optExit = new JMenuItem("Exit");
    /** "About" menu */
    private JMenu menuAbout = new JMenu("About");
    /** "About" sub-menu */
    private JMenuItem optAbout = new JMenuItem("About");

    /**
     * Return a menu bar
     */
    public MenuBar() {

        /* Mise en place des raccourcis clavier */
        optNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        optExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));

        /* Ajout des sous-categories aux categories de la barre de menu */
        this.menuFile.add(optNew);
        this.menuFile.addSeparator();
        this.menuFile.add(optExit);
        //this.menuDessin.add(optLancer);
        this.menuAbout.add(optAbout);

        /* Ajout des différents elements de la barre de menu */
        this.add(menuFile);
        //this.add(menuDessin);
        this.add(menuAbout);
    }

    /**
     * Initialize the listeners needed by the menu bar
     * @param drawZone
     */
    public void initializeListeners(final DrawPanel drawZone) {
        /* Ajout des ActionListener associes aux sous categories */
        optNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Window.sceneGraph.removeAllChildren();
                drawZone.repaintPanel();
            }
        });
        optExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        optAbout.addActionListener(new ActionListener() {

            @Override
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane jop = new JOptionPane();
                //ImageIcon img = new ImageIcon("images/img.png");
                String mess = "Shape Editor \n" +
                        "ILO Project 2009-2010 \n" +
                        "Developpement team :\n" +
                        "    - Boris Dadachev\n" +
                        "    - Jean-Denis Koeck\n" +
                        "All rights reserved. \n";
                JOptionPane.showMessageDialog(null, mess, "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
