package Draw;

import java.awt.Polygon;


/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Star extends SceneShape {

    final Polygon polygon;

    double originX;
    double originY;

    /**
     *
     * @param v
     * @param x
     * @param y
     * @param r
     * @param nbBranch
     */
    public Star(View v, double x, double y, double r, int nbBranch)
    {
        super(v, "Star");

        originX = x;
        originY = y;

        int nbSides = 2*nbBranch;
        int[] xpoints = new int[nbSides];
        int[] ypoints = new int[nbSides];
        double angle = Math.PI/2;

        for (int i=0; i<nbSides; i++) {
            if (i%2==0) {
                xpoints[i] = (int) x - (int)((double)r*Math.cos(angle));
                ypoints[i] = (int) y - (int)((double)r*Math.sin(angle));
            } else {
                xpoints[i] = (int) x - (int)((double)2*r/6*Math.cos(angle));
                ypoints[i] = (int) y - (int)((double)2*r/6*Math.sin(angle));
            }
            angle += Math.PI/nbBranch;
        }

        polygon = new Polygon(xpoints, ypoints, nbSides);
        shape = polygon;
        baseShape = polygon;

        setUserObject("Star");
    }

    /**
     *
     * @param s
     */
    public Star(Star s) {
        super(s.view, "Star");
        originX = s.originX + 5;
        originY = s.originY + 5;
        polygon = new Polygon(s.polygon.xpoints, s.polygon.ypoints, s.polygon.npoints);
        polygon.translate(5, 5);
        shape = polygon;
    }

    @Override
    public Star clone()
    {
        return new Star(this);
    }

}
