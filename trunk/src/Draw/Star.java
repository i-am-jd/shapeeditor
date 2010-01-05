package Draw;

import java.awt.Polygon;


public class Star extends SceneShape {

    final Polygon polygon;

    double originX;
    double originY;

    public Star (View v, double x, double y, double r, int nbBranch)
    {
        super(v);

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
    }

    public Star (Star s) {
        super(s.view);
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
