package Draw;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.Polygon;


public class Star extends SceneShape {

    final Polygon polygon;

    public Star (View v, double x, double y, double r, int nbBranch)
    {
        super(v);

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
    }

    @Override
    public void setLocation(Point2D p)
    {
        //Not implemented
    }

    @Override
    public void setOffset(Point p)
    {

    }
}
