package IHM;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import Draw.SceneGraph;
import Draw.View;
import java.awt.Color;

/**
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Window extends JFrame {

    /** Largeur de la fenetre */
    final static int HTAILLE = 1024;
    /** Hauteur de la fenetre */
    final static int VTAILLE = 768;
    /** Nom de la fenetre */
    final static String NOMFENETRE = "Editeur de formes geometriques";

    private MenuBar menuBar;
    private DrawPanel drawZone;
    private TreePanel treeZone;
    private ToolBar toolBar;
    private JSplitPane split;
    private InfoBar infoBar;
    private OptionPanel optionZone;
    private SceneGraph sceneGraph;
    //private Shape shape;

    public Window() {
        /* Creation de la fenetre */
        super();

        /* */
        sceneGraph = new SceneGraph(new View(Color.BLACK, 1, Color.YELLOW, null),
                "Scene Graph");

        /* Proprietes generales de la fenetre */
        /* Titre de la fenetre */
        this.setTitle(NOMFENETRE);
        /* Taille de la fenetre */
        this.setSize(HTAILLE, VTAILLE);
        /* Centre la fenetre a l'ecran */
        this.setLocationRelativeTo(null);
        /* Pour fermer la fenetre lorsqu'on clique sur la croix */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creation des composants de la fenetre */
        infoBar = new InfoBar();
        menuBar = new MenuBar();
        treeZone = new TreePanel(sceneGraph);
        drawZone = new DrawPanel(infoBar, /*treeZone,*/ sceneGraph);
        optionZone = new OptionPanel(drawZone);
        toolBar = new ToolBar(optionZone);
        
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeZone, drawZone);
        
        
        /* Ajout de la barre de menu a la fenetre */
        this.setJMenuBar(menuBar);
        /* Ajout des differents sous-conteneurs au conteneur principal */
        this.getContentPane().setLayout(new BorderLayout());
        this.add(optionZone, BorderLayout.NORTH);
        this.add(toolBar, BorderLayout.WEST);
        this.add(split, BorderLayout.CENTER);
        this.add(infoBar, BorderLayout.SOUTH);

        /* Affiche la fenetre a l'ecran */
        this.setVisible(true);
    }
}
