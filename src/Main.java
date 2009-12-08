import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Draw.*;
import IHM.*;

/**
 * Application Editeur de formes geometriques
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Main
{
	
	/**
	 * Programme principal.
	 * Instancie une nouvelle {@link Window} en utilisant le {@link UIManager} avec le look and feel
	 * @param args Arguments du programme
	 */
	public static void main(String[] args) 
	{
		/* Mise en place du look and feel en fonction de l'OS */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		/* Instanciation d'une nouvelle fenetre */
		Window w = new Window();
	}

}

