package Draw;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.Polygon;

/**
 * Triangle isocèle (feuille du graphe de scène).
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class IsoscelesTriangle extends SceneShape implements PolygonLike {

    /** Polygône à afficher */
    final Polygon polygon;

    /** Coordonées horizontales des sommets */
    int[] xs;
    /** Coordonées verticales des sommets */
    int[] ys;

    /**
     * Constructeur
     * @param view    paramètres de dessin
     * @param xpoints coordonées horizontales des sommets
     * @param ypoints coordonées verticales des sommets
     */
    public IsoscelesTriangle(View view, int[] xpoints, int[] ypoints)
    {
        super(view, "Isosceles Triangle");
        this.xs = xpoints;
        this.ys = ypoints;
        polygon = new Polygon(xpoints, ypoints, 3);
        shape = polygon;
        baseShape = polygon;
    }
    
    /**
     * Constructeur
     * @param view   paramètres de dessin
     * @param x 
     * @param y
     * @param width  largeur
     * @param height hauteur
     */
    public IsoscelesTriangle(View view, double x, double y, double width, double height) {
        super(view, "Isosceles Triangle");

        xs = new int[3];
        ys = new int[3];

        xs[0] = (int) (x + width/2);
        ys[0] = (int) y;
        xs[1] = (int) x;
        ys[1] = (int) (y+height);
        xs[2] = (int) (x + width);
        ys[2] = (int) (y+height);

        polygon = new Polygon(xs, ys, 3);
        shape = polygon;
        baseShape = polygon;

        //setUserObject("IsoscelesTriangle");
    }

    /**
     * Constructeur de copie
     * L'objet est décalé de (5,5) pixels par rapport au triangle isocèle copié
     * @param t triangle à copier
     */
    public IsoscelesTriangle(IsoscelesTriangle t) {
        super(t.getView(), "Isosceles Triangle");
        polygon = new Polygon(t.polygon.xpoints, t.polygon.ypoints, t.polygon.npoints);
        polygon.translate(5, 5);
        shape = polygon;
        baseShape = polygon;
    }

    /**
     * Copie le triangle isocèle courant
     * @return une copie décalée de (5,5) pixels
     */
     @Override
    public IsoscelesTriangle clone()
    {
        return new IsoscelesTriangle(this);
    }

    //Méthodes de l'interface PolygonLike

    @Override
    public int getPointsNb() {
        return 3;
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
