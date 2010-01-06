package Draw;

import java.util.Vector;

public class Group extends SceneGraph {
		
    public Group(View v) {
        super(v, "Group");
    }

    public Group(View v, String s) {
        super(v, s);
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
