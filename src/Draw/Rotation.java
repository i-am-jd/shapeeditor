package Draw;

import java.awt.Graphics2D;
import java.util.Enumeration;


public class Rotation extends UnaryOperation {

    public double angle;

    public Rotation(double angle) {
        super("Rotation");
        this.angle = angle;
    }

    @Override
    public Rotation clone()
    {
        Rotation r = new Rotation(angle);
        for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                r.add(en.nextElement().clone());
        }
        return r;
    }

    @Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {
        for (Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
            en.nextElement().draw(g2d, rotate + angle, scaleX, scaleY, shearX, shearY); //, scale, shear);
        }
    }

}
