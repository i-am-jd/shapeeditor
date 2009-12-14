package Draw;

import java.awt.Graphics2D;


public class Ellipse extends Geometry
{
	/** Largeur de l'ellipse (demi grand axe) */
	protected int a;
	/** Hauteur de l'ellipse (demi petit axe) */
	protected int b;
	
	public Ellipse (int w, int h)
	{
		setSemiMajorAxis(w);
		setSemiMinorAxis(h);
	}

	public void setSemiMajorAxis(int a) {
		this.a = a;
	}

	public int getSemiMajorAxis() {
		return a;
	}

	public void setSemiMinorAxis(int b) {
		this.b = b;
	}

	public int getSemiMinorAxis() {
		return b;
	}

         public void fillGeometry(Graphics2D g2d)
     {
         g2d.fillOval(894/2/*origine*/-getSemiMajorAxis(), 613/2-getSemiMinorAxis(), 2*getSemiMajorAxis(), 2*getSemiMinorAxis());
     }
    public void drawGeometry(Graphics2D g2d)
    {
        g2d.drawOval(894/2/*origine*/-getSemiMajorAxis(), 613/2-getSemiMinorAxis(), 2*getSemiMajorAxis(), 2*getSemiMinorAxis());
    }
	
}
