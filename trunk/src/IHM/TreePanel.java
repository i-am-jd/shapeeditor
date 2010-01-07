package IHM;

import Draw.*;
import DragNDrop_JTree.*;

import java.awt.BorderLayout;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * Class instantiating the JTree Panel
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class TreePanel extends JPanel {

    /** JTree used to display the scene graph elements */
    public JTree arbre;
    /** Context menu displayed when the user performs a right click on a node */
    public SelectionContextMenu popupMenu;
    
    /**
     * Returns a JTree panel
     */
    public TreePanel() {
        super();

        this.arbre = new JTree(Window.sceneGraph);
        //Permet de selectionner un noeud par un clic gauche sur le JTree
        this.arbre.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.arbre.setShowsRootHandles(true);

        /* Pour que l'arbre prenne toute la place disponible par defaut */
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(arbre), BorderLayout.CENTER);

    }

    /**
     * Initializes the listener of the tree panel
     * @param drawZone The draw panel of the window
     */
    public void initializeListeners(final DrawPanel drawZone) {
        // Selection du noeud sur lequel on clique
        arbre.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent event) {
                if (arbre.getLastSelectedPathComponent() != null) {
                    drawZone.getSelection().clear();
                    SceneGraph node = (SceneGraph) arbre.getLastSelectedPathComponent();
                    if (!node.isRoot()) {
                        drawZone.getSelection().add(node);
                    }
                    //System.out.println("Selection " + node.toString());
                    drawZone.repaint();
                }
            }
        });

        //Ajout du menu contextuel ouvert par clic droit sur un noeud
        popupMenu = new SelectionContextMenu(drawZone);
        
        // Ajout du MouseListener a l'arbre
        arbre.addMouseListener(new MouseAdapter() {

            @Override
            //Methode declenchee lors d'un clic droit sous linux
            public void mousePressed(MouseEvent e) {
                // Detection du clic droit
                if (e.isPopupTrigger()) {
                    int selRow = arbre.getRowForLocation(e.getX(), e.getY());
                    TreePath selPath = arbre.getPathForLocation(e.getX(), e.getY());
                    // Verification de la validite du clic (sur un noeud et pas sur la racine)
                    if (selRow != -1 && selRow != 0) {
                        arbre.clearSelection();
                        arbre.setSelectionPath(selPath);
                        // Affichage du menu contextuel
                        popupMenu.show(arbre, e.getX(), e.getY());
                    }
                }
            }
            //Methode declenchee lors d'un clic droit sous windows

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed(e);
            }
        });

        //Pour le DragNDrop
        TreeDragSource ds = new TreeDragSource(arbre, DnDConstants.ACTION_MOVE);
        TreeDropTarget dt = new TreeDropTarget(arbre, drawZone);

    }

    /**
     * Refreshes the view of the JTree
     */
    public void repaintJTree() {
        ((DefaultTreeModel) arbre.getModel()).reload();
    }
}
