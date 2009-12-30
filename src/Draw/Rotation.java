package Draw;

import java.awt.Graphics2D;
import java.util.Enumeration;


public class Rotation extends UnaryOperation {

    public double angle;

    public Rotation(double angle) {
        super("Rotation");
        this.angle = angle;
    }

    public double getAngle()
    {
        return angle;
    }
    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    @Override
    public Rotation clone()
    {
        Rotation r = new Rotation(angle);
        r.add(((SceneGraph)r.getChildAt(0)).clone());
        return r;
    }

    @Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {
        ((SceneGraph) this.getChildAt(0)).draw(g2d, rotate + angle, scaleX, scaleY, shearX, shearY); //, scale, shear);
    }

}
