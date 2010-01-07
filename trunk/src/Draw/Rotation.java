package Draw;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Rotation extends UnaryOperation {

    //ROTATE
    private double angle;
    //Coordonnées du point par rapport auquel on effectue la rotation
    private double anchorX;
    private double anchorY;

    public Rotation(SceneGraph child)
    {
        super("Rotation", child);
        angle = 0;
    }
    
    @Override
    protected ArrayList<AffineTransform> toAffineTransforms(Shape s)
    {
        ArrayList<AffineTransform> al = new ArrayList();
        al.add(AffineTransform.getRotateInstance(angle, anchorX, anchorY));
        return al;
    }

    public void rotate(double angleDiff)
    {
        angle += angleDiff;
        update();
    }


    @Override
    public void beginEditNode(SceneGraph child)
    {
        this.anchorX = child.getBarycenterX();
        this.anchorY = child.getBarycenterY();
    }
}
