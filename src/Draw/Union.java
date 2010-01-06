package Draw;

import java.awt.geom.Area;



public class Union extends BinaryOperation {

    public Union(SceneGraph sg1, SceneGraph sg2)
    {
        super("Union", sg1, sg2);
        Area area = new Area(sg1.getShape());
        area.add(new Area(sg2.getShape()));
        shape = area;
        baseShape = area;
    }

}
