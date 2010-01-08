package Draw;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Rotation d'un sous-graphe de scène.
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Rotation extends UnaryOperation {

    /** Angle de rotation */
    private double angle;

    /** Coordonnées du point par rapport auquel on effectue la rotation */
    private double anchorX;
    private double anchorY;

    /**
     * Constructeur par défaut
     * @param child sous-graphe soumis à la rotation
     */
    public Rotation(SceneGraph child)
    {
        super("Rotation", child);
        angle = 0;
    }

    /**
     * Modifie l'angle de rotation
     * @param angleDiff différence de rotation
     */
    public void rotate(double angleDiff)
    {
        angle += angleDiff;
        update();
    }

    /**
     * Calcule la différence d'angle entre les deux points puis l'ajoute
     * à l'angle de rotation courant
     */
    public void rotateFromTo(Point last, Point here)
    {
        double angleLast = Math.atan2(last.getX() - anchorX, last.getY() - anchorY);
        double angleHere = Math.atan2(here.getX() - anchorX, here.getY() - anchorY);
        this.rotate(angleLast - angleHere);
    }
    
    @Override
    protected ArrayList<AffineTransform> toAffineTransforms(Shape s)
    {
        ArrayList<AffineTransform> al = new ArrayList();
        al.add(AffineTransform.getRotateInstance(angle, anchorX, anchorY));
        return al;
    }

    @Override
    public void beginEditNode(SceneGraph child)
    {
        this.anchorX = child.getBarycenterX();
        this.anchorY = child.getBarycenterY();
    }
}
