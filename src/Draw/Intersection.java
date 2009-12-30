package Draw;

import java.awt.Graphics2D;


public class Intersection extends BinaryOperation {
    public Intersection()
    {
        super("Intersection");
    }

    @Override
    public Intersection clone()
    {
        Intersection i = new Intersection();
        i.add(((SceneGraph)i.getChildAt(0)).clone());
        i.add(((SceneGraph)i.getChildAt(1)).clone());
        return i;
    }

    @Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {

    }
}
