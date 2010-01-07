package Draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;


import java.awt.Shape;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Stack;

/**
 * Implante une forme géométrique qui est feuille du graphe de scène.
 * 
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public abstract class SceneShape extends SceneGraph {

    final View view;

    //Forme de base, aucune transformation appliquée
    /**
     *
     */
    protected Shape baseShape;

    //Forme affichée à l'écran, à laquelle on a appliquée les transformations nécessaires
    /**
     *
     */
    protected Shape shape;

    /**
     *
     */
    protected Point offset = new Point();

    /**
     *
     * @param v
     */
    public SceneShape(View v)
    {
        super(v, "Shape");
        /* Une forme est une feuille du graphe de scene */
        view = v;
        //System.out.println("Creation de shape");
    }

    /**
     *
     * @param v
     * @param s
     */
    public SceneShape(View v, String s)
    {
        super(v, s);
        view = v;
    }

    /**
     *
     * @return
     */
    public double getWidth()
    {
        return shape.getBounds2D().getWidth() / 2;
    }

    /**
     *
     * @return
     */
    public double getHeight()
    {
        return shape.getBounds2D().getHeight() / 2;
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public boolean contains(Point2D p)
    {
        return shape.contains(p);
    }

    /**
     *
     * @return
     */
    @Override
    public Rectangle2D getBounds2D()
    {
        return shape.getBounds2D();
    }

    //Applique une pile de transformations à la shape
    /**
     *
     * @param ops
     */
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

    /**
     *
     * @param g2d
     */
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

    /**
     *
     * @return
     */
    @Override
    public double getBarycenterX()
    {
        return shape.getBounds2D().getCenterX();
    }

    /**
     *
     * @return
     */
    @Override
    public double getBarycenterY()
    {
        return shape.getBounds2D().getCenterY();
    }

    /**
     *
     * @return
     */
    @Override
    public Shape getShape()
    {
        return shape;
    }
}