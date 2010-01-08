package IHM;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 * Control made of a title and a combobox using labels and icons
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class JLabeledComboBox extends JPanel
{
	/** Title of the combobox */
	private String title;

	/** ImageIcon array with the icons for each entry of the combobox */
	private ImageIcon[] icons;

	/** String array with the labels for each entry of the combobox */
	private String[] captions;

        /** Combobox */
        private JComboBox combobox;

	/** Directory containing the images */
	private final static String ImageBase = "../images/";

	/** Extension of the images */
	private final static String ImageType = ".gif";

	/**
	 * Returns a JLabeledCombobox
	 * @param title The title of the panel
	 * @param captions The captions of the list entries
	 * @param selectedIndex The initial selected index
	 * @param listener The combobox associated listener
	 */
	public JLabeledComboBox(String title, String[] captions, int selectedIndex,
	        ItemListener listener)
	{
		super(new GridLayout(2, 1), true); // double buffered

		this.title = title;
		this.captions = captions;

		// Charge les images et cr�� un tableau d'indexs pour le combobox
		// qui seront utilis�s dans le ComboBoxRenderer
		icons = new ImageIcon[this.captions.length];
		Integer[] intArray = new Integer[this.captions.length];
		for (int i = 0; i < icons.length; i++)
		{
			intArray[i] = new Integer(i);
			icons[i] = createImageIcon(ImageBase + this.captions[i] + ImageType);
			if (icons[i] != null)
			{
				icons[i].setDescription(this.captions[i]);
			}
		}

		// Creates the title
		JLabel label = new JLabel(this.title, JLabel.CENTER);
		add(label);

		// Creates the Combobox
		combobox = new JComboBox(intArray);
		combobox.setEditable(false);
		if (selectedIndex < 0 || selectedIndex > captions.length)
		{
			selectedIndex = 0;
		}
		combobox.setSelectedIndex(selectedIndex);
		combobox.addItemListener(listener);
		// Mise en place du renderer pour les �lements de la liste
		JLabelRenderer renderer = new JLabelRenderer();
		renderer.setPreferredSize(new Dimension(100, 32));
		combobox.setRenderer(renderer);
		// Ajout de la liste
		add(combobox);
		// setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}

	/**
	 * Creates an image icon from the path of an image
	 * @param path The path of the image
	 * @return The ImageIcon if the file exists, null otherwise
	 */
	protected ImageIcon createImageIcon(String path)
	{
		URL imgURL = JLabeledComboBox.class.getResource(path);
		if (imgURL != null)
		{
			return new ImageIcon(imgURL);
		}
		else
		{
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Class : renderer for the captions of the combobox
	 */
	protected class JLabelRenderer extends JLabel implements ListCellRenderer
	{
		/** fonte pour les items � probl�mes */
		private Font pbFont;

		/**
		 * Returns a renderer
		 */
		public JLabelRenderer()
		{
			setOpaque(true);
			setHorizontalAlignment(LEFT);
			setVerticalAlignment(CENTER);
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing
		 * .JList, java.lang.Object, int, boolean, boolean)
		 */
		@SuppressWarnings("synthetic-access")
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
		        int index, boolean isSelected, boolean cellHasFocus)
		{
			// Obtention de l'indice de l'�l�ment selectionn� : le param�tre
			// index est toujours valide, il faut juste le caster
			int selectedIndex = ((Integer) value).intValue();

			if (isSelected)
			{
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			}
			else
			{
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			// Mise en place de l'icone et du texte dans le label
			// Si l'icone est null afficher un label particulier avec
			// setPbText
			ImageIcon itemIcon = icons[selectedIndex];
			String itemString = captions[selectedIndex];
			setIcon(itemIcon);
			if (itemIcon != null)
			{
				setText(itemString);
				setFont(list.getFont());
			}
			else
			{
				setPbText(itemString + " (pas d'image)", list.getFont());
			}

			return this;
		}

		/**
		 * Sets the text if a problem happends for this item
		 * @param pbText The text to display
		 * @param normalFont The font to use
		 */
		protected void setPbText(String pbText, Font normalFont)
		{
			if (pbFont == null)
			{ // lazily create this font
				pbFont = normalFont.deriveFont(Font.ITALIC);
			}
			setFont(pbFont);
			setText(pbText);
		}

	}
}
