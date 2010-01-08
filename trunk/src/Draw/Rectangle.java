package Draw;

import java.awt.geom.Rectangle2D;

/**
 * Rectangle (feuille du graphe de scène).
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Rectangle extends SceneShape implements Interpolatable
{

    /** Rectangle à afficher */
    private Rectangle2D.Double rect;

    /** Coordonnées horizontales des sommets du rectangle */
    private int[] xs = new int[4];
    /** Coordonnées verticales des sommets du rectangle */
    private int[] ys = new int[4];

    /**
     * Constructeur
     * @param v      vue
     * @param x      coordonnée horizontale du sommet haut-gauche
     * @param y      coordonnée verticale du sommet haut-gauche
     * @param width  largeur
     * @param height hauteur
     */
    public Rectangle(View v, double x, double y, double width, double height) {
        super(v, "Rectangle");
        rect = new Rectangle2D.Double(x ,y, width, height);
        baseShape = rect;
        shape = rect;

	//Calcul des coordonées des sommets du rectangle
	//Nécessaire à l'interpolation

        double minx = x;
        double maxx = rect.getMaxX();
        double miny = y;
        double maxy = rect.getMaxY();

        xs[0] = (int) minx;
        ys[0] = (int) miny;
        xs[1] = (int) maxx;
        ys[1] = (int) miny;
        xs[2] = (int) maxx;
        ys[2] = (int) maxy;
        xs[3] = (int) minx;
        ys[3] = (int) maxy;
    }

    /** Méthodes de l'interface Interpolatable */

    @Override
    public int getPointsNb() {
        return 4;
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
