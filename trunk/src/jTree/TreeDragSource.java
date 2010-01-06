package jTree;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import Draw.SceneGraph;
import java.awt.Cursor;


//TreeDragSource.java
//A drag source wrapper for a JTree. This class can be used to make
//a rearrangeable DnD tree with the TransferableTreeNode class as the
//transfer data type.
/**
 * @author
 *
 */
public class TreeDragSource implements DragSourceListener, DragGestureListener
{

	DragSource source;

	DragGestureRecognizer recognizer;

	TransferableTreeNode transferable;

	MutableTreeNode oldNode;

	JTree sourceTree;

	public TreeDragSource(JTree tree, int actions)
	{
		sourceTree = tree;
		source = new DragSource();
		recognizer = source.createDefaultDragGestureRecognizer(sourceTree, actions, this);
	}

	/*
	 * Drag Gesture Handler
	 */
	public void dragGestureRecognized(DragGestureEvent dge)
	{
		TreePath path = sourceTree.getSelectionPath();
		if ((path == null) || (path.getPathCount() <= 1))
		{
			// We can't move the root node or an empty selection
			return;
		}
		oldNode = (MutableTreeNode) path.getLastPathComponent();
		transferable = new TransferableTreeNode(path);
		source.startDrag(dge, DragSource.DefaultMoveNoDrop, transferable, this);

	}

	/*
	 * Drag Event Handlers
	 */
	public void dragEnter(DragSourceDragEvent dsde)
	{
        //System.out.println("dragEnter");
	// nothing yet
	}

	public void dragExit(DragSourceEvent dse)
	{
        //System.out.println("dragExit");
        //System.out.println(oldNode);
		// Nothing yet
	}

	public void dragOver(DragSourceDragEvent dsde)
	{
        // Nothing yet
	}

	public void dropActionChanged(DragSourceDragEvent dsde)
	{
		System.out.println("Action: " + dsde.getDropAction());
		System.out.println("Target Action: " + dsde.getTargetActions());
		System.out.println("User Action: " + dsde.getUserAction());
	}

	public void dragDropEnd(DragSourceDropEvent dsde)
	{
		/*
		 * to support move or copy, we have to check which occurred:
		 */
		//System.out.println("Drop Action: " + dsde.getDropAction());
		//sourceTree.setCursor(new Cursor(Cursor.MOVE_CURSOR));

		if ( dsde.getDropSuccess() )
		{
			DefaultTreeModel tm = (DefaultTreeModel) sourceTree.getModel();
			
			if( dsde.getDropAction() == 2 )
			{
				((SceneGraph) oldNode).removeFromParent();
			}
			
			tm.reload();
			//tm.removeFromParentFromParent(oldNode);
			//TreeNode root = (TreeNode) tm.getRoot();
			//System.out.println("TreeDragSource after drop : \n" + MyTree.toStringNode(root, 0));
		}

		/*
		 * to support move only... if (dsde.getDropSuccess()) {
		 * ((DefaultTreeModel
		 * )sourceTree.getModel()).removeFromParentFromParent(oldNode); }
		 */
	}
}
