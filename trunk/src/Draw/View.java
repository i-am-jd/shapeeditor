package Draw;

import java.awt.Color;
import java.awt.TexturePaint;


/**
 * Contient les paramètres du dessin d'une forme géométrique :
 * couleur et épaisseur de trait, couleur ou motif de remplissage
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class View {

    /** Couleur du trait */
    private Color lineColor;

    /** Epaisseur du trait */
    private float lineWidth;

    /** Couleur de remplissage */
    private Color fillColor;

    /** Motif de remplissage */
    private TexturePaint fillPattern;
	
    /**
     * Constructeur de la vue
     * @param c     couleur de trait
     * @param w     épaisseur du trait
     * @param fillC couleur de remplissage
     * @param fillP texture de remplissage (éventuellement <code>null</code>)
     */
    public View(Color c, float w, Color fillC, TexturePaint fillP)
    {
	setLineColor(c);
	setLineWidth(w);
	fillColor = fillC;
	fillPattern = fillP;
    }

    /**
     * Constructeur de copie (copie profonde)
     * @param v vue à copier
     */
    public View(View v)
    {
	setLineColor(v.getLineColor());
	setLineWidth(new Float(v.getLineWidth()));
	fillColor = v.getFillColor();
	fillPattern = v.getFillPattern();
    }
	
    /**
     * Modifie la couleur de trait
     * @param lineColor nouvelle couleur de trait
     */
    public void setLineColor(Color lineColor) {
	this.lineColor = lineColor;
    }

    /**
     * Getter de l'attribut <tt>lineColor</tt>
     * @return couleur de trait
     */
    public Color getLineColor() {
	return lineColor;
    }

    /**
     * Modifie l'épaisseur du trait
     * @param lineWidth épaisseur du trait
     */
    public void setLineWidth(float lineWidth) {
	this.lineWidth = lineWidth;
    }

    /**
     * Getter de l'attribut <tt>lineWidth</tt>
     * @return l'épaisseur du trait
     */
    public float getLineWidth() {
	return lineWidth;
    }

    /**
     * Modifie la couleur de remplissage
     * @param fillColor nouvelle couleur de remplissage
     */
    public void setFillColor(Color fillColor)
    {
	this.fillColor = fillColor;
    }

    /**
     * Getter de l'attribut <tt>fillColor</tt>
     * @return couleur de remplissage
     */
    public Color getFillColor() {
	return fillColor;
    }
	
    /**
     * Modifie le motif de remplissage
     * @param fillPattern nouveau motif de remplissage
     */
    public void setFillPattern(TexturePaint fillPattern)
    {
	this.fillPattern = fillPattern;
    }

    /**
     * Getter de l'attribut <tt>fillPattern</tt>
     * @return motif de remplissage
     */
    public TexturePaint getFillPattern()
    {
	return fillPattern;
    }

	
}
