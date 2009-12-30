package Draw;

import java.awt.Graphics2D;


public class Interpolation extends BinaryOperation {
    public Interpolation()
    {
        super("Interpolation");
    }

    @Override
    public Interpolation clone()
    {
        Interpolation i = new Interpolation();
        i.add(((SceneGraph)i.getChildAt(0)).clone());
        i.add(((SceneGraph)i.getChildAt(1)).clone());
        return i;
    }

    @Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {

    }
}
