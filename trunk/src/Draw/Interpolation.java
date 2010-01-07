package Draw;

import java.awt.Polygon;


/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Interpolation extends BinaryOperation implements PolygonShape
{
    int[] xs;
    int[] ys;
    int n;

    /**
     *
     * @param sg1
     * @param sg2
     */
    public Interpolation(SceneGraph sg1, SceneGraph sg2)
    {
        super("Interpolation", sg1, sg2);
        
        /*if(sg1 instanceof Rectangle && sg2 instanceof Rectangle) {
            Rectangle2D r1 = (Rectangle2D) ((Rectangle) sg1).getShape();
            Rectangle2D r2 = (Rectangle2D) ((Rectangle) sg2).getShape();

            double minx = (r1.getMinX() + r2.getMinX()) / 2;
            double miny = (r1.getMinY() + r2.getMinY()) / 2;
            double width = (r1.getWidth() + r2.getWidth()) / 2;
            double height = (r1.getHeight() + r2.getHeight()) / 2;
            Rectangle2D rect = new Rectangle2D.Double(minx, miny, width, height);
            shape = rect;
            baseShape = rect;
        }*/

        PolygonShape p1 = (PolygonShape) sg1;
        PolygonShape p2 = (PolygonShape) sg2;
        assert(p1.getPointsNb() == p2.getPointsNb());
        n = p1.getPointsNb();
        xs = new int[n];
        ys = new int[n];
        for(int i = 0; i < n; i++) {
            xs[i] = (p1.getX(i) + p2.getX(i))/2;
            ys[i] = (p1.getY(i) + p2.getY(i))/2;
        }
        shape = new Polygon(xs, ys, p1.getPointsNb());
        baseShape = new Polygon(xs, ys, p1.getPointsNb());
    }

    /**
     *
     * @return
     */
    @Override
    public int getPointsNb() {
        return n;
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

