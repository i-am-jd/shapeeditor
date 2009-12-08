package Draw;


public class Star extends Polygon {
	/* Coordonnees x des sommets du triangle */
	private int[] x = new int[10];
	/* Coordonnees y des sommets du triangle */
	private int[] y = new int[10];
	
	public Star(int[] x, int[] y)
	{
		for(int i=0; i<10; i++) {
			this.x[i] = x[i];
			this.y[i] = y[i];
		}
	}

	public void setX(int[] x) {
		this.x = x;
	}
	public int[] getX() {
		return x;
	}

	public void setY(int[] y) {
		this.y = y;
	}
	public int[] getY() {
		return y;
	}
}
