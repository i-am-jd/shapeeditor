package Draw;

import java.awt.Polygon;

/**
 * Feuille du graphe de scène représentant un polygône régulier.
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class RegularPolygon extends SceneShape implements Interpolatable
{
    private Polygon polygon;

    int nbSides;
    int[][] tab;
    
    private double originY;
    private double originX;

    /**
     * Constructeur du polygône régulier
     * @param v   vue
     * @param x   coordonnée horizontale du centre
     * @param y   coordonnée verticale du centre
     * @param rad rayon
     * @param n   nombre de côtés
     */
    public RegularPolygon(View v, double x, double y, double rad, int n)
    {
        super(v, "Regular Polygon");

        originX = x;
        originY = y;

        nbSides = n;

        tab = new int[2][nbSides];
        int[] origin = {(int) x, (int) y};
        int r = (int) rad;
        double angle = Math.PI/2;
        for (int i=0; i<this.nbSides; i++) {
            /* Calcul des coordonnees du point : utilisation des coordonnees polaires */
            tab[0][i] = origin[0] - (int)((double)r*Math.cos(angle));
            tab[1][i] = origin[1] - (int)((double)r*Math.sin(angle));
            angle += 2*Math.PI/this.nbSides;
        }

        polygon = new Polygon(tab[0], tab[1], nbSides);
        shape = polygon;
        baseShape = polygon;

        setUserObject("RegularPolygon");
    }

    /**
     * Constructeur de copie
     * @param p
     * @return une copie du polygône décalée de 5 pixels vers le bas et vers la droite
     */
    public RegularPolygon(RegularPolygon p) {
        super(p.view, "Regular Polygon");
        originX = p.originX + 5;
        originY = p.originY + 5;
        polygon = new Polygon(p.polygon.xpoints, p.polygon.ypoints, p.polygon.npoints);
        polygon.translate(5, 5);
        shape = polygon;
        baseShape = polygon;
    }

    /**
     * Copie un polygône
     * @param p
     * @return une copie du polygône décalée de 5 pixels vers le bas et vers la droite
     */
     @Override
    public RegularPolygon clone()
    {
        return new RegularPolygon(this);
    }

    // Méthodes de l'interface Interpolatable

     @Override
    public int getPointsNb() {
        return nbSides;
    }

     @Override
    public int getX(int i) {
        return tab[0][i];
    }

    @Override
    public int getY(int i) {
        return tab[1][i];
    }
}
