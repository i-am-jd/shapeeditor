package Draw;

import java.awt.geom.Area;

/**
 * Intersection géométrique de deux sous-graphes de scène.
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Intersection extends BinaryOperation {

    /**
     * Constructeur
     * @param sg1 premier sous-graphe de scène
     * @param sg2 deuxième sous-graphe de scène
     */
    public Intersection(SceneGraph sg1, SceneGraph sg2) {
        super("Intersection", sg1, sg2);
        Area area = new Area(sg1.getShape());
        area.intersect(new Area(sg2.getShape()));
        shape = area;
        baseShape = area;
    }
}
