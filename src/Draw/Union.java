package Draw;

import java.awt.Graphics2D;



public class Union extends BinaryOperation {

    public Union()
    {
        super("Union");
    }
    
    public Union clone()
    {
        Union u = new Union();
        u.add(((SceneGraph)u.getChildAt(0)).clone());
        u.add(((SceneGraph)u.getChildAt(1)).clone());
        return u;
    }

    @Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {
    }

}
