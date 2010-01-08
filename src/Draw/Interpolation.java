package Draw;

import java.awt.Polygon;


/**
 * Interpolation de deux sous-graphes de scène.
 * Dans cette implémentation l'interpolation n'est possible
 * qu'entre formes assimilables à des polygônes.
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Interpolation extends BinaryOperation implements PolygonLike
{
    /** Coordonnées horizontales des sommets */
    private int[] xs;
    /** Coordonnées verticales des sommets */
    private int[] ys;
    /** Nombre de sommets */
    private int n;

    /**
     * Construit une interpolation à partir de deux formes assimilables à des polygônes
     * @param pl1 premier sous-graphe de scène
     * @param pl2 deuxième sous-graphe de scène
     */
    public Interpolation(PolygonLike pl1, PolygonLike pl2)
    {
        super("Interpolation", (SceneGraph) pl1, (SceneGraph) pl2);

        assert(pl1.getPointsNb() == pl2.getPointsNb());
        n = pl1.getPointsNb();
        xs = new int[n];
        ys = new int[n];
        for(int i = 0; i < n; i++) {
            xs[i] = (pl1.getX(i) + pl2.getX(i))/2;
            ys[i] = (pl1.getY(i) + pl2.getY(i))/2;
        }
        shape = new Polygon(xs, ys, pl1.getPointsNb());
        baseShape = new Polygon(xs, ys, pl1.getPointsNb());
    }

    @Override
    public int getPointsNb() {
        return n;
    }

    @Override
    public int getX(int i) {
        return xs[i];
    }

    @Override
    public int getY(int i) {
        return ys[i];
    }
            
}

