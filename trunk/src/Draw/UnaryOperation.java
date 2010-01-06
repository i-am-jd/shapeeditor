/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Draw;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Stack;

/**
 *
 * @author jdkoeck
 */
public class UnaryOperation extends Transformation {
    public enum TransFlag { Translate, Rotate, Scale, Shear };

    TransFlag flag;

    //SCALE
    protected double factorX;
    protected double factorY;
    protected double initWidth;
    protected double initHeight;
    protected double centerX;
    protected double centerY;

    //ROTATE
    private double angle;
    //Coordonnées du point par rapport auquel on effectue la rotation
    private double anchorX;
    private double anchorY;

    //TRANSLATE
    private double dx;
    private double dy;
    
    protected ArrayList<AffineTransform> toAffineTransforms(Shape s)
    {
        ArrayList<AffineTransform> al = new ArrayList();
        Rectangle2D r;
        switch(flag) {
            case Translate:
                al.add(AffineTransform.getTranslateInstance(dx, dy));
                break;
            case Rotate:
                al.add(AffineTransform.getRotateInstance(angle, anchorX, anchorY));
                break;
            case Scale:
                r = s.getBounds2D();
                al.add(AffineTransform.getTranslateInstance(-r.getCenterX(), -r.getCenterY()));
                al.add(AffineTransform.getScaleInstance(factorX,factorY));
                al.add(AffineTransform.getTranslateInstance(r.getCenterX(), r.getCenterY()));
                //at.scale(factorX, factorY);
                //at.translate(- r.getCenterX() * factorX, - r.getCenterY() * factorY);
                //at.translate((- factorX / 2) * r.getCenterX(), (- factorY / 2) * r.getCenterY());
                //at.scale(factorX, factorY);
                //at.translate((2 - factorX) * r.getCenterX() , (2 - factorY) * r.getCenterY() );
                break;
            case Shear:
                r = s.getBounds2D();
                al.add(AffineTransform.getTranslateInstance(-r.getCenterX(), -r.getCenterY()));
                al.add(AffineTransform.getShearInstance(factorX,factorY));
                al.add(AffineTransform.getTranslateInstance(r.getCenterX(), r.getCenterY()));
        }
        return al;
    }

    protected UnaryOperation(String s, TransFlag f, SceneGraph child)
    {
        super(s);
        flag = f;
        factorX = 1;
        factorY = 1;
        angle = 0;
        anchorX = 0;
        anchorY = 0;
        dx = 0;
        dx = 1;
        centerX = child.getBarycenterX();
        centerY = child.getBarycenterY();
        initWidth = child.getBounds2D().getWidth();
        initHeight = child.getBounds2D().getHeight();

        //Ajouter l'enfant
        child.removeFromParent();
        this.add(child);

        this.view = new View(child.getView());
    }

    /*
    static public UnaryOperation rotation(double angle, double anchorX, double anchorY)
    {
        UnaryOperation t = new UnaryOperation();
        t.setRotateAnchor(anchorX, anchorY);
        t.rotate(angle);
        t.setFlag(TransFlag.Rotate);
        return t;
    }

    static public UnaryOperation translation(double dx, double dy)
    {
        UnaryOperation t = new UnaryOperation();
        t.setTranslate(dx, dy);
        t.setFlag(TransFlag.Translate);
        return t;
    }

    static public UnaryOperation scale(double dx, double dy)
    {
        UnaryOperation t = new UnaryOperation();
        t.setScale(dx, dy);
        t.setFlag(TransFlag.Scale);
        return t;
    }
     */

    @Override
    public void applyUnaryOperations(Stack<UnaryOperation> unaryOps)
    {
        unaryOps.push(this);

        for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
            en.nextElement().applyUnaryOperations(unaryOps);
        }

        unaryOps.pop();
    }

    public void rotate(double angleDiff)
    {
        angle += angleDiff;
        update();
    }

    public void setFlag(TransFlag f)
    {
        flag = f;
    }

    public void setRotateAnchor(double ax, double ay)
    {
        this.anchorX = ax;
        this.anchorY = ay;
    }

    public void resetAnchor()
    {
        SceneGraph child = (SceneGraph) this.getChildAt(0);
        this.anchorX = child.getBarycenterX();
        this.anchorY = child.getBarycenterY();
    }
    
    public void setTranslate(double dx, double dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    public void scaleTo(Point to)
    {
        this.factorX = (to.getX() - centerX)/(this.initWidth / 2);
        this.factorY = (to.getY() - centerY)/(this.initHeight / 2);
        System.out.println(factorX);
        System.out.println(factorY);
    }

    public void shearTo(Point to)
    {
        scaleTo(to);
    }
}
