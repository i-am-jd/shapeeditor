package IHM;

import gestionnaire.GestionnaireWidth;
import gestionnaire.GestionnaireColors;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 * La barre d'infos situee en bas de la fenetre pour afficher
 * differentes informations
 * @author Boris Dadachev
 */
public class OptionPanel extends JPanel
{

    /** Le premier label */
    private JComboBox shapeList;
    /** Le premier label */
    private JLabeledComboBox lineColorList;
    /** Le premier label */
    private JLabeledComboBox lineWidthList;
    /** Le premier label */
    private JLabeledComboBox fillPatternList;
    /** Le second label */
    private JLabel info2;
    private DrawPanel drawZone;

    /**
     * Constructeur de la barre d'infos
     */
    public OptionPanel(DrawPanel drawZone) {
        super();

        this.drawZone = drawZone;
        /* Mise en place de la combobox pour la couleur de ligne */
        Color[] colors = { Color.black, Color.blue, Color.cyan, Color.green, Color.yellow,
		        Color.orange, Color.red, Color.magenta };
	String libelleColors[] = {"Black", "Blue", "Cyan", "Green", "Yellow", "Orange",
		        "Red", "Magenta" };
        lineColorList = new JLabeledComboBox("Line Colors", libelleColors, 0,
                new GestionnaireColors(colors));

        /* Mise en place de la combobox pour l'epaisseur de ligne */
	float[] widths = { 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f };
	String[] libelleEpaisseurs = new String[10];
	DecimalFormat numberFormat = new DecimalFormat("00");
	for (int i = 0; i < widths.length; i++)
	{
		libelleEpaisseurs[i] = numberFormat.format(widths[i]);
	}
        lineWidthList = new JLabeledComboBox("Width", libelleEpaisseurs, 0, new
                GestionnaireWidth(widths));

        /* Mise en place de la combobox pour la couleur de ligne */
        Color[] fill = { Color.black, Color.blue, Color.cyan, Color.green,
            Color.yellow, Color.orange, Color.red, Color.magenta };
	String libelleFill[] = {"Black", "Blue", "Cyan", "Green", "Yellow", "Orange",
		        "Red", "Magenta" };
        fillPatternList = new JLabeledComboBox("Fill Patterns", libelleFill, 0,
                new GestionnaireColors(fill));

        shapeList = new JComboBox();
        //shapeList.setPreferredSize(new Dimension(100,20));
        shapeList.addItem("Rectangle");
        shapeList.addItem("Square");
        shapeList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //System.out.println(shapeList.getSelectedItem());
                getDrawZone().setCurrentShapeType((String)shapeList.getSelectedItem());
            }
        });

        this.setLayout(new GridLayout(1, 5));
        this.add(shapeList);
        this.add(info2 = new JLabel("Essai"));
        this.add(lineColorList);
        this.add(lineWidthList);
        this.add(fillPatternList);
        this.setBorder(BorderFactory.createEtchedBorder());
    }

    public void changeShapeList(int id)
    {
        shapeList.removeAllItems();
        switch (id) {
            case 0:
                shapeList.addItem("Rectangle");
                shapeList.addItem("Square");
                return;
            case 1:
                shapeList.addItem("Circle");
                shapeList.addItem("Ellipse");
                return;
            case 2:
                return;
            case 3:
                return;
            case 4:
                return;
        }
    }

    public DrawPanel getDrawZone() {
        return this.drawZone;
    }
}
