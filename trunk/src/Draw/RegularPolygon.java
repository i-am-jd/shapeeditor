/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Draw;

import java.awt.Polygon;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class RegularPolygon extends SceneShape implements PolygonShape
{
    int nbSides;
    final Polygon polygon;

    double originX;
    double originY;

    int[][] tab;

    /**
     *
     * @param v
     * @param x
     * @param y
     * @param rad
     * @param n
     */
    public RegularPolygon(View v, double x, double y, double rad, int n)
    {
        super(v, "Regular Polygon");

        originX = x;
        originY = y;

        nbSides = n;

        tab = new int[2][nbSides];
        int[] origin = {(int) x, (int) y};
        int r = (int) rad;
        double angle = Math.PI/2;
        for (int i=0; i<this.nbSides; i++) {
            /* Calcul des coordonnees du point : utilisation des coordonnees polaires */
            tab[0][i] = origin[0] - (int)((double)r*Math.cos(angle));
            tab[1][i] = origin[1] - (int)((double)r*Math.sin(angle));
            angle += 2*Math.PI/this.nbSides;
        }

        polygon = new Polygon(tab[0], tab[1], nbSides);
        shape = polygon;
        baseShape = polygon;

        setUserObject("RegularPolygon");
    }

    /**
     *
     * @param p
     */
    public RegularPolygon(RegularPolygon p) {
        super(p.view, "Regular Polygon");
        originX = p.originX + 5;
        originY = p.originY + 5;
        polygon = new Polygon(p.polygon.xpoints, p.polygon.ypoints, p.polygon.npoints);
        polygon.translate(5, 5);
        shape = polygon;
        baseShape = polygon;
    }

     @Override
    public RegularPolygon clone()
    {
        return new RegularPolygon(this);
    }

     /**
      *
      * @return
      */
     @Override
    public int getPointsNb() {
        return nbSides;
    }

     /**
      * 
      * @param i
      * @return
      */
     @Override
    public int getX(int i) {
        return tab[0][i];
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public int getY(int i) {
        return tab[1][i];
    }
}
