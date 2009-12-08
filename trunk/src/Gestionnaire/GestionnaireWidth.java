package gestionnaire;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import IHM.DrawPanel;

/**
 * Gestionnaire d'action pour changer la couleur de dessin
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class GestionnaireWidth implements ItemListener
{

	/** La liste des couleurs */
	private float[] width;
	/** La zone de dessin concenrn�e par le changement de couleurs */
	private DrawPanel drawZone;

	/**
	 * Constructeur du gestionnaire de couleurs
	 * @param colors la liste des couleurs
	 * @param zone la zone de dessin
	 */
	public GestionnaireWidth(float[] width)
	{
		this.width = width;
		//this.drawZone = drawZone;
		//drawZone.changeCouleur(this.colors[0]);
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
		//drawZone.changeCouleur(colors[liste.getSelectedIndex()]);
	}
}

