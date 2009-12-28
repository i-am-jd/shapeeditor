package Draw;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.Point;


public class Ellipse extends SceneShape {

    private Ellipse2D.Double ellipse;

    public Ellipse(View v, double x, double y, double width, double height) {
        super(v);
        ellipse = new Ellipse2D.Double(x, y, width, height);
        shape = ellipse;
    }

    public double getSemiMajorAxis()
    {
        return this.getWidth() / 2;
    }

    public double getSemiMinorAxis()
    {
        return this.getHeight() / 2;
    }

    public void setLocation(Point2D p)
    {
        ellipse.x = p.getX() - offset.getX();
        ellipse.y = p.getY() - offset.getY();
    }


    public void setOffset(Point p)
    {
        offset.x = p.x - (int) ellipse.x;
        offset.y = p.y - (int) ellipse.y;
    }
}
