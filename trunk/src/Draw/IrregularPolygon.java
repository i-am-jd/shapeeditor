package Draw;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.Polygon;

/**
 * Feuille du scène de graphe représentant un polygône irrégulier
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class IrregularPolygon extends SceneShape implements Interpolatable {
    /** Polygône à afficher */
    private Polygon polygon;

    /** Coordonées du polygône */
    int[][] vertices;

    /**
     * Constructeur par défaut
     * @param v        vue (paramètres d'affichage)
     * @param vertices sommets du polygône
     */
    public IrregularPolygon(View v, int[][] vertices)
    {
        super(v, "Irregular Polygon");

        this.vertices = vertices;

        polygon = new Polygon(vertices[0], vertices[1], vertices[0].length);
        shape = polygon;
        baseShape = polygon;
    }

    //Méthodes de l'interface Interpolatable

    @Override
    public int getPointsNb() {
        return vertices[0].length;
    }

    @Override
    public int getX(int i) {
        return vertices[0][i];
    }

    @Override
    public int getY(int i) {
        return vertices[1][i];
    }
}
