package Draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import javax.swing.tree.DefaultMutableTreeNode;

public class Shape extends SceneGraph
{

    /**
     * @uml.property name="geometry"
     * @uml.associationEnd multiplicity="(1 1)" aggregation="composite" inverse="shape:Geometry"
     */
    private Geometry geometry = null;

    public Shape(View v, Geometry g)
    {
        super(v, "Shape");
        /* Une forme est une feuille du graphe de scene */
        geometry = g;
        //System.out.println("Creation de shape");
    }

    /**
     * Getter of the property <tt>geometry</tt>
     * @return  Returns the geometry.
     * @uml.property  name="geometry"
     */
    public Geometry getGeometry()
    {
        return geometry;
    }
    /**
     * Setter of the property <tt>geometry</tt>
     * @param geometry  The geometry to set.
     * @uml.property  name="geometry"
     */
    public void setGeometry(Geometry geometry)
    {
        this.geometry = geometry;
    }

    public void drawShape(Graphics2D g2d)
    {
        g2d.setColor(view.getFillColor());
        this.geometry.fillGeometry(g2d);
        g2d.setStroke(new BasicStroke(view.getLineWidth()));
        g2d.setColor(view.getLineColor());
        this.geometry.drawGeometry(g2d);
    }

    /* Insere la shape sur le noeud n passe en argument */
    public void insertShape(DefaultMutableTreeNode n)
    {
        // if (n isInstanceOf Shape) => error
        n.add(this);
    }

    /*public void changeSize(float width, float height)
    {
        Rectangle r = (Rectangle) geometry;
        r.setWidth((int)width);
        r.setHeight((int)height);       
    }*/
}
