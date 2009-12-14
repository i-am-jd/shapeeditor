package IHM;

import Draw.SceneGraph;
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
    private JMenu menuDessin = new JMenu("Dessin");
    private JMenuItem optLancer = new JMenuItem("Lancer");
    /* menu A propos */
    private JMenu menuAide = new JMenu("A propos");
    private JMenuItem optAPropos = new JMenuItem("A propos");

    public MenuBar() {
        /* Ajout des ActionListener associes aux sous categories */
        optNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Window.sceneGraph = new SceneGraph("Scene Graph");
            }
        });
        optExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        optLancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //...
            }
        });
        optAPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane jop = new JOptionPane();
                //ImageIcon img = new ImageIcon("images/img.png");
                String mess = "Editeur de formes geometriques \n" +
                        "Projet ILO 2009-2010 \n" +
                        "Equipe de developpement :\n" +
                        "    - Boris Dadachev\n" +
                        "    - Jean-Denis Koeck\n" +
                        "Tous droits reserves \n";
                jop.showMessageDialog(null, mess, "A propos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        /* Mise en place des raccourcis clavier */
        optNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        optExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));

        /* Ajout des sous-categories aux categories de la barre de menu */
        this.menuFile.add(optNew);
        this.menuFile.add(optExit);
        this.menuDessin.add(optLancer);
        this.menuAide.add(optAPropos);

        /* Ajout des différents elements de la barre de menu */
        this.add(menuFile);
        this.add(menuDessin);
        this.add(menuAide);
    }
}
