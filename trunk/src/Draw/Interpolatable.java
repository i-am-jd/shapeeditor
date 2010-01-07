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
     * 
     * @return
     */
    public int getPointsNb();

    /**
     *
     * @param index
     * @return
     */
    public int getX(int index);

    /**
     *
     * @param index
     * @return
     */
    public int getY(int index);

}
