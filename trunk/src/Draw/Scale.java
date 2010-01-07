package Draw;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Scale extends UnaryOperation
{

    protected double factorX;
    /**
     *
     */
    protected double factorY;
    private double centerX;
    private double centerY;
    private double initWidth;
    private double initHeight;

    /**
     *
     * @param child Child of the shearing transformation
     */
    public Scale(SceneGraph child)
    {
        super("Scale", child);
        factorX = 1;
        factorY = 1;
    }

    /**
     *
     * @param to
     */
    public void scaleTo(Point to)
    {
        this.factorX = (to.getX() - centerX)/(this.initWidth / 2);
        this.factorY = (to.getY() - centerY)/(this.initHeight / 2);
        System.out.println(factorX);
        System.out.println(factorY);
    }

    @Override
    protected ArrayList<AffineTransform> toAffineTransforms(Shape s)
    {
	ArrayList<AffineTransform> al = new ArrayList();
	Rectangle2D r = s.getBounds2D();
	al.add(AffineTransform.getTranslateInstance(-r.getCenterX(), -r.getCenterY()));
	al.add(AffineTransform.getScaleInstance(factorX,factorY));
	al.add(AffineTransform.getTranslateInstance(r.getCenterX(), r.getCenterY()));
	return al;
    }

    @Override
    public void beginEditNode(SceneGraph child)
    {
        centerX = child.getBarycenterX();
        centerY = child.getBarycenterY();
        initWidth = child.getBounds2D().getWidth();
        initHeight = child.getBounds2D().getHeight();
    }
}
