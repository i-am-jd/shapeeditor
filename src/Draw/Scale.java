package Draw;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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

    public void setFactors (double[] t)
    {
        factorX = t[0];
        factorY = t[1];

        //Appliquer récursivement la rotation
        ((SceneGraph) this.getRoot()).applyTransform(new AffineTransform());
    }

    /*@Override
    public Scale clone()
    {
        Scale s = new Scale(factorX, factorY);
        s.add(((SceneGraph)s.getChildAt(0)).clone());
        return s;
    }*/

    @Override
    public void applyTransform(AffineTransform trans)
    {
        AffineTransform trans2 = (AffineTransform) trans.clone();
        trans2.scale(factorX, factorY);
        
        for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
            en.nextElement().applyTransform(trans2);
        }
    }

    /*@Override
    public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {
        ((SceneGraph)this.getChildAt(0)).draw(g2d, rotate, factorX*scaleX, factorY*scaleY, shearX, shearY);
    }*/
}
