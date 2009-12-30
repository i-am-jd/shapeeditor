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
        s.add(((SceneGraph)s.getChildAt(0)).clone());
        return s;
    }

    @Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {
        ((SceneGraph) this.getChildAt(0)).draw(g2d, rotate, scaleX, scaleY, factorX + shearX, factorY + shearY);
    }

}
