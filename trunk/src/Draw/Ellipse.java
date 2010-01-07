package Draw;

import java.awt.geom.Ellipse2D;


/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Ellipse extends SceneShape {

    private Ellipse2D.Double ellipse;

    /**
     *
     * @param v
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Ellipse(View v, double x, double y, double width, double height) {
        super(v);
        ellipse = new Ellipse2D.Double(x, y, width, height);
        shape = ellipse;
        baseShape = ellipse;

        setUserObject("Ellipse");
    }

    /**
     *
     * @param e
     */
    public Ellipse(Ellipse e) {
        super(e.view);
        ellipse = new Ellipse2D.Double(e.ellipse.x+5, e.ellipse.y+5, e.ellipse.width, e.ellipse.height);
        shape = ellipse;
    }
}
