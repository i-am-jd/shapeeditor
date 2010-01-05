package gestionnaire;

import Draw.SceneGraph;
import IHM.DrawPanel;
import IHM.Window;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



/**
 * Gestionnaire d'action pour changer la couleur de dessin
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class GestionnaireWidth implements ChangeListener {

    private DrawPanel drawZone;
    /**
     * Constructeur du gestionnaire de couleurs
     * @param colors la liste des couleurs
     * @param zone la zone de dessin
     */
    public GestionnaireWidth(int init, DrawPanel drawZone) {
        Window.sceneGraph.getView().setLineWidth(init);
        this.drawZone = drawZone;
    }

    /**
     * Action d�clench�e lorsque l'on change de couleur.
     * on r�cup�re l'indice de la couleur s�lectionn�e pour l'appliquer �
     * la zone de dessin.
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            int newWidth = (int) source.getValue();
            Window.sceneGraph.getView().setLineWidth(newWidth);
            Vector<SceneGraph> selection = drawZone.getSelection();
            for(Enumeration<SceneGraph> en = selection.elements(); en.hasMoreElements();) {
                SceneGraph sg = en.nextElement();
                sg.getView().setLineWidth(newWidth);
                drawZone.repaint();
            }
        }
    }
}

