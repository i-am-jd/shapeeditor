package Draw;

import java.util.Enumeration;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Rotation extends UnaryOperation {

    public double angle;
    //protected AffineTransform rotation;
    protected Shape shape;

    //Coordonnées du point par rapport auquel on effectue la rotation
    double anchorX;
    double anchorY;

    public Rotation(SceneGraph child, double angle) {
        super("Rotation");

        //Ajouter l'enfant
        child.removeFromParent();
        this.add(child);

        this.anchorX = child.getBarycenterX();
        this.anchorY = child.getBarycenterY();
        this.angle = angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle; 

        //Appliquer récursivement la rotation
        ((SceneGraph) this.getRoot()).applyTransform(new AffineTransform());
    }

    public void rotate(double angleDiff)
    {
        this.angle += angleDiff;
    }

    @Override
    public void applyTransform(AffineTransform trans)
    {
        SceneGraph child = (SceneGraph) this.getChildAt(0);
        AffineTransform trans2 = (AffineTransform) trans.clone();
        trans2.rotate(angle, anchorX, anchorY);
        for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
            en.nextElement().applyTransform(trans2);
        }
    }

    public void resetAnchor()
    {
        SceneGraph child = (SceneGraph) this.getChildAt(0);
        this.anchorX = child.getBarycenterX();
        this.anchorY = child.getBarycenterY();
    }

    /*
    @Override
    public Rotation clone()
    {
        Rotation r = new Rotation(angle);
        r.add(((SceneGraph)r.getChildAt(0)).clone());
        return r;
    }*/

    /*@Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {
        ((SceneGraph) this.getChildAt(0)).draw(g2d, rotate + angle, scaleX, scaleY, shearX, shearY); //, scale, shear);
    }*/

}
