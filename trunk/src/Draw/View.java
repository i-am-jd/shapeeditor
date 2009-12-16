package Draw;

import java.awt.Color;
import java.awt.TexturePaint;


public class View {

	/** Couleur du trait */
	private Color lineColor;
	/** Epaisseur du trait */
	private float lineWidth;
	/** Couleur de remplissage */
	private Color fillColor;
	/** Motif de remplissage */
	private TexturePaint fillPattern;
	
	public View (Color c, float w, Color fillC, TexturePaint fillP)
	{
		setLineColor(c);
		setLineWidth(w);
		fillColor = fillC;
		fillPattern = fillP;
	}
        /* Constructeur de copie (copie profonde) */
        public View (View v)
        {
		setLineColor(v.getLineColor());
		setLineWidth(new Float(v.getLineWidth()));
		fillColor = v.getFillColor();
		fillPattern = v.getFillPattern();
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
	
	public void setFillPattern(TexturePaint fillPattern)
	{
		this.fillPattern = fillPattern;
	}
	public TexturePaint getFillPattern()
	{
		return fillPattern;
	}

	
}
