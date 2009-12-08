package IHM;

import Draw.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

public class TreePanel extends JPanel {

    /* La vue associee au graphe de scene */
    public JTree arbre;
    public SceneGraph sceneGraph = null;
    /* Le menu contextuel ouvert lors du clic droit sur un noeud de l'arbre */
    private JPopupMenu jpm = new JPopupMenu();
    private JMenuItem suppression = new JMenuItem("Supprimer");

    public TreePanel(SceneGraph sceneGraph) {
        super();

        this.sceneGraph = sceneGraph;
        this.arbre = new JTree(sceneGraph);
        //arbre = buildTree();
        //arbre = new JTree(new SceneGraph(new View(Color.YELLOW, 10, Color.BLUE, null), "Essai").getNode());
        /* Pour recuperer un noeud sur lequel on clique */
        /*arbre.addTreeSelectionListener(new TreeSelectionListener(){
        public void valueChanged(TreeSelectionEvent event) {
        if(arbre.getLastSelectedPathComponent() != null){
        //La méthode getPath retourne un objet TreePath
        System.out.println(arbre.getLastSelectedPathComponent().toString());
        // System.out.println(getAbsolutePath(event.getPath()));
        }
        }
        // Recupere les parents
        private String getAbsolutePath(TreePath treePath){
        String str = "";
        //On balaie le contenu de notre TreePath
        for(Object name : treePath.getPath()){
        //Si l'objet à un nom, on l'ajoute au chemin
        if(name.toString() != null)
        str += name.toString();
        }
        return str;
        }
        });*/

        /* Preparation du menu contextuel declenche lors d'un clic droit */
        jpm.add(suppression);
        suppression.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //JOptionPane jop = new JOptionPane();
                JOptionPane.showMessageDialog(null, "Suppression demandee", "Supp", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        arbre.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Detection du clic droit
                if (e.isPopupTrigger()) {
                    int selRow = arbre.getRowForLocation(e.getX(), e.getY());
                    TreePath selPath = arbre.getPathForLocation(e.getX(), e.getY());
                    if (selRow != -1) {
                        arbre.clearSelection();
                        arbre.setSelectionPath(selPath);
                        //DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
                        // Affichage du menu contextuel
                        jpm.show(arbre, e.getX(), e.getY());
                        // Informations recuperees sur le noeud
                        //System.out.println("clic droit sur : "+node.getUserObject());
                    }
                }
            }
        });

        /* Pour que l'arbre prenne toute la place disponible par defaut */
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(arbre), BorderLayout.CENTER);
    }

    /*
    private JTree buildTree()
    {
    //Création d'une racine
    DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Racine");

    //Nous allons ajouter des branches et des feuilles à notre racine
    for(int i = 1; i < 12; i++){
    DefaultMutableTreeNode rep = new DefaultMutableTreeNode("Noeud N�"+i);

    //S'il s'agit d'un nombre pair, on rajoute une branche
    if((i%2) == 0){
    //Et une branche en plus ! Une !
    for(int j = 1; j < 5; j++){
    DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode("Fichier enfant N�" + j);
    //Cette fois, on ajoute nos feuilles
    for(int k = 1; k < 5; k++)
    rep2.add(new DefaultMutableTreeNode("Sous Fichier enfant N�" + k));
    rep.add(rep2);
    }
    }
    //On ajoute la feuille ou la branche à la racine
    racine.add(rep);
    }
    //On crée, avec notre hiérarchie, un arbre
    return new JTree(racine);
    }

    private JTree buildTree2()
    {
    //Création d'une racine
    DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Racine");
    //On crée, avec notre hiérarchie, un arbre
    return new JTree(racine);
    }
     */
    public SceneGraph getSceneGraph() {
        return sceneGraph;
    }

    /* public void reloadJTree() {
        //arbre.setModel(arbre.getModel());
    }*/
}
