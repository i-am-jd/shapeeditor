package DragNDrop_JTree;

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

/**
 * A drag source objet to drag'N'drop in the JTree
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class TreeDragSource implements DragSourceListener, DragGestureListener {

    DragSource source;
    DragGestureRecognizer recognizer;
    TransferableTreeNode transferable;
    MutableTreeNode oldNode;
    JTree sourceTree;

    /**
     * Returns a drag source objet
     * @param tree
     * @param actions
     */
    public TreeDragSource(JTree tree, int actions) {
        sourceTree = tree;
        source = new DragSource();
        recognizer = source.createDefaultDragGestureRecognizer(sourceTree, actions, this);
    }

    /*
     * Drag Gesture Handler
     */
    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        TreePath path = sourceTree.getSelectionPath();
        if ((path == null) || (path.getPathCount() <= 1)) {
            // We can't move the root node or an empty selection
            return;
        }
        oldNode = (MutableTreeNode) path.getLastPathComponent();
        transferable = new TransferableTreeNode(path);
        source.startDrag(dge, DragSource.DefaultMoveDrop, transferable, this);

    }

    /*
     * Drag Event Handlers
     */
    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
        //System.out.println("dragEnter");
        // nothing yet
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
        //System.out.println("dragExit");
        //System.out.println(oldNode);
        // Nothing yet
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
        // Nothing yet
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
        System.out.println("Action: " + dsde.getDropAction());
        System.out.println("Target Action: " + dsde.getTargetActions());
        System.out.println("User Action: " + dsde.getUserAction());
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        if (dsde.getDropSuccess()) {
            ((DefaultTreeModel) sourceTree.getModel()).removeNodeFromParent(oldNode);
            System.out.println(((DefaultTreeModel) sourceTree.getModel()).getClass().toString());
        }
    }
}
