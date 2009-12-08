package Draw;


public class Ellipse extends Geometry {
	
	/** Coordonnee X du centre */
	protected int x;
	/** Coordonnee Y du centre */
	protected int y;
	/** Largeur de l'ellipse (demi grand axe) */
	protected int a;
	/** Hauteur de l'ellipse (demi petit axe) */
	protected int b;
	
	public Ellipse (int xC, int yC, int w, int h)
	{
		setX(xC);
		setY(yC);
		setSemiMajorAxis(w);
		setSemiMinorAxis(h);
	}

	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
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
	
}
