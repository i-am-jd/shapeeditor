package Draw;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Noeud représentant un cisaillement.
 * Contient nécessairement un seul sous-graphe
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Shear extends UnaryOperation {

    /** Facteurs de cisaillement */
    protected double factorX;
    protected double factorY;

    /** Coordonnées du centre de la figure à redimensionner */
    private double centerX;
    private double centerY;

    /** Largeur et hauteur de la figure à redimensionner */
    private double initWidth;
    private double initHeight;

    /**
     * Constructeur recevant le sous-graphe à modifier
     * @param child sous-graphe soumis au cisaillement
     */
    public Shear(SceneGraph child)
    {
        super("Shear", child);
        factorX = 1;
        factorY = 1;
    }

    /**
     * Cisaille le sous-graphe par rapport à un point.
     * Les facteurs de cisaillement dépendent de la distance du point au centre
     * du sous-graphe modifié, et des dimensions de ce dernier.
     * @param p point par rapport auquel on effectue le cisaillement
     */
    public void shearTo(Point to)
    {
        this.factorX = (to.getX() - centerX)/(this.initWidth / 2);
        this.factorY = (to.getY() - centerY)/(this.initHeight / 2);
    }

    @Override
    protected ArrayList<AffineTransform> toAffineTransforms(Shape s)
    {
        ArrayList<AffineTransform> al = new ArrayList();
	Rectangle2D r = s.getBounds2D();
        al.add(AffineTransform.getTranslateInstance(-r.getCenterX(), -r.getCenterY()));
        al.add(AffineTransform.getShearInstance(factorX,factorY));
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
