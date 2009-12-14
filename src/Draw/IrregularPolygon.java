/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Draw;

import java.awt.Graphics2D;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class IrregularPolygon extends Polygon {

    private int[][] verticesCoordinates = null;

    public IrregularPolygon (int[][] vertices)
    {
        this.verticesCoordinates = vertices;
    }

    public int[][] getVerticesCoordinates()
    {
        return this.verticesCoordinates;
    }
    public int getNumberOfSides()
    {
        return verticesCoordinates[0].length;
    }

     public void fillGeometry(Graphics2D g2d)
     {
        g2d.fillPolygon(verticesCoordinates[0], verticesCoordinates[1], verticesCoordinates[0].length);
     }
    public void drawGeometry(Graphics2D g2d)
    {
        g2d.drawPolygon(verticesCoordinates[0], verticesCoordinates[1], verticesCoordinates[0].length);
    }

}
