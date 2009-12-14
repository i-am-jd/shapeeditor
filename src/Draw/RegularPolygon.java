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
public class RegularPolygon extends Circle
{
    int nbSides;

    public RegularPolygon (int rad, int nbSides){
        super(rad);
        this.nbSides = nbSides;
    }

    public int getNbSides ()
    {
        return nbSides;
    }
    public void setNbSides (int n)
    {
        this.nbSides = n;
    }

    public int[][] calculateCoordinates ()
    {
        int[][] tab = new int[2][nbSides];
        int[] origin = {894/2, 613/2};
        int r = this.getRadius();
        double angle = Math.PI/2;
        for (int i=0; i<this.nbSides; i++) {
            /* Calcul des coordonnees du point : utilisation des coordonnees polaires */
            tab[0][i] = origin[0] - (int)((double)r*Math.cos(angle));
            tab[1][i] = origin[1] - (int)((double)r*Math.sin(angle));
            angle += 2*Math.PI/this.nbSides;
        }
        return tab;
    }

     public void fillGeometry(Graphics2D g2d)
     {
         int[][] tab = this.calculateCoordinates();
        g2d.fillPolygon(tab[0], tab[1], nbSides);
     }
    public void drawGeometry(Graphics2D g2d)
    {
        int[][] tab = this.calculateCoordinates();
        g2d.drawPolygon(tab[0], tab[1], nbSides);
    }
}
