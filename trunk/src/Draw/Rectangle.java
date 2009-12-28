package Draw;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.Point;


public class Rectangle extends SceneShape
 {
    private Rectangle2D.Double rect;

    public Rectangle(View v, double x, double y, double width, double height) {
        super(v);
        rect = new Rectangle2D.Double(x ,y, width, height);
        shape = rect;
    }

    public void setLocation(Point2D p)
    {
        rect.x = p.getX() - offset.getX();
        rect.y = p.getY() - offset.getY();
    }

       public void setOffset(Point p)
    {
        offset.x = p.x - (int) rect.x;
        offset.y = p.y - (int) rect.y;
    }
}
