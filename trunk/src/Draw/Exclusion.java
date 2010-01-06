package Draw;

import java.awt.geom.Area;


public class Exclusion extends BinaryOperation
{

    public Exclusion(SceneGraph sg1, SceneGraph sg2)
    {
        super("Exclusion", sg1, sg2);
        this.area = new Area(sg1.getShape());
        area.exclusiveOr(new Area(sg2.getShape()));
        shape = area;
        baseShape = area;
    }
    
}
