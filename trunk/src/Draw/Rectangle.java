package Draw;

import java.awt.geom.Rectangle2D;


/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Rectangle extends SceneShape implements PolygonShape
{
    private Rectangle2D.Double rect;

    int[] xs = new int[4];
    int[] ys = new int[4];

    /**
     *
     * @param v
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Rectangle(View v, double x, double y, double width, double height) {
        super(v, "Rectangle");
        rect = new Rectangle2D.Double(x ,y, width, height);
        baseShape = rect;
        shape = rect;

        double minx = x;
        double maxx = rect.getMaxX();
        double miny = y;
        double maxy = rect.getMaxY();

        xs[0] = (int) minx;
        ys[0] = (int) miny;
        xs[1] = (int) maxx;
        ys[1] = (int) miny;
        xs[2] = (int) maxx;
        ys[2] = (int) maxy;
        xs[3] = (int) minx;
        ys[3] = (int) maxy;

        setUserObject("Rectangle");
    }

    /**
     *
     * @return
     */
    @Override
    public int getPointsNb() {
        return 4;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public int getX(int i) {
        return xs[i];
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public int getY(int i) {
        return ys[i];
    }
    
}
