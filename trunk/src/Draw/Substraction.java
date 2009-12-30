package Draw;

import java.awt.Graphics2D;



public class Substraction extends BinaryOperation {

    public Substraction()
    {
        super("Substraction");
    }

    public Substraction clone()
    {
        Substraction s = new Substraction();
        s.add(((SceneGraph)s.getChildAt(0)).clone());
        s.add(((SceneGraph)s.getChildAt(1)).clone());
        return s;
    }

    @Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {

    }

}
