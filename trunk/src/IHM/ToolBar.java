package IHM;

import Draw.*;
import Gestionnaire.*;
import Gestionnaire.GestionnaireToolBar.ButtonPressed;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar
{	
	private JButton btn1 = new JButton("B1");
	private JButton btn2 = new JButton("B2");
	private JButton btn3 = new JButton("B3");
	private JButton btn4 = new JButton("B4");
	private JButton btn5 = new JButton("B5");
	//private JButton btn6 = new JButton("B6");
	
	public ToolBar(OptionPanel optionZone)
	{
		/* Creation de la barre d'outils avec une orientation verticale */
		super(1);
				
                btn1.addActionListener(new GestionnaireToolBar(ButtonPressed.DRAW_RECTANGLE, optionZone));
                btn2.addActionListener(new GestionnaireToolBar(ButtonPressed.DRAW_ELLIPSE, optionZone));
                btn3.addActionListener(new GestionnaireToolBar(ButtonPressed.DRAW_TRIANGLE, optionZone));
                btn4.addActionListener(new GestionnaireToolBar(ButtonPressed.DRAW_STAR, optionZone));
                btn5.addActionListener(new GestionnaireToolBar(ButtonPressed.DRAW_POLYGON, optionZone));
		this.add(btn1);
		this.add(btn2);
		this.add(btn3);
		this.add(btn4);
		this.add(btn5);
		//this.add(btn6);

                //this.setBorder(BorderFactory.createEtchedBorder());
	}
	
}
