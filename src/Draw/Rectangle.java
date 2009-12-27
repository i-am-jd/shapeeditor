package Draw;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;


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
        rect.x = p.getX();
        rect.y = p.getY();
    }
}
