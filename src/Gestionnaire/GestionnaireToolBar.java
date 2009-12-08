package Gestionnaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import IHM.*;

/**
 * Classe de gestion des evenements boutons de la barre d'outils, des instances de cette
 * classe sont utilisees dans {@link ToolBar}.
 * @see ToolBar
 */
public class GestionnaireToolBar implements ActionListener
{
    /**
     * Action specifique pour laquelle ce Gestionnaire d'action est instancie
     */
    public enum ButtonPressed
    {
        /** Actionpour quitter l'application */
        DRAW_RECTANGLE,
        /** Action pour effacer le dessin et en faire un nouveau */
        DRAW_ELLIPSE,
        /** Action pour d�faire la derni�re �tape du dessin */
        DRAW_TRIANGLE,
        /** Action pour ouvrir une boite de dialogue "A propos" */
        DRAW_STAR,
        /** Action pour ouvrir une boite de dialogue "A propos" */
        DRAW_POLYGON;

        /**
         * Action � r�aliser en fonction de la valeur de l'enum
         * @param ga le gestionnaire d'actions
         * @param e l'action event
         * @throws AssertionError
         */
        public void performAction(GestionnaireToolBar g, ActionEvent e)
                throws AssertionError
        {
            switch (this) {
                case DRAW_RECTANGLE: // fin de l'application
                    g.getOptionZone().changeShapeList(0);;
                    return;
                case DRAW_ELLIPSE: // effacement de la zone de dessin
                    g.getOptionZone().changeShapeList(1);
                    return;
                case DRAW_TRIANGLE: // effacement de la derni�re ligne trac�e
                    JOptionPane.showMessageDialog(null, "TRIANGLE",
                            "TRIANGLE", JOptionPane.INFORMATION_MESSAGE);
                    //optionZone.changeShapeList(2);
                    return;
                case DRAW_STAR: // boite de dialogue "� propos"
                    JOptionPane.showMessageDialog(null, "STAR",
                            "STAR", JOptionPane.INFORMATION_MESSAGE);
                    //g.getOptionZone().changeShapeList(3);
                    return;
                case DRAW_POLYGON: // effacement de la derni�re ligne trac�e
                    JOptionPane.showMessageDialog(null, "POLYGON",
                            "POLYGON", JOptionPane.INFORMATION_MESSAGE);
                    //g.getOptionZone().changeShapeList(4);
                    return;
            } throw new AssertionError("ActionToPerform::unknown assertion : " + this);
        }

        /**
         * Affichage de la valeur de l'enum
         */
        public String toString()
        {
            switch (this) {
                case DRAW_RECTANGLE: // fin de l'application
                    return new String("RECTANGLE");
                case DRAW_ELLIPSE: // fin de l'application
                    return new String("ELLIPSE");
                case DRAW_TRIANGLE: // fin de l'application
                    return new String("TRIANGLE");
                case DRAW_STAR: // effacement de la zone de dessin
                    return new String("STAR");
                case DRAW_POLYGON: // boite de dialogue "� propos"
                    return new String("POLYGON");
            } throw new AssertionError("ActionToPerform::unknown assertion : " + this);
        }
    }
    
    /**
     * Action a effectuer
     */
    private ButtonPressed action;
    /**
     * 
     */
    private OptionPanel optionZone;
    /**
     * r�f�rence vers la zone Graphique. Dans la mesure ou les actions trait�es
     * par le GestionnaireActions ont des r�percutions dans la zone graphique
     */
    //private JZoneGraphique zone;
    
    /**
     * Constructeur utilise pour traiter l'action d'un bouton
     * @param id indentifiant de l'action demandee
     * @param zone r�f�rence vers la zone graphique qui sera modifi�e par cette
     *            action
     */
    public GestionnaireToolBar(ButtonPressed id, OptionPanel optionZone)
    {
        action = id;
        this.optionZone = optionZone;
    }
    
    /**
     * Accesseur en lecture de la zone
     * @return the zone
     */
    /*public JZoneGraphique getZone()
    {
    return zone;
    }*/
    public OptionPanel getOptionZone()
    {
        return optionZone;
    }

    /**
     * implementation de ActionListener. Traitement de l'action generee par
     * le clic sur un bouton.
     * @param e action courante
     * @see ActionToPerform#performAction(GestionnaireActions, ActionEvent)
     */
    public void actionPerformed(ActionEvent e)
    {
        action.performAction(this, e);
    }
}

/*(new ActionListener(){
public void actionPerformed(ActionEvent arg0) {
//JOptionPane jop = new JOptionPane();
//jop.showMessageDialog(null, "Kikou", "title", JOptionPane.INFORMATION_MESSAGE, null);
Shape rect = new Shape(new View(Color.YELLOW, 10, Color.BLUE, null), new Rectangle(200,200,200,200)); // recuperer vue du scene graph
DefaultMutableTreeNode node = sceneGraph.getNode();
rect.insertShape(/getTreeZone().getSceneGraph()/sceneGraph.getNode());
}				
});*/
