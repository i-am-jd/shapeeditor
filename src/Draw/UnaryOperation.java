package Draw;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Stack;

/**
 * Transformation unaire appliquée à un sous-graphe de scène.
 * Contient nécessairement un seul sous-graphe.
 *
 * Notons que cette classe ne contient pas directement une liste de transformations
 * affines, mais plutôt une méthode renvoyant les transformations affines à effectuer
 * sur une figure donnée. En effet, le calcul des transformations affines liées à un
 * redimensionnement nécessite de connaire le barycentre de la figure à transformer.
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public abstract class UnaryOperation extends Transformation {

    /**
     * Constructeur par défaut.
     * @param name  nom de la transformation
     * @param child sous-graphe auquel la transformation est appliquée
     */
    public UnaryOperation(String name, SceneGraph child)
    {
        super(child.getView(), name);
        this.add(child);
        this.beginEdit();
    }
    
    /**
     * Calcule les transformations affine à effectuer sur une forme géométrique donnée
     * @param   shape forme à modifier
     * @return une liste de transformations affine à effectuer dans l'ordre donné
     */
    abstract protected ArrayList<AffineTransform> toAffineTransforms(Shape shape);

    /**
     * Lors du parcours du graphe afin d'appliquer les transformations,
     * la pile <tt>unaryOps</tt> contient les transformations unaires à effectuer.
     * Avec cette méthode, la transformation s'empile dans <tt>unaryOps</tt>,
     * appelle la méthode de parcours sur le sous-graphe, puis se dépile.
     * @param unaryOps pile des transformations à effectuer
     */
    @Override
    public void applyUnaryOperations(Stack<UnaryOperation> unaryOps)
    {
        unaryOps.push(this);

        for(Enumeration<SceneGraph> en = this.children(); en.hasMoreElements();) {
            en.nextElement().applyUnaryOperations(unaryOps);
        }

        unaryOps.pop();
    }

    /**
     * Initialise la transformation unaire par rapport à un sous-graphe
     * @param child sous-graphe que l'utilisateur s'apprête à éditer
     */
    abstract public void beginEditNode(SceneGraph child);

    /**
     * Initialise la transformation unaire par rapport au sous-graphe courant
     */
    public void beginEdit()
    {
        assert(this.getChildCount() == 1);
        SceneGraph child = (SceneGraph) this.getChildAt(0);
        this.beginEditNode(child);
    }
}
