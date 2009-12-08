package Draw;

import java.awt.Color;
import java.awt.Image;


public class View {

	/** Couleur du trait */
	private Color lineColor;
	/** Epaisseur du trait */
	private float lineWidth;
	/** Couleur de remplissage */
	private Color fillColor;
	/** Motif de remplissage */
	private Image fillPattern;
	
	public View (Color c, float w, Color fillC, Image fillP)
	{
		setLineColor(c);
		setLineWidth(w);
		fillColor = fillC;
		fillPattern = fillP;
	}
	
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public Color getLineColor() {
		return lineColor;
	}

	public void setLineWidth(float lineWidth) {
		this.lineWidth = lineWidth;
	}
	public float getLineWidth() {
		return lineWidth;
	}

	public void setFillColor(Color fillColor) 
	{
		this.fillColor = fillColor;
	}
	public Color getFillColor() {
		return fillColor;
	}
	
	public void setFillPattern(Image fillPattern) 
	{
		this.fillPattern = fillPattern;
	}
	public Image getFillPattern() 
	{
		return fillPattern;
	}

	
}
