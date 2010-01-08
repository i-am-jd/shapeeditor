package Draw;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Redimensionnement d'un sous-graphe de scène.
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Scale extends UnaryOperation
{

    /** Facteurs de redimensionnement */
    private double factorX;
    private double factorY;

    /** Coordonnées du centre de la figure à redimensionner */
    private double centerX;
    private double centerY;

    /** Largeur et hauteur de la figure à redimensionner */
    private double initWidth;
    private double initHeight;

    /**
     * Constructeur recevant le sous-graphe à modifier
     * @param child sous-graphe soumis au redimensionnement
     */
    public Scale(SceneGraph child)
    {
        super("Scale", child);
        factorX = 1;
        factorY = 1;
    }

    /**
     * Redimensionne le sous-graphe par rapport à un point.
     * Les paramètres de redimensionnement dépendent de la distance du point au centre
     * du sous-graphe modifié, et des dimensions de ce dernier.
     * @param p point par rapport auquel on effectue le redimensionnement
     */
    public void scaleTo(Point p)
    {
        this.factorX = (p.getX() - centerX)/(this.initWidth / 2);
        this.factorY = (p.getY() - centerY)/(this.initHeight / 2);
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
