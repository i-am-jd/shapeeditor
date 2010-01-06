package Draw;

import java.awt.geom.Rectangle2D;


public class Rectangle extends SceneShape implements PolygonShape
{
    private Rectangle2D.Double rect;

    int[] xs = new int[4];
    int[] ys = new int[4];

    public Rectangle(View v, double x, double y, double width, double height) {
        super(v);
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

    @Override
    public int getPointsNb() {
        return 4;
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
