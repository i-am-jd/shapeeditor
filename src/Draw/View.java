package Draw;

import java.awt.Color;
import java.awt.TexturePaint;


/**
 *
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
         *
         * @param c
         * @param w
         * @param fillC
         * @param fillP
         */
        public View(Color c, float w, Color fillC, TexturePaint fillP)
	{
		setLineColor(c);
		setLineWidth(w);
		fillColor = fillC;
		fillPattern = fillP;
	}
        /* Constructeur de copie (copie profonde) */
        /**
         *
         * @param v
         */
        public View(View v)
        {
		setLineColor(v.getLineColor());
		setLineWidth(new Float(v.getLineWidth()));
		fillColor = v.getFillColor();
		fillPattern = v.getFillPattern();
	}
	
        /**
         *
         * @param lineColor
         */
        public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
        /**
         *
         * @return
         */
        public Color getLineColor() {
		return lineColor;
	}

        /**
         *
         * @param lineWidth
         */
        public void setLineWidth(float lineWidth) {
		this.lineWidth = lineWidth;
	}
        /**
         *
         * @return
         */
        public float getLineWidth() {
		return lineWidth;
	}

        /**
         *
         * @param fillColor
         */
        public void setFillColor(Color fillColor)
	{
		this.fillColor = fillColor;
	}
        /**
         *
         * @return
         */
        public Color getFillColor() {
		return fillColor;
	}
	
        /**
         *
         * @param fillPattern
         */
        public void setFillPattern(TexturePaint fillPattern)
	{
		this.fillPattern = fillPattern;
	}
        /**
         *
         * @return
         */
        public TexturePaint getFillPattern()
	{
		return fillPattern;
	}

	
}
