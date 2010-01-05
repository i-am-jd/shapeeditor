package Draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.Shape;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author jdkoeck
 */
public abstract class SceneShape extends SceneGraph {

    final View view;

    //Forme de base, aucune transformation appliquée
    protected Shape baseShape;

    //Forme affichée à l'écran, à laquelle on a appliquée les transformations nécessaires
    protected Shape shape;

    protected Point offset = new Point();

    public SceneShape(View v)
    {
        super(v, "Shape");
        /* Une forme est une feuille du graphe de scene */
        view = v;
        //System.out.println("Creation de shape");
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
    public void applyTransform(AffineTransform trans)
    {
        shape = trans.createTransformedShape(baseShape);
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

    /*
    @Override
     public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY)
     {
         AffineTransform saveAT = g2d.getTransform();
         AffineTransform newAT = new AffineTransform();

         newAT.rotate(rotate, getOriginX(), getOriginY());
         newAT.scale(scaleX, scaleY);
         newAT.shear(shearX, shearY);
         //newAT.translate(a, b);
        g2d.setTransform(newAT);
        
          if (view.getFillPattern()==null) {
            g2d.setPaint(view.getFillColor());
        } else { // view.getFillPattern() != null
            g2d.setPaint(view.getFillPattern());
        }
        g2d.fill(shape);

        g2d.setStroke(new BasicStroke(view.getLineWidth()));
        g2d.setColor(view.getLineColor());
        g2d.draw(shape);

        g2d.setTransform(saveAT);
     }*/

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
}