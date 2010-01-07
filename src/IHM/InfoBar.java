package IHM;

import java.awt.GridLayout;

import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.geom.Point2D;

/**
 * Class instantiating the information bar, which displays
 * information
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class InfoBar extends JPanel
{

    /** First label */
    private JLabel info;
    /** Second label */
    private JLabel coordinates;
    /** Format to print the coordinates with */
    private final static String NUMBERFORMAT = "000";

    /**
     * Returns an information bar
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
    
    /**
     * Prints the coordinates of the mouse, when it is in the draw panel
     * @param x The x coordinate where the mouse is
     * @param y The y coordinate where the mouse is
     */
    public void printCoordinates(float x, float y)
    {
        DecimalFormat coordFormat = new DecimalFormat(NUMBERFORMAT);
        String xs = coordFormat.format(x);
        String ys = coordFormat.format(y);
        coordinates.setText("x : " + xs + " y : " + ys);
    }

    /**
     * Prints the coordinates of the mouse, when it is in the draw panel
     * @param p The point where the mouse is
     */
    public void printCoordinates(Point2D p)
    {
        DecimalFormat coordFormat = new DecimalFormat(NUMBERFORMAT);
        String xs = coordFormat.format(p.getX());
        String ys = coordFormat.format(p.getY());
        coordinates.setText("x : " + xs + " y : " + ys);
    }

    /**
     * Prints a message (first label)
     * @param message
     */
    public void printMessage(String message)
    {
        info.setText(message);
    }

    /**
     * Prints a default message when the mouse is not in the draw panel
     */
    public void printDefaultCoordinates() {
        coordinates.setText("x : ___ y : ___");
    }
}
