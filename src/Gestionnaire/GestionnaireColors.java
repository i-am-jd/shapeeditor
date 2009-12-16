package gestionnaire;

import IHM.Window;
import java.awt.Color;
import java.awt.TexturePaint;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;


/**
 * Gestionnaire d'action pour changer la couleur de dessin
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class GestionnaireColors implements ItemListener
{

	/** La liste des couleurs */
	private Object[] patterns;
	/** La zone de dessin concenrn�e par le changement de couleurs */
        private int type;

	/**
	 * Constructeur du gestionnaire de couleurs
	 * @param colors la liste des couleurs
	 * @param zone la zone de dessin
	 */
	public GestionnaireColors(Object[] patterns, int type)
	{
		this.patterns = patterns;
                this.type = type;
                if (type==0) {
                    Window.sceneGraph.getView().setLineColor((Color)this.patterns[0]);
                } else if (type==1) {
                    if (this.patterns[0] instanceof Color) {
                        Window.sceneGraph.getView().setFillColor((Color)this.patterns[0]);
                        Window.sceneGraph.getView().setFillPattern(null);
                    } else {
                        Window.sceneGraph.getView().setFillColor(null);
                        Window.sceneGraph.getView().setFillPattern((TexturePaint)this.patterns[0]);
                    }
                }
	}

	/**
	 * Action d�clench�e lorsque l'on change de couleur.
	 * on r�cup�re l'indice de la couleur s�lectionn�e pour l'appliquer �
	 * la zone de dessin.
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	   @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox liste = (JComboBox) e.getSource();
        if (type == 0) {
            Window.sceneGraph.getView().setLineColor((Color)patterns[liste.getSelectedIndex()]);
        } else if (type == 1) {
            if (patterns[liste.getSelectedIndex()] instanceof Color) {
                Window.sceneGraph.getView().setFillColor((Color)patterns[liste.getSelectedIndex()]);
                Window.sceneGraph.getView().setFillPattern(null);
            } else {
                Window.sceneGraph.getView().setFillColor(null);
                Window.sceneGraph.getView().setFillPattern((TexturePaint)patterns[liste.getSelectedIndex()]);
            }
        }
    }
}

