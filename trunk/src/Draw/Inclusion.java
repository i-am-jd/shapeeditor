package Draw;

import java.awt.geom.Area;


public class Inclusion extends BinaryOperation
{

    public Inclusion(SceneGraph sg1, SceneGraph sg2)
    {
        super("Inclusion", sg1, sg2);
        this.area = new Area(sg1.getShape());
        Area area2 = new Area(sg2.getShape());
        area2.intersect(area);
        area.add(area2);
        shape = area;
        baseShape = area;
    }

}
