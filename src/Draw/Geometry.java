package Draw;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;


public interface Geometry {
    /*
    public void fillGeometry(Graphics2D g2d);
    public void drawGeometry(Graphics2D g2d);*/

    boolean contains(Point2D p);
    void setLocation(Point2D p);
}
