/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Draw;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.Polygon;

/**
 *
 * @author Boris
 */
public class RightAngledTriangle extends SceneShape {
    
    final Polygon polygon;

    int originX;
    int originY;

    public RightAngledTriangle(View v, double x, double y, double width, double height) {
        super(v);

        int[] xpoints = new int[3];
        int[] ypoints = new int[3];

        xpoints[0] = originX = (int) x;
        ypoints[0] = originY = (int) y;
        xpoints[1] = (int) x;
        ypoints[1] = (int) (y+height);
        xpoints[2] = (int) (x+width);
        ypoints[2] = (int) (y+height);

        polygon = new Polygon(xpoints, ypoints, 3);
        shape = polygon;
    }

    @Override
    public void setLocation(Point2D p)
    {
        polygon.translate((int) (p.getX() - originX), (int) (p.getY() - originY));
        originX = (int) p.getX();
        originY = (int) p.getY();
    }

    @Override
       public void setOffset(Point p)
    {
    }
}
