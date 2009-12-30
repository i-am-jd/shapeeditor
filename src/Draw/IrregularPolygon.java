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
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class IrregularPolygon extends SceneShape {
    protected Polygon polygon;

    public IrregularPolygon (View v, int[][] vertices)
    {
        super(v);

        polygon = new Polygon(vertices[0], vertices[1], vertices[0].length);
        shape = polygon; 
    }

     public IrregularPolygon (IrregularPolygon p) {
        super(p.view);
        polygon = new Polygon(p.polygon.xpoints, p.polygon.ypoints, p.polygon.npoints);
        polygon.translate(5, 5);
        shape = polygon;
    }

     @Override
    public IrregularPolygon clone()
    {
        return new IrregularPolygon(this);
    }

    @Override
    public void setLocation(Point2D p)
    {
        polygon.translate((int) (p.getX() - polygon.xpoints[0]), (int) (p.getY() - polygon.ypoints[0]));
    }

    @Override
    public void setOffset(Point p)
    {
        
    }

    @Override
    public double getOriginX()
    {
        return polygon.xpoints[0];
    }
    @Override
    public double getOriginY()
    {
        return polygon.xpoints[1];
    }

    @Override
     public double getRadius()
    {
        return 1;
    }

}
