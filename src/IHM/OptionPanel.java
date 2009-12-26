package IHM;

import gestionnaire.GestionnaireWidth;
import gestionnaire.GestionnaireColors;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 * La barre d'infos situee en bas de la fenetre pour afficher
 * differentes informations
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class OptionPanel extends JPanel {

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
    private JTextField textField;
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

        /* Mise en place de la combobox pour l'epaisseur de ligne */
        float[] widths = {1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f};
        String[] libelleEpaisseurs = new String[10];
        DecimalFormat numberFormat = new DecimalFormat("00");
        for (int i = 0; i < widths.length; i++) {
            libelleEpaisseurs[i] = numberFormat.format(widths[i]);
        }
        lineWidthList = new JLabeledComboBox("Width", libelleEpaisseurs, 0, new GestionnaireWidth(widths));

        
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

        this.textField = new JTextField();
        KeyAdapter kAdapter = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                if (!isNumeric(event.getKeyChar()) || textField.getText().length() > 2) {
                    //a modifier : enleve toutes les occurences d'un meme caractere
                    textField.setText(textField.getText().replace(String.valueOf(event.getKeyChar()), ""));
                }
                if (textField.getText().equals("")) {
                    getDrawZone().setNbSides(0);
                } else {
                    getDrawZone().setNbSides(Integer.parseInt(textField.getText()));
                }
            }
            private boolean isNumeric(char carac) {
                try {
                    int i = Integer.parseInt(String.valueOf(carac));
                } catch (NumberFormatException e) {
                    return false;
                }
                return true;
            }
        };
        this.textField.addKeyListener(kAdapter);
        this.textField.setColumns(2);
        //this.textField.setPreferredSize(new Dimension(50, 20));

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

        this.setLayout(new GridLayout(1, 6));
        this.add(shapeList);
        this.add(info2 = new JLabel("Number of sides : "));
        this.add(textField);
        this.add(lineColorList);
        this.add(lineWidthList);
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
