package Draw;

import java.awt.geom.Area;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Union extends BinaryOperation {

    /**
     *
     * @param sg1
     * @param sg2
     */
    public Union(SceneGraph sg1, SceneGraph sg2) {
        super("Union", sg1, sg2);
        Area area = new Area(sg1.getShape());
        area.add(new Area(sg2.getShape()));
        shape = area;
        baseShape = area;
    }
}
