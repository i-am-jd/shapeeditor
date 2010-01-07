package IHM;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import Draw.SceneGraph;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Class instantiating the main window of the application
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Window extends JFrame {

    /** Width of the window */
    final static int HTAILLE = 1024;
    /** Height of the window */
    final static int VTAILLE = 768;
    /** Name of the window */
    final static String NOMFENETRE = "Graphic Shape Editor";
    /** Scene graph : global variable */
    public static SceneGraph sceneGraph;
    /** Menu bar */
    private MenuBar menuBar;
    /** Draw panel */
    private DrawPanel drawZone;
    /** JTree panel */
    private TreePanel treeZone;
    /** Tool bar */
    private ToolBar toolBar;
    /** JSplitPane, separating the draw panel from the JTree panel */
    private JSplitPane split;
    /** Information bar */
    private InfoBar infoBar;
    /** Options panel */
    private OptionPanel optionZone;
    /** Origin of the draw panel */
    public static int[] origin;

    /**
     * Returns a main window
     */
    public Window() {
        // Creation de la fenetre
        super();

        // Creation du graphe de scene
        sceneGraph = new SceneGraph("Scene Graph");
        origin = new int[2];

        // Proprietes generales de la fenetre
        // Titre de la fenetre
        this.setTitle(NOMFENETRE);
        /// Taille de la fenetre
        this.setSize(HTAILLE, VTAILLE);
        // Centre la fenetre a l'ecran
        this.setLocationRelativeTo(null);
        // Pour fermer la fenetre lorsqu'on clique sur la croix
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creation des composants de la fenetre
        infoBar = new InfoBar();
        treeZone = new TreePanel();
        drawZone = new DrawPanel(infoBar, treeZone);
        optionZone = new OptionPanel(drawZone);
        menuBar = new MenuBar();
        toolBar = new ToolBar(optionZone);

        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeZone, drawZone);
        split.setDividerLocation(200);

        treeZone.initializeListeners(drawZone);
        menuBar.initializeListeners(drawZone);

        // Ajout de la barre de menu a la fenetre
        this.setJMenuBar(menuBar);
        // Ajout des differents sous-conteneurs au conteneur principal
        this.getContentPane().setLayout(new BorderLayout());
        this.add(optionZone, BorderLayout.NORTH);
        this.add(toolBar, BorderLayout.WEST);
        this.add(split, BorderLayout.CENTER);
        this.add(infoBar, BorderLayout.SOUTH);

        // Affiche la fenetre a l'ecran
        this.setVisible(true);

        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                drawZone.calculateOrigin();
            }
        });

    }
}
