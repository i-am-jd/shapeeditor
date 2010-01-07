/**
 * 
 */
package DragNDrop_JTree;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * @author Boris Dadachev & Jean-Denis Koeck
 * 
 */
class MyLeafNode extends MyNode
{
	protected static int LeafNodeCount = 0;

	protected int leafNodeNumber;

	public MyLeafNode()
	{
		this(null);
	}

	public MyLeafNode(MutableTreeNode parent)
	{
		super(parent);
		name = new String("LeafNode");
		leafNodeNumber = ++LeafNodeCount;
	}

	/**
	 * Constructeur prot�g� utilis� dans la m�thode clone
	 * @param parent le parent
	 * @param number le num�ro (qui doit �tre identique contrairement � une
	 *            cr�ation normale de noeud)
	 */
	protected MyLeafNode(MutableTreeNode parent, int number)
	{
		name = new String("LeafNode");
		this.parent = parent;
		leafNodeNumber = number;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.swing.tree.MutableTreeNode#insert(javax.swing.tree.MutableTreeNode,
	 * int)
	 */
	@Override
	public void insert(MutableTreeNode child, int index) 
	{
		// No Action, this is a leaf node
		throw new IllegalStateException("Leaf Nodes does not have chilren");
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.tree.MutableTreeNode#remove(int)
	 */
	@Override
	public void remove(int index)
	{
		// No Action, this is a leaf node
		throw new IllegalStateException("Leaf Nodes does not have children");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.swing.tree.MutableTreeNode#remove(javax.swing.tree.MutableTreeNode)
	 */
	@Override
	public void remove(MutableTreeNode node)
	{
		// No Action, this is a leaf node
		throw new IllegalStateException("Leaf Nodes does not have children");
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#children()
	 */
	@Override
	public Enumeration<MutableTreeNode> children()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	@Override
	public boolean getAllowsChildren()
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	@Override
	public TreeNode getChildAt(int childIndex)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	@Override
	public int getChildCount()
	{
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
	 */
	@Override
	public int getIndex(TreeNode node)
	{
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	@Override
	public boolean isLeaf()
	{
		return true;
	}

	@Override
	public String toString()
	{
		return new String(super.toString() + Integer.toString(leafNodeNumber));
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		return new MyLeafNode(parent, leafNodeNumber);
	}

}
