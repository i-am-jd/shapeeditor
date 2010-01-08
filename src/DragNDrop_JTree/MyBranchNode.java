package DragNDrop_JTree;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Noeud du graphe contenant d'autres noeuds
 * @author David Roussel
 */
class MyBranchNode extends MyNode {

    /**
     * Les enfants de cette branche :
     * <ul>
     * <li>soit d'autres BranchNodes</li>
     * <li>soit des LeafNodes</li>
     * </ul>
     */
    protected Vector<MutableTreeNode> children;
    /**
     * Compteur de branches.
     */
    protected static int BranchNodeCount = 0;
    /**
     * Le num�ro courant de banche � attribuer � la prochaine branche
     */
    protected int branchNodeNumber;

    /**
     * Constructeur par d�faut d'un noeud de branche avec un parent null
     * (utilis� pour la racine du graphe)
     */
    public MyBranchNode() {
        this(null);
    }

    /**
     * Constructeur valu� d'un noeud de branche
     * @param parent le parent de ce noeud de branche
     */
    public MyBranchNode(MutableTreeNode parent) {
        super(parent);
        name = new String("BranchNode");
        branchNodeNumber = ++BranchNodeCount;
        children = new Vector<MutableTreeNode>();
    }

    /**
     * Constructeur prot�g� utilis� dans la m�thode clone
     * @param parent le parent
     * @param children les enfants
     * @param number le n� de noeud (qui doit �tre identique contrairement � une
     *            cr�ation normale de noeud).
     */
    protected MyBranchNode(MutableTreeNode parent,
            Vector<MutableTreeNode> children, int number) {
        name = new String("BranchNode");
        this.parent = parent;
        this.children = children;
        branchNodeNumber = number;
    }

    /*
     * (non-Javadoc)
     * @see
     * javax.swing.tree.MutableTreeNode#insert(javax.swing.tree.MutableTreeNode,
     * int)
     */
    @Override
    public void insert(MutableTreeNode newChild, int index) {
        if (newChild != null) {
            MutableTreeNode oldParent = (MutableTreeNode) newChild.getParent();

            if (oldParent != null) {
                oldParent.remove(newChild);
            }

            newChild.setParent(this);

            if (children == null) {
                children = new Vector<MutableTreeNode>();
            }

            children.insertElementAt(newChild, index);
        } else {
            System.err.println(this + "::insert(" + newChild + ") : null node insertion");
        }
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.tree.MutableTreeNode#remove(int)
     */
    @Override
    public void remove(int index) {
        children.remove(index);
    }

    /*
     * (non-Javadoc)
     * @see
     * javax.swing.tree.MutableTreeNode#remove(javax.swing.tree.MutableTreeNode)
     */
    @Override
    public void remove(MutableTreeNode node) {
        if (node != null) {
            if (!children.remove(node)) {
                System.err.println(this + "::remove(" + node + " ) failed !");
            }
        } else {
            System.err.println(this + "::remove(null) cannot remove null child");
        }
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.tree.TreeNode#children()
     */
    @Override
    public Enumeration<MutableTreeNode> children() {
        return children.elements();
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.tree.TreeNode#getAllowsChildren()
     */
    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.tree.TreeNode#getChildAt(int)
     */
    @Override
    public TreeNode getChildAt(int childIndex) {
        return children.elementAt(childIndex);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.tree.TreeNode#getChildCount()
     */
    @Override
    public int getChildCount() {
        return children.size();
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
     */
    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.tree.TreeNode#isLeaf()
     */
    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public String toString() {
        return new String(super.toString() + Integer.toString(branchNodeNumber));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new MyBranchNode(parent, children, branchNodeNumber);
    }
}
