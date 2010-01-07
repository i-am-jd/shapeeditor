package Draw;



/**
 * Feuille du graphe de scène représentant un carré.
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Square extends Rectangle {

    /**
     * Constructeur du carré
     * @param v vue
     * @param x coordonnée horizontale du point haut-gauche
     * @param y coordonnée verticale du point haut-gauche
     * @param w longeur du côté
     */
    public Square(View v, double x, double y, double w) {
        super(v, x, y, w, w);
        setUserObject("Square");
    }
    
}
