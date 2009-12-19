package Draw;

import java.awt.Graphics2D;


public abstract class Triangle extends Polygon {
	
	/*  */
	private int width;
	/*  */
	private int height;
	
	public Triangle (int width, int height)
	{
		this.width = width;
                this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	public int getWidth() {
		return width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}

    public abstract int[][] calculateCoordinates();

    public void fillGeometry(Graphics2D g2d) {
        int[][] tab = calculateCoordinates();
        g2d.fillPolygon(tab[0], tab[1], 3);
    }

    @Override
    public void drawGeometry(Graphics2D g2d) {
        int[][] tab = calculateCoordinates();
        g2d.drawPolygon(tab[0], tab[1], 3);
    }
}
