package Draw;

import java.util.Vector;

/**
 * Noeud du graphe de scène regroupant plusieurs formes géométriques
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Group extends SceneGraph {
		
    /**
     * Constructeur par défaut
     * @param view paramètres de dessin
     */
    public Group(View view) {
        super(view, "Group");
    }

    /**
     * Constructeur pour un noeud particulier
     * @param view paramètres de dessin
     * @param name nom du groupe
     */
    public Group(View view, String name) {
        super(view, name);
    }

    /**
     * Détruit le groupe : ses sous-graphes sont réattachés à son parent
     * et le groupe est retiré du graphe de scène
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
