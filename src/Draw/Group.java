package Draw;

import java.util.Vector;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Group extends SceneGraph {
		
    /**
     *
     * @param v
     */
    public Group(View v) {
        super(v, "Group");
    }

    /**
     *
     * @param v
     * @param s
     */
    public Group(View v, String s) {
        super(v, s);
    }

    /**
     *
     */
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
