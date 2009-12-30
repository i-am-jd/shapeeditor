package Draw;

import java.awt.Graphics2D;


public class Exclusion extends BinaryOperation {

    public Exclusion()
    {
        super("Exclusion");
    }

    @Override
    public Exclusion clone()
    {
        Exclusion e = new Exclusion();
        e.add(((SceneGraph)e.getChildAt(0)).clone());
        e.add(((SceneGraph)e.getChildAt(1)).clone());
        return e;
    }

    @Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {

    }

}
