package Draw;

import java.util.Enumeration;
import java.awt.geom.AffineTransform;

public class Translation extends UnaryOperation {

    double dx = 0;
    double dy = 0;
    
    public Translation(SceneGraph child, double angle) {
        super("Translation");

        //Ajouter l'enfant
        child.removeFromParent();
        this.add(child);
    }

    public void set(double dx, double dy)
    {
        this.dx = dx;
        this.dy = dy;

        //Appliquer récursivement la rotation
        ((SceneGraph) this.getRoot()).applyTransform(new AffineTransform());
    }

    @Override
    public void applyTransform(AffineTransform trans)
    {
        AffineTransform trans2 = (AffineTransform) trans.clone();
        trans2.translate(dx, dy);
        for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
            en.nextElement().applyTransform(trans2);
        }
    }
    
}