package Draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


/**
 * Soustraction géométrique de deux sous-graphes de scène.
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Substraction extends BinaryOperation
{

    /**
     * Constructeur
     * @param sg1 premier sous-graphe
     * @param sg2 premier sous-graphe
     */
    public Substraction(SceneGraph sg1, SceneGraph sg2)
    {
        super("Substraction", sg1, sg2);
        Area area = new Area(sg1.getShape());
        area.subtract(new Area(sg2.getShape()));
        shape = area;
        baseShape = area;
    }

}
