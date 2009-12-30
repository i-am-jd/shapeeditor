package Draw;

import java.awt.Graphics2D;


public class Inclusion extends BinaryOperation {
    public Inclusion()
    {
        super("Inclusion");
    }

    @Override
    public Inclusion clone()
    {
        Inclusion i = new Inclusion();
        i.add(((SceneGraph)i.getChildAt(0)).clone());
        i.add(((SceneGraph)i.getChildAt(1)).clone());
        return i;
    }

    @Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {

    }
}
