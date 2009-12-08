package Draw;

import java.util.Collection;



public class Group extends SceneGraph {
	
	Collection<Group> groupC = null;
	Collection<Shape> shapeC = null;
	
	
	public Group()
	{
		super("Group");
	}
	
	public void addShape(Shape s)
	{
		shapeC.add(s);
	}
	
	public void addGroup(Group g)
	{
		groupC.add(g);
	}

}
