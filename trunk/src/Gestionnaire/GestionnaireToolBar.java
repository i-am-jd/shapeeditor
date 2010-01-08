package Gestionnaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import IHM.*;

/**
 * Action manager for the buttons used in the {@link ToolBar}.
 * @see ToolBar
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class GestionnaireToolBar implements ActionListener
{
    /**
     * Enumeration of the possible actions (when the user clicks on a button)
     */
    public enum ButtonPressed
    {
        /**
         * Draw a 4-sides regular polygon
         */
        DRAW_RECTANGLE,
        /**
         * Draw an elliptic shape
         */
        DRAW_ELLIPSE,
        /**
         * Draw a 3-sides polygon
         */
        DRAW_TRIANGLE,
        /**
         * Draw a star
         */
        DRAW_STAR,
        /**
         * Draw a polygon
         */
        DRAW_POLYGON,

        /**
         * Selection
         */
        MODE_SELECTION,

        /**
         * Group
         */
        GROUP,

        /**
         *
         */
        DEGROUP,

        /**
         * Copy
         */
        COPY,

        /**
         * Delete
         */
        DELETE,

        /**
         * Undo
         */
        UNDO,

        /**
         * Rotate
         */
        ROTATE,

        /**
         * Scale
         */
        SCALE,

        /**
         * Shear
         */
        SHEAR,

        /**
         * Union
         */
        UNION,

        /**
         * Intersection
         */
        INTERSECTION,

        /**
         * Substraction
         */
        SUBSTRACTION,

        /**
         * Exclusion
         */
        EXCLUSION,

        /**
         * Inclusion
         */
        INCLUSION,

        /**
         * Interpolation
         */
        INTERPOLATION;

        /**
         * Action to perform when the user clicks on a button of the toolbar
         * @param g The action manager
         * @param e The action event
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
    }
    
    /**
     * Action to perform
     */
    private ButtonPressed action;
    
    /**
     * Reference to the options panel of the window
     */
    private OptionPanel optionZone;
    
    /**
     * Returns an action manager
     * @param id ID of the wanted action
     * @param optionZone Reference to the options panel of the window
     */
    public GestionnaireToolBar(ButtonPressed id, OptionPanel optionZone)
    {
        action = id;
        this.optionZone = optionZone;
    }
    
    /**
     * gets the options panel of the window
     * @return the zone
     */
    public OptionPanel getOptionZone()
    {
        return optionZone;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        action.performAction(this, e);
    }
}

