package Draw;

import java.util.Enumeration;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import javax.swing.tree.DefaultMutableTreeNode;


public class SceneGraph extends DefaultMutableTreeNode {
	/**
	 * @uml.property  name="view"
	 * @uml.associationEnd  inverse="sceneGraph:View"
	 */
	protected View view;

	public SceneGraph(View v, String name)
	{
            super(name);
            setView(v);
	}
	public SceneGraph(String name)
	{
            super(name);
            view = new View(Color.BLACK, 1, Color.BLACK, null);
	}

        public SceneGraph(SceneShape s)
        {
            super();
            view = new View(Color.BLACK, 1, Color.BLACK, null);
            this.add(s);
        }

        /*
        @Override
        public SceneGraph clone()
        {
            return new SceneGraph(this.view, "Scene Graph");
        }*/
       @Override
    public SceneGraph clone() {
        SceneGraph node = (SceneGraph) super.clone();
        for (Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
            node.add((en.nextElement()).clone());
        }

        return node;
    }
	/**
	 * Getter of the property <tt>view</tt>
	 * @return  Returns the view.
	 * @uml.property  name="view"
	 */
	public View getView() {
		return view;
	}	

	/**
	 * Setter of the property <tt>view</tt>
	 * @param view  The view to set.
	 * @uml.property  name="view"
	 */
	public void setView(View view) {
		this.view = view;
	}
        
        public void draw(Graphics2D g2d) {
            for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                en.nextElement().draw(g2d); //, scale, shear);
            }
                //this.draw(g2d, 0, 1, 1, 0, 0);
        }

        public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {
            for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                en.nextElement().draw(g2d, rotate, scaleX, scaleY, shearX, shearY); //, scale, shear);
            }
        }

        public boolean contains(Point2D p)
        {
            for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                if(en.nextElement().contains(p)) {
                    return true;
                }
            }
            return false;
        }

        public Rectangle2D getBounds2D()
        {
            Rectangle2D r;
            Enumeration<SceneGraph> en = this.children();

            if(!en.hasMoreElements()) {
                return null;
            } else {
                r = en.nextElement().getBounds2D();
                while(en.hasMoreElements()) {
                    r = r.createUnion(en.nextElement().getBounds2D());
                }
                return r;
            }
        }

        //Monte un graphe de scène enfant un premier plan (sera dessiné en priorité)
        public void moveToFront(SceneGraph g)
        {
            this.remove(g);
            this.add(g);
        }

        public void applyTransform(AffineTransform trans)
        {
            for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                en.nextElement().applyTransform(trans);
            }
        }

        public double getBarycenterX()
        {
            double x = 0;
            for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                x += en.nextElement().getBarycenterX();
            }
            return x / this.getChildCount();
        }

        public double getBarycenterY()
        {
            double y = 0;
            for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                y += en.nextElement().getBarycenterY();
            }
            return y / this.getChildCount();
        }

        public void translate(double x, double y)
        {
            for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                en.nextElement().translate(x, y);
            }

        }

        public Shape getShape()
        {
            Area area = new Area();
            for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                SceneGraph sg = en.nextElement();
                area.add(new Area(sg.getShape()));
            }
            return area;
        }
}
