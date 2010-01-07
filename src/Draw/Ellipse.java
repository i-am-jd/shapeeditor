package Draw;

import java.awt.geom.Ellipse2D;


/**
 * Feuille du graphe de scène représentant une ellipse.
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Ellipse extends SceneShape {

    private Ellipse2D.Double ellipse;

    /**
     * Construit une ellipse à partir du rectangle qui la contient
     * @param v      vue
     * @param x      coordonée horizontale du point haut-gauche du rectangle
     * @param y      coordonée verticale du point haut-gauche du rectangle
     * @param width  largeur
     * @param height hauteur
     */
    public Ellipse(View v, double x, double y, double width, double height) {
        super(v, "Ellipse");
        ellipse = new Ellipse2D.Double(x, y, width, height);
        shape = ellipse;
        baseShape = ellipse;
    }

    /**
     * Copie de l'ellipse courante
     * @param e ellipse à copier
     * @returns copie de l'ellipse décalée de 5 pixels vers la droite et le bas
     */
    @Override
    public Ellipse clone() {
        return new Ellipse(new View(view), ellipse.x+5, ellipse.y+5, ellipse.width, ellipse.height);
    }
}
