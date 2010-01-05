package Draw;

import java.util.Vector;

public class Group extends SceneGraph {
		
    public Group() {
        super("Group");
    }

    public Group(String s) {
        super(s);
    }

    public void ungroup()
    {
        Vector<SceneGraph> currentChildren = (Vector) this.children.clone();

        for(SceneGraph child : currentChildren) {
            System.out.println(child.toString());
            ((SceneGraph) this.getParent()).add(child);
        }

        this.removeFromParent();
    }

}
