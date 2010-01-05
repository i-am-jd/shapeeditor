package Draw;

import java.awt.Shape;
import java.awt.geom.Area;


public class Intersection extends SceneShape
{
    Area area;
    
    public Intersection(View v, SceneGraph sg1, SceneGraph sg2)
    {
        super(v);
        this.area = new Area(sg1.getShape());
        area.intersect(new Area(sg2.getShape()));

        shape = area;
        baseShape = area;

        this.add(sg1);
        this.add(sg2);
    }
}
