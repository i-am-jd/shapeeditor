package Draw;

/**
 * Cercle (feuille du graphe de scène).
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Circle extends Ellipse {

    /**
     * Constructeur
     * @param view   paramètres de dessin
     * @param x      coordonnée horizontale du centre
     * @param y      coordonnée verticale du centre
     * @param radius rayon
     */
    public Circle(View view, double x, double y, double radius) {
        super(view, x, y, radius, radius);
    }
}
