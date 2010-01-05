package IHM;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

/**
 * Classe contenant un titre et une liste d�roulante utilisant des JLabel avec
 * des icones
 */
public class JLabeledSlider extends JPanel
{
	/** Le titre */
	private String title;

        /** Le slider affichée*/
        private JSlider slider;

	/**
	 * Constructeur
	 * @param title le titre du panel
	 * @param captions les l�gendes des �l�ments de la liste
	 * @param selectedIndex l'�l�ment s�lectionn� initialement
	 * @param listener le listener � appeller quand l'�lement s�lectionn� de la
	 *            liste change
	 * @see #createImageIcon(String)
	 */
	public JLabeledSlider(String title, int orient, int min, int max, int init, ChangeListener listener)
	{
		super(new GridLayout(2, 1), true); // double buffered

		this.title = title;

		// Creates the title
		JLabel label = new JLabel(this.title);
		add(label);

		// Creates the Combobox
		this.slider = new JSlider(orient, min, max, init);
                this.slider.setMajorTickSpacing(min);
                this.slider.setMinorTickSpacing(min);
                this.slider.setPaintTicks(true);
                this.slider.setPaintLabels(true);

                slider.addChangeListener(listener);
                add(slider);
	}
}
