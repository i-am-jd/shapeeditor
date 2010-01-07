package Gestionnaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import IHM.*;

/**
 * Classe de gestion des evenements boutons de la barre d'outils, des instances de cette
 * classe sont utilisees dans {@link ToolBar}.
 * @see ToolBar
 * @author Boris Dadachev & Jean-Denis Koeck
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
        DRAW_POLYGON,

        /**
         *
         */
        MODE_SELECTION,

        /**
         *
         */
        GROUP,

        /**
         *
         */
        DEGROUP,

        /**
         *
         */
        COPY,

        /**
         *
         */
        DELETE,

        /**
         *
         */
        UNDO,

        /**
         *
         */
        ROTATE,

        /**
         *
         */
        SCALE,

        /**
         *
         */
        SHEAR,

        /**
         *
         */
        UNION,

        /**
         *
         */
        INTERSECTION,

        /**
         *
         */
        SUBSTRACTION,

        /**
         *
         */
        EXCLUSION,

        /**
         *
         */
        INCLUSION,

        /**
         *
         */
        INTERPOLATION;

        /**
         * Action � r�aliser en fonction de la valeur de l'enum
         * @param g
         * @param e l'action event
         * @throws AssertionError
         */
        public void performAction(GestionnaireToolBar g, ActionEvent e)
                throws AssertionError
        {
            OptionPanel p = g.getOptionZone();
            switch (this) {
                case DRAW_RECTANGLE: // fin de l'application
                    p.getDrawZone().switchDrawingMode();
                    p.changeShapeList(0);
                    return;
                case DRAW_ELLIPSE: // effacement de la zone de dessin
                    p.getDrawZone().switchDrawingMode();
                    p.changeShapeList(1);
                    return;
                case DRAW_TRIANGLE: // effacement de la derni�re ligne trac�e
                    p.getDrawZone().switchDrawingMode();
                    p.changeShapeList(2);
                    return;
                case DRAW_STAR: // boite de dialogue "� propos"
                    p.getDrawZone().switchDrawingMode();
                    p.changeShapeList(3);
                    return;
                case DRAW_POLYGON: // effacement de la derni�re ligne trac�e
                    p.getDrawZone().switchDrawingMode();
                    p.changeShapeList(4);
                    return;
                case MODE_SELECTION: //Passage en mode sélection
                    p.getDrawZone().switchSelectionMode();
                    return;
                case GROUP: //Groupement des formes constituant la sélection courante
                    p.getDrawZone().groupCurrentSelection();
                    return;
                case DEGROUP:
                    p.getDrawZone().degroupCurrentSelection();
                    return;
                case COPY: //Copie des formes constituant la sélection courante
                    p.getDrawZone().copyCurrentSelection();
                    return;
                case DELETE: //Suppression des formes constituant la sélection courante
                    p.getDrawZone().deleteCurrentSelection();
                    return;
                case UNDO:
                    return;
                case ROTATE: //Copie des formes constituant la sélection courante
                    p.getDrawZone().switchRotationMode();
                    return;
                case SCALE: //Copie des formes constituant la sélection courante
                    p.getDrawZone().switchScaleMode();
                    return;
                case SHEAR: //Copie des formes constituant la sélection courante
                    p.getDrawZone().switchShearMode();
                    return;
                case UNION: //Suppression des formes constituant la sélection courante
                    p.getDrawZone().unionCurrentSelection();
                    return;
                case INTERSECTION: //Suppression des formes constituant la sélection courante
                    p.getDrawZone().intersectCurrentSelection();
                    return;
                case SUBSTRACTION:
                    p.getDrawZone().substractCurrentSelection();
                    return;
                case EXCLUSION:
                    p.getDrawZone().exclusionCurrentSelection();
                    return;
                case INCLUSION: //Suppression des formes constituant la sélection courante
                    p.getDrawZone().inclusionCurrentSelection();
                    return;
                case INTERPOLATION: //Suppression des formes constituant la sélection courante
                    p.getDrawZone().interpolateCurrentSelection();
                    return;
            } throw new AssertionError("ActionToPerform::unknown assertion : " + this);
        }

        /**
         * Affichage de la valeur de l'enum
         */
        @Override
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
                case MODE_SELECTION:
                    return new String("SELECTION");
                case GROUP:
                    return new String("GROUP");
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
     * @param optionZone
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
    @Override
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
