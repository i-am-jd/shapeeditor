package Draw;

import javax.swing.tree.MutableTreeNode;

public class Group extends SceneGraph {
		
	public Group()
	{
		super("Group");
	}
	
	public void addToGroup (MutableTreeNode n)
	{
		this.add(n);
	}

        

}
