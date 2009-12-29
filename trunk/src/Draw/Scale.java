package Draw;

import java.awt.Graphics2D;
import java.util.Enumeration;


public class Scale extends UnaryOperation {

    private double factorX;
    private double factorY;

    public Scale (double scaleX, double scaleY)
    {
        super("Scale");
        this.factorX = scaleX;
        this.factorY = scaleY;
    }

    @Override
    public Scale clone()
    {
        Scale s = new Scale(factorX, factorY);
        for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                s.add(en.nextElement().clone());
        }
        return s;
    }

    @Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {
        for (Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
            en.nextElement().draw(g2d, rotate, factorX*scaleX, factorY*scaleY, shearX, shearY);
        }
    }
}
