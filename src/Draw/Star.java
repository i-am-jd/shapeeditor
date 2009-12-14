package Draw;

import java.awt.Graphics2D;


public class Star extends RegularPolygon {

    public Star (int rad, int nbSides)
    {
        super(rad, 2*nbSides);
    }

    public int[][] calculateCoordinates ()
    {
        int[][] tab = new int[2][nbSides];
        int[] origin = {894/2, 613/2};
        int r = this.getRadius();
        double angle = Math.PI/2;
        for (int i=0; i<this.nbSides; i++) {
            if (i%2==0) {
                tab[0][i] = origin[0] - (int)((double)r*Math.cos(angle));
                tab[1][i] = origin[1] - (int)((double)r*Math.sin(angle));
            } else {
                tab[0][i] = origin[0] - (int)((double)2*r/6*Math.cos(angle));
                tab[1][i] = origin[1] - (int)((double)2*r/6*Math.sin(angle));
            }
            angle += 2*Math.PI/(this.nbSides);
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
