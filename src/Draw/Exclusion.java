package Draw;

import java.awt.geom.Area;


/**
 * Exclusion géométrique de deux sous-graphes de scène.
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Exclusion extends BinaryOperation
{

    /**
     * Constructeur
     * @param sg1 premier sous-graphe de scène
     * @param sg2 deuxième sous-graphe de scène
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
