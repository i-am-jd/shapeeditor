package gestionnaire;

import Draw.SceneGraph;
import IHM.Window;
import java.awt.Color;
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
	private Color[] colors;
	/** La zone de dessin concenrn�e par le changement de couleurs */
	//private DrawPanel drawZone;
        private int type;
        private SceneGraph sceneGraph;

	/**
	 * Constructeur du gestionnaire de couleurs
	 * @param colors la liste des couleurs
	 * @param zone la zone de dessin
	 */
	public GestionnaireColors(SceneGraph sceneGraph, Color[] colors, int type)
	{
		this.colors = colors;
                this.type = type;
		this.sceneGraph = sceneGraph;
                if (type==0) {
                    Window.sceneGraph.getView().setLineColor(this.colors[0]);
                } else if (type==1) {
                    Window.sceneGraph.getView().setFillColor(this.colors[0]);
                }
	}

	/**
	 * Action d�clench�e lorsque l'on change de couleur.
	 * on r�cup�re l'indice de la couleur s�lectionn�e pour l'appliquer �
	 * la zone de dessin.
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		JComboBox liste = (JComboBox) e.getSource();
                if (type==0) {
                     Window.sceneGraph.getView().setLineColor(colors[liste.getSelectedIndex()]);
                } else if (type==1) {
                     Window.sceneGraph.getView().setFillColor(colors[liste.getSelectedIndex()]);
                }
	}
}

