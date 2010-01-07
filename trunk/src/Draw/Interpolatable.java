/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Draw;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public interface Interpolatable {
    
    /**
     * @return le nombre de sommets de la figure
     */
    public int getPointsNb();

    /**
     * @return la coordonée horizontale du <tt>i</tt>-ième sommet
     */
    public int getX(int i);

    /**
     * @return la coordonée verticale du <tt>i</tt>-ième sommet
     */
    public int getY(int i);

}
