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


/**
 * Transformation combinant deux sous-graphes de scène.
 * Ce noeud du graphe de scène contient nécessairement deux sous-graphes.
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class BinaryOperation extends Transformation
{

    /**
     * Forme géométrique initiale.
     */
    protected Shape shape;

    /**
     * Forme géométrique résultant de l'application successive des transformations
     * unaires à la forme de base.
     */
    protected Shape baseShape;

    /**
     * Constructeur
     * @param name nom de la transformation binaire
     * @param sg1  premier sous-graphe
     * @param sg2  deuxième sous-graphe
     */
    public BinaryOperation(String name, SceneGraph sg1, SceneGraph sg2)
    {
        super(sg1.getView(), name);

        this.add(sg1);
        this.add(sg2);

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
    public void draw(Graphics2D g2d)
    {
        if (view.getFillPattern()==null) {
            g2d.setPaint(view.getFillColor());
        } else {
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

    /**
     * Calcule la figure résultant de la transformation appliquée aux deux sous-graphes
     * @return la figure résultant de la transformation appliquée aux deux sous-graphes
     */
    @Override
    public Shape getShape()
    {
        return shape;
    }

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
