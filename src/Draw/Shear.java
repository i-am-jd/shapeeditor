package Draw;

import java.awt.Graphics2D;
import java.util.Enumeration;

public class Shear extends UnaryOperation {

    private double factorX;
    private double factorY;

    public Shear (double shearX, double shearY)
    {
        super("Shear");
        this.factorX = shearX;
        this.factorY = shearY;
    }

    @Override
    public Shear clone()
    {
        Shear s = new Shear(factorX, factorY);
        for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                s.add(en.nextElement().clone());
        }
        return s;
    }

    @Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {
        for (Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
            en.nextElement().draw(g2d, rotate, scaleX, scaleY, factorX+shearX, factorY+shearY);
        }
    }

}
