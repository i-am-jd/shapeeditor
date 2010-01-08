package Draw;

import java.awt.geom.Area;

/**
 * Union géométrique de deux sous-graphe de scène.
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Union extends BinaryOperation {

    /**
     * Constructeur
     * @param sg1 premier sous-graphe de scène
     * @param sg2 deuxième sous-graphe de scène
     */
    public Union(SceneGraph sg1, SceneGraph sg2) {
        super("Union", sg1, sg2);
        Area area = new Area(sg1.getShape());
        area.add(new Area(sg2.getShape()));
        shape = area;
        baseShape = area;
    }
}
