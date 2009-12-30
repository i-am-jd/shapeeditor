package Draw;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.Point;


public class Rectangle extends SceneShape
 {
    private Rectangle2D.Double rect;

    public Rectangle(View v, double x, double y, double width, double height) {
        super(v);
        System.out.println("rectangle");
        rect = new Rectangle2D.Double(x ,y, width, height);
        shape = rect;
    }

    public Rectangle(Rectangle r) {
        super(r.view);
        rect = new Rectangle2D.Double(r.rect.x+5, r.rect.y+5, r.rect.width, r.rect.height);
        shape = rect;
    }

    @Override
    public Rectangle clone()
    {
        return new Rectangle(this);
    }

    @Override
    public void setLocation(Point2D p)
    {
        rect.x = p.getX() - offset.getX();
        rect.y = p.getY() - offset.getY();
    }

    @Override
       public void setOffset(Point p)
    {
        offset.x = p.x - (int) rect.x;
        offset.y = p.y - (int) rect.y;
    }

    @Override
    public double getOriginX()
    {
        return rect.x+rect.width/2;
    }
    @Override
    public double getOriginY()
    {
        return rect.y+rect.height/2;
    }

    @Override
     public double getRadius()
    {
        return Math.sqrt(Math.pow(rect.height/2, 2)+Math.pow(rect.width/2, 2));
    }
}
