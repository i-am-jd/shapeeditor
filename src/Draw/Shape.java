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
        System.out.println("Creation de shape");
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
        /*BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND);
        g2d.setStroke(stroke);
         */

        if (geometry instanceof Ellipse) {
            //Ellipse2D e2d = new Ellipse2D.Double(e.getX(), e.getY(), e.getSemiMajorAxis(), e.getSemiMinorAxis());
            //g2d.draw(e2d);
            Ellipse e = (Ellipse) geometry;
            g2d.setColor(view.getFillColor());
            g2d.fillOval(e.getX()-e.getSemiMajorAxis(), e.getY()-e.getSemiMinorAxis(), 2*e.getSemiMajorAxis(), 2*e.getSemiMinorAxis());
            //g2d.fillOval(e.getX(), e.getY(), e.getWidth(), e.getHeight());
            g2d.setStroke(new BasicStroke(view.getLineWidth()));
            g2d.setColor(view.getLineColor());
            g2d.drawOval(e.getX()-e.getSemiMajorAxis(), e.getY()-e.getSemiMinorAxis(), 2*e.getSemiMajorAxis(), 2*e.getSemiMinorAxis());
            //g2d.drawOval(e.getX(), e.getY(), e.getWidth(), e.getHeight());
        } else if (geometry instanceof Rectangle) {
            //Rectangle2D r2d = new Rectangle2D.Double(r.getX1(), r.getY1(), r.getX2(), r.getY2());
            //g2d.draw(r2d);
            Rectangle r = (Rectangle) geometry;
            g2d.setColor(view.getFillColor());
            g2d.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
            g2d.setStroke(new BasicStroke(view.getLineWidth()));
            g2d.setColor(view.getLineColor());
            g2d.drawRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        } else {
            // to be continued
        }
    }

    /* Insere la shape sur le noeud n passe en argument */
    public void insertShape(DefaultMutableTreeNode n)
    {
        // if (n isInstanceOf Shape) => error
        n.add(this);
    }

    public void changeSize(float width, float height)
    {
        Rectangle r = (Rectangle) geometry;
        r.setWidth((int)width);
        r.setHeight((int)height);
        //Circle r = (Circle) geometry;
        //r.setRadius((int)width);
        
    }
}
