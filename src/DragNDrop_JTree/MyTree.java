package DragNDrop_JTree;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;


/**
 * @author Boris Dadachev & Jean-Denis Koeck
 *
 */
public class MyTree
{
    /**
     *
     */
    protected MutableTreeNode root;
	
        /**
         *
         */
        @SuppressWarnings("unused")
	public MyTree()
	{
		root = new MyBranchNode();
		MyBranchNode cat1 = new MyBranchNode(root);
		MyBranchNode cat2 = new MyBranchNode(root);
		MyBranchNode cat3 = new MyBranchNode(root);
		
        MyLeafNode item11 = new MyLeafNode(cat1);
		MyLeafNode item12 = new MyLeafNode(cat1);
		MyLeafNode item21 = new MyLeafNode(cat2);
		MyLeafNode item31 = new MyLeafNode(cat3);

		MyBranchNode subcat3 = new MyBranchNode(cat3);
		MyLeafNode item311 = new MyLeafNode(subcat3);
		
	}

        /**
         *
         * @param node
         * @param level
         * @return
         */
        public static String toStringNode(TreeNode node, int level)
	{
		StringBuffer sb = new StringBuffer();

		if (node != null)
		{
			for (int i = 0; i < level; i++)
			{
				sb.append(" ");
			}
			sb.append(node.toString());
			
			if (node.getAllowsChildren())
			{
				for (Enumeration<?> en = node.children(); en.hasMoreElements();)
				{
					sb.append("\n");
					sb.append(toStringNode((TreeNode) en.nextElement(),
					        level + 1));
				}
			}
		}
		return new String(sb);
	}

        /**
         *
         * @return
         */
        public MutableTreeNode getRoot()
    {
    	return root;
    }
	
}
