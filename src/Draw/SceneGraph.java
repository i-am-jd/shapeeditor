package Draw;

import java.util.Enumeration;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.tree.DefaultMutableTreeNode;

import java.util.Stack;


public class SceneGraph extends DefaultMutableTreeNode {
	/**
	 * @uml.property  name="view"
	 * @uml.associationEnd  inverse="sceneGraph:View"
	 */
	protected View view;
        
        protected Stack<SceneGraph> stack = new Stack();

        public void add(SceneGraph s)
        {

            super.add(s);
            stack.push(s);
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
            for(Enumeration<SceneGraph> en = stack.elements(); en.hasMoreElements();) {
                en.nextElement().draw(g2d);
            }
        }

}
