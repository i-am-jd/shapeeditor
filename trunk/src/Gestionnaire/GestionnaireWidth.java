package gestionnaire;

import Draw.SceneGraph;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;



/**
 * Gestionnaire d'action pour changer la couleur de dessin
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class GestionnaireWidth implements ItemListener
{

	/** La liste des couleurs */
	private float[] widths;
	/** La zone de dessin concenrn�e par le changement de couleurs */
	private SceneGraph sceneGraph;

	/**
	 * Constructeur du gestionnaire de couleurs
	 * @param colors la liste des couleurs
	 * @param zone la zone de dessin
	 */
	public GestionnaireWidth(SceneGraph sceneGraph, float[] widths)
	{
		this.widths = widths;
		this.sceneGraph = sceneGraph;
		sceneGraph.getView().setLineWidth(this.widths[0]);
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
            sceneGraph.getView().setLineWidth(widths[liste.getSelectedIndex()]);
	}
}

