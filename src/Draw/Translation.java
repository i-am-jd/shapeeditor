package Draw;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Translation d'un sous-graphe de scène.
 * Contient nécessairement un seul sous-graphe
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Translation extends UnaryOperation {

    /** Déplacements selon les axes horizontal et vertical */
    private double dx;
    private double dy;

    /** 
     * Constructeur par défaut
     * @param child sous-graphe à modifier
     */
    public Translation(SceneGraph child)
    {
        super("Translation", child);
        dx = 0;
        dy = 0;
    }

    /** Modifie la translation */
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
