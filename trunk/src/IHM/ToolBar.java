package IHM;

import Gestionnaire.*;
import Gestionnaire.GestionnaireToolBar.ButtonPressed;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btn10;
    private JButton btn11;
    private JButton btn12;
    private JButton btn13;
    private JButton btn14;
    private JButton btn15;
    private JButton btn16;
    private JButton btn17;
    private JButton btn18;

    public ToolBar(OptionPanel optionZone) {
        /* Creation de la barre d'outils avec une orientation verticale */
        super(1);

        btn1 = new JButton(new ImageIcon(getClass().getResource("../images/4-sides.png")));
        btn2 = new JButton(new ImageIcon(getClass().getResource("../images/ellipse.png")));
        btn3 = new JButton(new ImageIcon(getClass().getResource("../images/3-sides.png")));
        btn4 = new JButton(new ImageIcon(getClass().getResource("../images/star.png")));
        btn5 = new JButton(new ImageIcon(getClass().getResource("../images/polygon.png")));

        btn6 = new JButton(new ImageIcon(getClass().getResource("../images/select.png")));
        btn7 = new JButton(new ImageIcon(getClass().getResource("../images/group.png")));
        btn8 = new JButton(new ImageIcon(getClass().getResource("../images/copy.png")));
        btn9 = new JButton(new ImageIcon(getClass().getResource("../images/delete.png")));

        btn10 = new JButton(new ImageIcon(getClass().getResource("../images/rotate.png")));
        btn11 = new JButton(new ImageIcon(getClass().getResource("../images/scale.png")));
        btn12 = new JButton(new ImageIcon(getClass().getResource("../images/shear.png")));

        btn13 = new JButton(new ImageIcon(getClass().getResource("../images/union.png")));
        btn14 = new JButton(new ImageIcon(getClass().getResource("../images/intersection.png")));
        btn15 = new JButton(new ImageIcon(getClass().getResource("../images/substraction.png")));
        btn16 = new JButton(new ImageIcon(getClass().getResource("../images/exclusion.png")));
        btn17 = new JButton(new ImageIcon(getClass().getResource("../images/inclusion.png")));
        btn18 = new JButton(new ImageIcon(getClass().getResource("../images/interpolation.png")));

        btn1.setToolTipText("Rectangles & Squares");
        btn2.setToolTipText("Ellipses & Circles");
        btn3.setToolTipText("Triangles");
        btn4.setToolTipText("Stars");
        btn5.setToolTipText("Regular & Irregular polygons");

        btn6.setToolTipText("Select");
        btn7.setToolTipText("Group");
        btn8.setToolTipText("Copy");
        btn9.setToolTipText("Delete");

        btn10.setToolTipText("Rotation");
        btn11.setToolTipText("Scale Factor");
        btn12.setToolTipText("Shearing");

        btn13.setToolTipText("Union");
        btn14.setToolTipText("Intersection");
        btn15.setToolTipText("Substraction");
        btn16.setToolTipText("Exclusion");
        btn17.setToolTipText("Inclusion");
        btn18.setToolTipText("Interpolation");


        btn1.addActionListener(new GestionnaireToolBar(ButtonPressed.DRAW_RECTANGLE, optionZone));
        btn2.addActionListener(new GestionnaireToolBar(ButtonPressed.DRAW_ELLIPSE, optionZone));
        btn3.addActionListener(new GestionnaireToolBar(ButtonPressed.DRAW_TRIANGLE, optionZone));
        btn4.addActionListener(new GestionnaireToolBar(ButtonPressed.DRAW_STAR, optionZone));
        btn5.addActionListener(new GestionnaireToolBar(ButtonPressed.DRAW_POLYGON, optionZone));
        btn6.addActionListener(new GestionnaireToolBar(ButtonPressed.MODE_SELECTION, optionZone));
        btn7.addActionListener(new GestionnaireToolBar(ButtonPressed.GROUP, optionZone));
        btn8.addActionListener(new GestionnaireToolBar(ButtonPressed.COPY, optionZone));
        btn9.addActionListener(new GestionnaireToolBar(ButtonPressed.DELETE, optionZone));
        btn10.addActionListener(new GestionnaireToolBar(ButtonPressed.ROTATE, optionZone));
        btn11.addActionListener(new GestionnaireToolBar(ButtonPressed.SCALE, optionZone));
        btn12.addActionListener(new GestionnaireToolBar(ButtonPressed.SHEAR, optionZone));
        btn13.addActionListener(new GestionnaireToolBar(ButtonPressed.UNION, optionZone));
        btn14.addActionListener(new GestionnaireToolBar(ButtonPressed.INTERSECTION, optionZone));
        btn15.addActionListener(new GestionnaireToolBar(ButtonPressed.SUBSTRACTION, optionZone));
        btn16.addActionListener(new GestionnaireToolBar(ButtonPressed.EXCLUSION, optionZone));
        btn17.addActionListener(new GestionnaireToolBar(ButtonPressed.INCLUSION, optionZone));
        btn18.addActionListener(new GestionnaireToolBar(ButtonPressed.INTERPOLATION, optionZone));
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
        this.addSeparator();
        this.add(btn6);
        this.add(btn7);
        this.add(btn8);
        this.add(btn9);
        this.addSeparator();
        this.add(btn10);
        this.add(btn11);
        this.add(btn12);
        this.addSeparator();
        this.add(btn13);
        this.add(btn14);
        this.add(btn15);
        this.add(btn16);
        this.add(btn17);
        this.add(btn18);

        this.setBorder(BorderFactory.createEtchedBorder());
    }
}
