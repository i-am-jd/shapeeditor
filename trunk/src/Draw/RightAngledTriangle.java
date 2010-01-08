package Draw;

import java.awt.Polygon;

/**
 * Triangle rectangle (feuille du graphe de scène)..
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class RightAngledTriangle extends SceneShape {

    /** Polygône à afficher */
    private final Polygon polygon;

    /**
     * Constructeur
     * @param v      paramètres de dessin
     * @param x      coordonnée horizontale de l'angle droit
     * @param y      coordonnée verticale de l'angle droit
     * @param width  largeur
     * @param height hauteur
     */
    public RightAngledTriangle(View v, double x, double y, double width, double height) {
        super(v, "Right Angled Triangle");

        int[] xpoints = new int[3];
        int[] ypoints = new int[3];

        xpoints[0] = (int) x;
        ypoints[0] = (int) y;
        xpoints[1] = (int) x;
        ypoints[1] = (int) (y + height);
        xpoints[2] = (int) (x + width);
        ypoints[2] = (int) (y + height);

        polygon = new Polygon(xpoints, ypoints, 3);
        shape = polygon;
        baseShape = polygon;
    }

    /**
     * Constructeur de copie
     * @param t triangle rectangle à copier
     */
    public RightAngledTriangle(RightAngledTriangle t) {
        super(t.view, "Right Angled Triangle");
        polygon = new Polygon(t.polygon.xpoints, t.polygon.ypoints, t.polygon.npoints);
        polygon.translate(5, 5);
        shape = polygon;
        baseShape = polygon;
    }

    /**
     * Copie le triangle rectangle courant.
     */
    @Override
    public RightAngledTriangle clone() {
        return new RightAngledTriangle(this);
    }
}
