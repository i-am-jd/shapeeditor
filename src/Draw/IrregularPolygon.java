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
public class IrregularPolygon extends SceneShape implements PolygonShape {
    protected Polygon polygon;

    int[][] vertices;

    public IrregularPolygon (View v, int[][] vertices)
    {
        super(v);

        this.vertices = vertices;

        polygon = new Polygon(vertices[0], vertices[1], vertices[0].length);
        shape = polygon;
        baseShape = polygon;

        setUserObject("IrregularPolygon");
    }

    @Override
    public int getPointsNb() {
        return vertices[0].length;
    }

    @Override
    public int getX(int i) {
        return vertices[0][i];
    }

    @Override
    public int getY(int i) {
        return vertices[1][i];
    }
}
