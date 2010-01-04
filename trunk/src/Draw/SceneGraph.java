package Draw;

import java.util.Enumeration;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Point;
import javax.swing.tree.DefaultMutableTreeNode;

import java.util.Stack;


public class SceneGraph extends DefaultMutableTreeNode {
	/**
	 * @uml.property  name="view"
	 * @uml.associationEnd  inverse="sceneGraph:View"
	 */
	protected View view;

        //Enfants du noeud courant
        //Donne un ordre de priorité pour leur affichage
        protected Stack<SceneGraph> stack = new Stack();

        public void add(SceneGraph s)
        {
            super.add(s);
            stack.push(s);
        }

        public void remove(SceneGraph s)
        {
            super.remove(s);
            stack.remove(s);
        }

        public Stack<SceneGraph> getStack()
        {
            return stack;
        }

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

        @Override
        public SceneGraph clone()
        {
            return new SceneGraph(this.view, "Scene Graph");
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

       /*public void draw(Graphics2D g2d) {
            for(Enumeration<SceneGraph> en = this.getStack(); en.hasMoreElements();) { //stack
                 en.nextElement().draw(g2d, 0);
            }
        }*/
        
        public void draw(Graphics2D g2d) {
                this.draw(g2d, 0, 1, 1, 0, 0);
        }

        public void draw(Graphics2D g2d, double rotate, double scaleX, double scaleY, double shearX, double shearY) {
            for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                en.nextElement().draw(g2d, rotate, scaleX, scaleY, shearX, shearY); //, scale, shear);
            }
        }

        public void setLocation(Point2D p)
        {
            for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                en.nextElement().setLocation(p);
            }
        }

        public void setOffset(Point p)
        {
            for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                en.nextElement().setOffset(p);
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
        
        public void removeNode(SceneGraph s)
        {
            // reste a prevoir le cas ou on supprime un fils d'une operation binaire, par ex.
            stack.remove(s);
            s.removeFromParent();

            //SceneGraph currentNode;
            /*for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
                if((currentNode = en.nextElement()).equals(s)) {
                    currentNode.removeFromParent();
                } else {
                    //recherche en profondeur
                    currentNode.removeNode(s);
                }
            }*/
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

}
