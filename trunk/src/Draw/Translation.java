package Draw;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Translation extends UnaryOperation {

    //TRANSLATE
    private double dx;
    private double dy;

    public Translation(SceneGraph child)
    {
        super("Translation", child);
        dx = 0;
        dy = 0;
    }

    public void translate(double dx, double dy)
    {
        this.dx += dx;
        this.dy += dy;
    }

    @Override
    protected ArrayList<AffineTransform> toAffineTransforms(Shape s)
    {
        ArrayList<AffineTransform> al = new ArrayList();
	al.add(AffineTransform.getTranslateInstance(dx, dy));
	return al;
    }

    @Override
    public void beginEditNode(SceneGraph child)
    {
    }
}