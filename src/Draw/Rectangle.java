package Draw;

import java.awt.Graphics2D;


public class Rectangle extends Polygon
{
	/* Demi-hauteur du rectangle */
	protected int height;
	/* Demi-longueur du rectangle */
	protected int width;
	
	public Rectangle(int width, int height)
	{
		this.setHeight(height);
		this.setWidth(width);
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
	
	public void fillGeometry(Graphics2D g2d)
     {
         g2d.fillRect(894/2-getWidth(), 613/2-getHeight(), getWidth()*2, getHeight()*2);
     }
    public void drawGeometry(Graphics2D g2d)
    {
        g2d.drawRect(894/2-getWidth(), 613/2-getHeight(), getWidth()*2, getHeight()*2);
    }
}
