package Draw;


public class Rectangle extends Polygon {
	/* Coordonnee x du rectangle */
	protected int x;
	/* Coordonnee y du rectangle */
	protected int y;
	/* Hauteur du rectangle */
	protected int height;
	/* Longueur du rectangle */
	protected int width;
	
	public Rectangle(int width, int height)
	{
		this.setX(0);
		this.setY(0);
		this.setHeight(height);
		this.setWidth(width);
	}
	public Rectangle(int x, int y, int width, int height)
	{
		this.setX(x);
		this.setY(y);
		this.setHeight(height);
		this.setWidth(width);
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
	
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	public int getWidth() {
		return width;
	}
	
	
}
