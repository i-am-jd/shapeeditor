package gestionnaire;

import Draw.SceneGraph;
import IHM.DrawPanel;
import IHM.Window;
import java.awt.Color;
import java.awt.TexturePaint;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JComboBox;

/**
 * Gestionnaire d'action pour changer la couleur de dessin
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class GestionnaireColors implements ItemListener {

    /** La liste des couleurs */
    private Object[] patterns;
    /** La zone de dessin concenrn�e par le changement de couleurs */
    private int type;
    private DrawPanel drawZone;
    /**
     * Constructeur du gestionnaire de couleurs
     * @param patterns
     * @param type
     * @param drawZone
     */
    public GestionnaireColors(Object[] patterns, int type, DrawPanel drawZone) {
        this.patterns = patterns;
        this.type = type;
        this.drawZone = drawZone;
        if (type == 0) {
            Window.sceneGraph.getView().setLineColor((Color) this.patterns[0]);
        } else if (type == 1) {
            if (this.patterns[0] instanceof Color) {
                Window.sceneGraph.getView().setFillColor((Color) this.patterns[0]);
                Window.sceneGraph.getView().setFillPattern(null);
            } else {
                Window.sceneGraph.getView().setFillColor(null);
                Window.sceneGraph.getView().setFillPattern((TexturePaint) this.patterns[0]);
            }
        }
    }

    /**
     * Action d�clench�e lorsque l'on change de couleur.
     * on r�cup�re l'indice de la couleur s�lectionn�e pour l'appliquer �
     * la zone de dessin.
     * @param e 
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox liste = (JComboBox) e.getSource();
        Vector<SceneGraph> selection = drawZone.getSelection();
        if (type == 0) {
            Window.sceneGraph.getView().setLineColor((Color) patterns[liste.getSelectedIndex()]);
            for (Enumeration<SceneGraph> en = selection.elements(); en.hasMoreElements();) {
                    SceneGraph sg = en.nextElement();
                    sg.getView().setLineColor((Color) patterns[liste.getSelectedIndex()]);
                    drawZone.repaintPanel();
                }
        } else if (type == 1) {
            if (patterns[liste.getSelectedIndex()] instanceof Color) {
                Window.sceneGraph.getView().setFillColor((Color) patterns[liste.getSelectedIndex()]);
                Window.sceneGraph.getView().setFillPattern(null);
                for (Enumeration<SceneGraph> en = selection.elements(); en.hasMoreElements();) {
                    SceneGraph sg = en.nextElement();
                    sg.getView().setFillColor((Color) patterns[liste.getSelectedIndex()]);
                    sg.getView().setFillPattern(null);
                    drawZone.repaint();
                }
            } else {
                Window.sceneGraph.getView().setFillColor(null);
                Window.sceneGraph.getView().setFillPattern((TexturePaint) patterns[liste.getSelectedIndex()]);
                for (Enumeration<SceneGraph> en = selection.elements(); en.hasMoreElements();) {
                    SceneGraph sg = en.nextElement();
                    sg.getView().setFillColor(null);
                     sg.getView().setFillPattern((TexturePaint) patterns[liste.getSelectedIndex()]);
                    drawZone.repaint();
                }
            }
        }
    }
}

