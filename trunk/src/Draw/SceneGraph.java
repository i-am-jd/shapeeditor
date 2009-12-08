package Draw;

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
            view = null;
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

}
