package IHM;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

/**
 * Control made of a title and a slider
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class JLabeledSlider extends JPanel
{
	/** The title of the slider */
	private String title;

        /** The slider */
        private JSlider slider;

	/**
	 * Returns a labeled slider
	 * @param title The title of the slider
         * @param orient The orientation of the slider
         * @param min The minimum value of the slider
	 * @param listener The listener of the slider
         * @param max The maximum value of the slider
         * @param init The initial value of the slider
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
