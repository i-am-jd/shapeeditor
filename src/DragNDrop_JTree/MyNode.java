package DragNDrop_JTree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Classe repr�sentant un noeud d'un graphe
 * Cette classe impl�mente {@link MutableTreeNode} de mani�re � ce que le noeud
 * soit modifiable dans un JTree.
 * Cette Classe impl�mente l'interface {@link Cloneable} de mani�re � ce qu'une 
 * copie de noeud poss�de exactement les m�mes caract�ristiques (Non utilis� ici)
 * Cette classe impl�mente l'interface {@link Serializable} car c'est le m�canisme
 * utilis� dans les JTree pour d�placer les noeuds avec le DragNDrop. Cela suppose
 * implicitement que la classe doit impl�menter les m�thodes writeObject et
 * readObject
 * @author Boris Dadachev & Jean-Denis Koeck
 *
 */
abstract class MyNode implements MutableTreeNode, Cloneable, Serializable {

    /**
     * Le parent du noeud courant
     * (peut �tre null si on est � la racine du graphe)
     */
    protected MutableTreeNode parent;
    /**
     * Le nom du noeud
     */
    protected String name;

    /**
     * Constructeur par d�faut prot�g� d'un noeud (utilis� uniquement dans les
     * classes filles).
     */
    protected MyNode() {
        this(null);
    }

    /**
     * Constructeur prot�g� d'un noeud avec un parent (utilis� uniquement dans
     * les classes filles).
     * @param parent le parent de ce noeud.
     */
    protected MyNode(MutableTreeNode parent) {
        this.parent = parent;
        if (this.parent != null) {
            this.parent.insert(this, this.parent.getChildCount());
        } else {
            System.err.println(this + "::MyNode(null)");
        }
    }

    @Override
    public void removeFromParent() {
        if (parent != null) {
            parent.remove(this);
        } else {
            System.err.println(this + "::removeFromParent : null parent");
        }
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        parent = newParent;
        if (newParent == null) {
            System.err.println("MyNode::setParent : Caution null parent");
        }
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public void setUserObject(Object object) {
        // rien pour l'instant
    }

    // Serialization support.
    private void writeObject(ObjectOutputStream s) throws IOException {
        /*
         * Write the non-static and non-transient fields of the current class
         * to the stream
         */
        s.defaultWriteObject();
    }

    private void readObject(ObjectInputStream s) throws IOException,
            ClassNotFoundException {
        /*
         * Read the non-static and non-transient fields of the current class
         * from this stream
         */
        s.defaultReadObject();
    }

    @Override
    public String toString() {
        return name;
    }
}
