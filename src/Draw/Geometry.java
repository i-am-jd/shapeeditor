package Draw;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;


/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public interface Geometry {
    /*
    public void fillGeometry(Graphics2D g2d);
    public void drawGeometry(Graphics2D g2d);*/

    /**
     *
     * @param p
     * @return
     */
    boolean contains(Point2D p);
    /**
     *
     * @param p
     */
    void setLocation(Point2D p);
}
