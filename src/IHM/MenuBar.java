package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar {

    /* Definition des categories et sous-categories de la barre de menu */
    /* menu Fichier */

    private JMenu menuFile = new JMenu("File");
    private JMenuItem optNew = new JMenuItem("New");
    private JMenuItem optExit = new JMenuItem("Exit");
    /* menu Dessin */
    //private JMenu menuDessin = new JMenu("Dessin");
    //private JMenuItem optLancer = new JMenuItem("Lancer");
    /* menu A propos */
    private JMenu menuAbout = new JMenu("About");
    private JMenuItem optAbout = new JMenuItem("About");

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

    public void initializeListeners(final DrawPanel drawZone)
    {
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
        //optLancer.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent arg0) {
        //        //...
        //    }
        //});
        optAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane jop = new JOptionPane();
                //ImageIcon img = new ImageIcon("images/img.png");
                String mess = "Shape Editor \n" +
                        "ILO Project 2009-2010 \n" +
                        "Developpement team :\n" +
                        "    - Boris Dadachev\n" +
                        "    - Jean-Denis Koeck\n" +
                        "All rights reserved. \n";
                jop.showMessageDialog(null, mess, "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
