package Draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Stack;


public class BinaryOperation extends Transformation
{
    Area area;
    Shape shape;
    Shape baseShape;

    public BinaryOperation(String s, SceneGraph sg1, SceneGraph sg2)
    {
        super(s);

        this.add(sg1);
        this.add(sg2);

    }

    public double getWidth()
    {
        return shape.getBounds2D().getWidth() / 2;
    }

    public double getHeight()
    {
        return shape.getBounds2D().getHeight() / 2;
    }

    @Override
    public boolean contains(Point2D p)
    {
        return shape.contains(p);
    }

    @Override
    public Rectangle2D getBounds2D()
    {
        return shape.getBounds2D();
    }

    @Override
    public void translate(double x, double y)
    {
        AffineTransform t = AffineTransform.getTranslateInstance(x, y);
        shape = t.createTransformedShape(shape);
        baseShape = t.createTransformedShape(baseShape);
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        if (view.getFillPattern()==null) {
            g2d.setPaint(view.getFillColor());
        } else { // view.getFillPattern() != null
            g2d.setPaint(view.getFillPattern());
        }
        g2d.fill(shape);

        g2d.setStroke(new BasicStroke(view.getLineWidth()));
        g2d.setColor(view.getLineColor());
        g2d.draw(shape);
    }

    @Override
    public double getBarycenterX()
    {
        return shape.getBounds2D().getCenterX();
    }

    @Override
    public double getBarycenterY()
    {
        return shape.getBounds2D().getCenterY();
    }

    @Override
    public Shape getShape()
    {
        return shape;
    }

    //Applique une pile de transformations à la shape
    @Override
    public void applyUnaryOperations(Stack<UnaryOperation> ops)
    {
        shape = baseShape;
        for(Enumeration<UnaryOperation> en = ops.elements(); en.hasMoreElements();) {
            ArrayList<AffineTransform> afl = en.nextElement().toAffineTransforms(shape);
            for(AffineTransform af : afl) {
                shape = af.createTransformedShape(shape);
            }
        }
    }
}
