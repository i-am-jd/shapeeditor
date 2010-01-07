package IHM;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

/**
 * Control containing a title and a slider
 * @author Boris Dadachev & Jean-Denis Koeck
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
         * @param orient
         * @param min
	 * @param listener le listener � appeller quand l'�lement s�lectionn� de la
	 *            liste change
         * @param max
         * @param init
         * @see #createImageIcon(String)
	 */
	public JLabeledSlider(String title, int orient, int min, int max, int init, ChangeListener listener)
	{
		super(new GridLayout(2, 1), true); // double buffered

		this.title = title;

		// Creates the title
		JLabel label = new JLabel(this.title, JLabel.CENTER);
		add(label, JLabel.CENTER);
                
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
