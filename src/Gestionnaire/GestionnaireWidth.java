package Gestionnaire;

import Draw.SceneGraph;
import IHM.DrawPanel;
import IHM.Window;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



/**
 * Action manager to change the line width
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class GestionnaireWidth implements ChangeListener {

    private DrawPanel drawZone;
    /**
     * Returns a line width manager
     * @param init The initial value
     * @param drawZone Reference to the draw panel of the application
     */
    public GestionnaireWidth(int init, DrawPanel drawZone) {
        Window.sceneGraph.getView().setLineWidth(init);
        this.drawZone = drawZone;
    }

    /**
     * Action performed when the user changes the line width
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
                drawZone.repaintPanel();
            }
        }
    }
}

