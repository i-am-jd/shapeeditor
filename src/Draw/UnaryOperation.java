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
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public abstract class UnaryOperation extends Transformation {

    public UnaryOperation(String name, SceneGraph child)
    {
        super(child.getView(), name);
        this.add(child);
        this.beginEdit();
    }
    
    /**
     *
     * @param s
     * @return
     */
    abstract protected ArrayList<AffineTransform> toAffineTransforms(Shape s);

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

    /**
     *
     * @param unaryOps
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

    abstract public void beginEditNode(SceneGraph child);

    public void beginEdit()
    {
        SceneGraph child = (SceneGraph) this.getChildAt(0);
        this.beginEditNode(child);
    }
}
