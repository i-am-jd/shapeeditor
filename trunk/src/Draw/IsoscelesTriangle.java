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
public class IsoscelesTriangle extends SceneShape implements Interpolatable {

    final Polygon polygon;

    int[] xs;
    int[] ys;

    /**
     *
     * @param v
     * @param xpoints
     * @param ypoints
     */
    public IsoscelesTriangle(View v, int[] xpoints, int[] ypoints)
    {
        super(v, "Isosceles Triangle");
        this.xs = xpoints;
        this.ys = ypoints;
        polygon = new Polygon(xpoints, ypoints, 3);
        shape = polygon;
        baseShape = polygon;
    }
    
    /**
     *
     * @param v
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public IsoscelesTriangle(View v, double x, double y, double width, double height) {
        super(v, "Isosceles Triangle");

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

    /**
     * Constructeur de copie
     * L'objet est décalé de (5,5) pixels par rapport au triangle isocèle copié
     * @param t triangle à copier
     */
    public IsoscelesTriangle(IsoscelesTriangle t) {
        super(t.getView(), "Isosceles Triangle");
        polygon = new Polygon(t.polygon.xpoints, t.polygon.ypoints, t.polygon.npoints);
        polygon.translate(5, 5);
        shape = polygon;
        baseShape = polygon;
    }

    /**
     * Copie le triangle isocèle courant
     * @return une copie décalée de (5,5) pixels
     */
     @Override
    public IsoscelesTriangle clone()
    {
        return new IsoscelesTriangle(this);
    }

    //Méthodes de l'interface Interpolatable

    @Override
    public int getPointsNb() {
        return 3;
    }

    @Override
     public int getX(int i) {
        return xs[i];
     }

    @Override
     public int getY(int i) {
         return ys[i];
     }
     
}
