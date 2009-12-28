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

    int originX;
    int originY;

    public IrregularPolygon (View v, int[][] vertices)
    {
        super(v);

        originX = vertices[0][0];
        originY = vertices[1][0];
        polygon = new Polygon(vertices[0], vertices[1], vertices[0].length);
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
