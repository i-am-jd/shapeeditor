package Draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Intersection extends BinaryOperation
{

    public Intersection(SceneGraph sg1, SceneGraph sg2)
    {
        super("Intersection", sg1, sg2);
        this.area = new Area(sg1.getShape());
        area.intersect(new Area(sg2.getShape()));
        shape = area;
        baseShape = area;
    }
    
}
