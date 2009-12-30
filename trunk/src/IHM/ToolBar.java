package IHM;

import Gestionnaire.*;
import Gestionnaire.GestionnaireToolBar.ButtonPressed;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar
{	
	private JButton btn1 = new JButton("Rectangles");
	private JButton btn2 = new JButton("Ellipses");
	private JButton btn3 = new JButton("Triangles");
	private JButton btn4 = new JButton("Stars");
	private JButton btn5 = new JButton("Others");
        
	private JButton btn6 = new JButton("Sélection");
        private JButton btn7 = new JButton("Group");
        private JButton btn8 = new JButton("Copy");
        private JButton btn9 = new JButton("Delete");

        private JButton btn10 = new JButton("Rotate");
        private JButton btn11 = new JButton("Scale");
        private JButton btn12 = new JButton("Shear");

        private JButton btn13 = new JButton("Union");
        private JButton btn14 = new JButton("Intersection");
        private JButton btn15 = new JButton("Substraction");
        private JButton btn16 = new JButton("Exclusion");
        private JButton btn17 = new JButton("Inclusion");
        private JButton btn18 = new JButton("Interpolation");

	public ToolBar(OptionPanel optionZone)
	{
		/* Creation de la barre d'outils avec une orientation verticale */
		super(1);
                
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
