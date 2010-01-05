package IHM;

import gestionnaire.GestionnaireColors;
import gestionnaire.GestionnaireWidth;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;

/**
 * La barre d'infos situee en bas de la fenetre pour afficher
 * differentes informations
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class OptionPanel extends JPanel {

    static final int LW_MIN = 1;
    static final int LW_MAX = 10;
    static final int LW_INIT = 1;

    /** Le premier label */
    private JPanel shapePanel;
    private JLabel shapeLabel;
    private JComboBox shapeList;

    private JLabeledTextField textField;
    /** Le premier label */
    private JLabeledComboBox lineColorList;
    private JLabeledSlider lineWidthSlider;

    /** Le premier label */
    private JLabeledComboBox fillPatternList;
    
    private DrawPanel drawZone;

    /**
     * Constructeur de la barre d'infos
     */
    public OptionPanel(DrawPanel drawZone) {
        super();

        this.drawZone = drawZone;

        /* Mise en place de la combobox pour la couleur de ligne */
        Color[] colors = {Color.black, Color.blue, Color.cyan, Color.green, Color.yellow,
            Color.orange, Color.red, Color.magenta};
        String libelleColors[] = {"Black", "Blue", "Cyan", "Green", "Yellow", "Orange",
            "Red", "Magenta"};
        lineColorList = new JLabeledComboBox("Line Colors", libelleColors, 0,
              new GestionnaireColors(colors, 0));

        // Mise en place du slider pour l'epaisseur de ligne
        lineWidthSlider = new JLabeledSlider("Line Width", JSlider.HORIZONTAL,
              LW_MIN, LW_MAX, LW_INIT, new GestionnaireWidth(LW_INIT));
             
        /* Creation de la premiere texture (motifs de remplissage) */
        BufferedImage pBI1 = new BufferedImage(32, 32, BufferedImage.TYPE_INT_BGR);
        Graphics2D pG2d1 = pBI1.createGraphics();
        //ImageIcon pImg1 = createImageIcon("../images/Stripes.gif");
        ImageIcon pImg1 = new ImageIcon(OptionPanel.class.getResource("../images/Stripes.gif"));
        pG2d1.drawImage(pImg1.getImage(), 0, 0, this);
        Rectangle2D imageRect1 = new Rectangle2D.Double(0, 0, 32, 32);
        TexturePaint texture1 = new TexturePaint(pBI1, imageRect1.getBounds2D());
        /* Creation de la seconde texture */
        BufferedImage pBI2 = new BufferedImage(32, 32, BufferedImage.TYPE_INT_BGR);
        Graphics2D pG2d2 = pBI2.createGraphics();
        ImageIcon pImg2 = new ImageIcon(OptionPanel.class.getResource("../images/Baroque.gif"));
        pG2d2.drawImage(pImg2.getImage(), 0, 0, this);
        Rectangle2D imageRect2 = new Rectangle2D.Double(0, 0, 32, 32);
        TexturePaint texture2 = new TexturePaint(pBI2, imageRect2.getBounds2D());
        /* Creation de la troisieme texture */
        BufferedImage pBI3 = new BufferedImage(32, 32, BufferedImage.TYPE_INT_BGR);
        Graphics2D pG2d3 = pBI3.createGraphics();
        ImageIcon pImg3 = new ImageIcon(OptionPanel.class.getResource("../images/Stars.gif"));
        pG2d3.drawImage(pImg3.getImage(), 0, 0, this);
        Rectangle2D imageRect3 = new Rectangle2D.Double(0, 0, 32, 32);
        TexturePaint texture3 = new TexturePaint(pBI3, imageRect3.getBounds2D());
        /* Mise en place de la combobox pour le motif de remplissage */
        Object[] patterns = {Color.black, Color.blue, Color.cyan, Color.green,
            Color.yellow, Color.orange, Color.red, Color.magenta,
            texture1, texture2, texture3};
        String libelleFill[] = {"Black", "Blue", "Cyan", "Green", "Yellow", "Orange",
            "Red", "Magenta", "Stripes", "Baroque", "Stars"};
        fillPatternList = new JLabeledComboBox("Fill Patterns", libelleFill, 0,
              new GestionnaireColors(patterns, 1));

        // Mise en place du textField pour le nombre de cotes
        this.textField = new JLabeledTextField("Number of sides", 2);

        // Mise en place du textField pour le type de forme
        shapeList = new JComboBox();
        //shapeList.setPreferredSize(new Dimension(100,20));
        shapeList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                getDrawZone().setCurrentShapeType((String) shapeList.getSelectedItem());
                if (shapeList.getSelectedItem() != null) {
                    if (((String) shapeList.getSelectedItem()).equals(new String("Regular Polygon")) ||
                            //((String) shapeList.getSelectedItem()).equals(new String("Irregular Polygon")) ||
                            ((String) shapeList.getSelectedItem()).equals(new String("Other Star"))) {
                        textField.setEnabled(true);
                    } else {
                        textField.setEnabled(false);
                    }
                }
            }
        });
        changeShapeList(0);
        shapePanel = new JPanel(new GridLayout(2, 1), true);
        shapeLabel = new JLabel("Type of shape", JLabel.CENTER);
        shapePanel.add(shapeLabel);
        shapePanel.add(shapeList);
     
        this.setLayout(new GridLayout(1, 5));
        this.add(shapePanel);
        this.add(textField);
        this.add(lineColorList);
        this.add(lineWidthSlider);

        this.add(fillPatternList);
        this.setBorder(BorderFactory.createEtchedBorder());
    }

    public void changeShapeList(int id) {
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
                shapeList.addItem("Equilateral Triangle");
                shapeList.addItem("Isosceles Triangle");
                shapeList.addItem("Right-angled Triangle");
                return;
            case 3:
                shapeList.addItem("Five-pointed Star");
                shapeList.addItem("Six-pointed Star");
                shapeList.addItem("Other Star");
                return;
            case 4:
                shapeList.addItem("Regular Polygon");
                shapeList.addItem("Irregular Polygon");
                return;
        }
    }

    public DrawPanel getDrawZone() {
        return this.drawZone;
    }

}
