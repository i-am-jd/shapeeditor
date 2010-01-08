package Draw;

import java.awt.Polygon;


/**
 * Étoile (feuille du graphe de scène).
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Star extends SceneShape implements PolygonLike {

    /** Polygône à afficher */
    final Polygon polygon;

    /** Centre de l'étoile */
    double originX;
    double originY;

    /** Nombre de points et coordonées du polygône */
    int nbSides;
    int[] xpoints;
    int[] ypoints;

    /**
     *
     * @param v        vue (paramètres de dessin)
     * @param x        coordonnée horizontale du centre de l'étoile
     * @param y        coordonnée verticale du centre de l'étoile
     * @param r        rayon de l'étoile
     * @param nbBranch nombre de branches
     */
    public Star(View v, double x, double y, double r, int nbBranch)
    {
        super(v, "Star");

        originX = x;
        originY = y;

        nbSides = 2*nbBranch;
        xpoints = new int[nbSides];
        ypoints = new int[nbSides];
        double angle = Math.PI/2;

        for (int i=0; i<nbSides; i++) {
            if (i%2==0) {
                xpoints[i] = (int) x - (int)((double)r*Math.cos(angle));
                ypoints[i] = (int) y - (int)((double)r*Math.sin(angle));
            } else {
                xpoints[i] = (int) x - (int)((double)2*r/6*Math.cos(angle));
                ypoints[i] = (int) y - (int)((double)2*r/6*Math.sin(angle));
            }
            angle += Math.PI/nbBranch;
        }

        polygon = new Polygon(xpoints, ypoints, nbSides);
        shape = polygon;
        baseShape = polygon;
    }

    /**
     * Constructeur de copie
     * L'étoile résultante est décalée de (5,5) pixels par rapport à l'étoile copiée
     * @param star étoile à copier
     */
    public Star(Star star) {
        super(new View(star.view), "Star");
        originX = star.originX + 5;
        originY = star.originY + 5;
        polygon = new Polygon(star.polygon.xpoints, star.polygon.ypoints, star.polygon.npoints);
        polygon.translate(5, 5);
        shape = polygon;
    }

    /**
     * Copie de l'étoile courante
     * @return copie de l'étoile décalée de 5 pixels vers la droite et le bas
     */
    @Override
    public Star clone()
    {
        return new Star(this);
    }

    // Méthodes de l'interface Interpolatable

     @Override
    public int getPointsNb() {
        return nbSides;
    }

     @Override
    public int getX(int i) {
        return xpoints[i];
    }

    @Override
    public int getY(int i) {
        return ypoints[i];
    }
}
