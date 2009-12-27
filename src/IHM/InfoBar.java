package IHM;

import java.awt.GridLayout;

import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.geom.Point2D;

/**
 * La barre d'infos situee en bas de la fenetre pour afficher 
 * differentes informations
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class InfoBar extends JPanel
{

    /** Le premier label */
    private JLabel info;
    /** Le second label */
    private JLabel coordinates;
    /**
     * le format des coordonn�es dans la barre d'�tat
     * sur 3 digits, sans d�cimales
     */
    private final static String NUMBERFORMAT = "000";

    /**
     * Constructeur de la barre d'infos
     */
    public InfoBar()
    {
        super();
        this.setLayout(new GridLayout(1, 2));
        this.add(info = new JLabel("Zone d'Information"));
        this.add(coordinates = new JLabel());
        printDefaultCoordinates();
        this.setBorder(BorderFactory.createEtchedBorder());
    }

    /* (non-Javadoc)
     * @see widgets.Etat#afficheCoord(float, float)
     */
    public void printCoordinates(float x, float y)
    {
        DecimalFormat coordFormat = new DecimalFormat(NUMBERFORMAT);
        String xs = coordFormat.format(x);
        String ys = coordFormat.format(y);
        coordinates.setText("x : " + xs + " y : " + ys);
    }

    public void printCoordinates(Point2D p)
    {
        DecimalFormat coordFormat = new DecimalFormat(NUMBERFORMAT);
        String xs = coordFormat.format(p.getX());
        String ys = coordFormat.format(p.getY());
        coordinates.setText("x : " + xs + " y : " + ys);
    }

    /* (non-Javadoc)
     * @see widgets.Etat#afficheMessage(java.lang.String)
     */
    public void printMessage(String message)
    {
        info.setText(message);
    }

    public void printDefaultCoordinates() {
        coordinates.setText("x : ___ y : ___");
    }
}
