package Draw;

/**
 * Interface regroupant les méthodes d'une forme assimilable à
 * un polygône.
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public interface PolygonLike {
    
    /**
     * @return le nombre de sommets de la figure
     */
    public int getPointsNb();

    /**
     * @return la coordonnée horizontale du <tt>i</tt>-ième sommet
     */
    public int getX(int i);

    /**
     * @return la coordonnée verticale du <tt>i</tt>-ième sommet
     */
    public int getY(int i);

}
