package Draw;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;


public class Rectangle extends SceneShape
 {
    private Rectangle2D.Double rect;

    public Rectangle(View v, double x, double y, double width, double height) {
        super(v);
        System.out.println("rectangle");
        rect = new Rectangle2D.Double(x ,y, width, height);
        baseShape = rect;
        shape = rect;
    }
}
