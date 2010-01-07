package Draw;

import java.awt.geom.Area;


/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Exclusion extends BinaryOperation
{

    /**
     *
     * @param sg1
     * @param sg2
     */
    public Exclusion(SceneGraph sg1, SceneGraph sg2)
    {
        super("Exclusion", sg1, sg2);
        Area area = new Area(sg1.getShape());
        area.exclusiveOr(new Area(sg2.getShape()));
        shape = area;
        baseShape = area;
    }
    
}
