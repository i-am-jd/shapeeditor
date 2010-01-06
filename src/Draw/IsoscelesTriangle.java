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
public class IsoscelesTriangle extends SceneShape implements PolygonShape {

    final Polygon polygon;

    int[] xs;
    int[] ys;

    public IsoscelesTriangle(View v, int[] xpoints, int[] ypoints)
    {
        super(v);
        this.xs = xpoints;
        this.ys = ypoints;
        polygon = new Polygon(xpoints, ypoints, 3);
        shape = polygon;
        baseShape = polygon;
    }
    
    public IsoscelesTriangle(View v, double x, double y, double width, double height) {
        super(v);

        xs = new int[3];
        ys = new int[3];

        xs[0] = (int) (x + width/2);
        ys[0] = (int) y;
        xs[1] = (int) x;
        ys[1] = (int) (y+height);
        xs[2] = (int) (x + width);
        ys[2] = (int) (y+height);

        polygon = new Polygon(xs, ys, 3);
        shape = polygon;
        baseShape = polygon;

        setUserObject("IsoscelesTriangle");
    }

     public IsoscelesTriangle(IsoscelesTriangle t) {
        super(t.view);
        polygon = new Polygon(t.polygon.xpoints, t.polygon.ypoints, t.polygon.npoints);
        polygon.translate(5, 5);
        shape = polygon;
        baseShape = polygon;
    }

    @Override
     public int getX(int i) {
        return xs[i];
     }

    @Override
     public int getY(int i) {
         return ys[i];
     }
     
     @Override
    public IsoscelesTriangle clone()
    {
        return new IsoscelesTriangle(this);
    }

    @Override
    public int getPointsNb() {
        return 3;
    }
}
