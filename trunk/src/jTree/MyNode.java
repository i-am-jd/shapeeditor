package jTree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Classe représentant un noeud d'un graphe
 * Cette classe implémente {@link MutableTreeNode} de manière à ce que le noeud
 * soit modifiable dans un JTree.
 * Cette Classe implémente l'interface {@link Cloneable} de manière à ce qu'une 
 * copie de noeud possède exactement les mêmes caractéristiques (Non utilisé ici)
 * Cette classe implémente l'interface {@link Serializable} car c'est le mécanisme
 * utilisé dans les JTree pour déplacer les noeuds avec le DragNDrop. Cela suppose
 * implicitement que la classe doit implémenter les méthodes writeObject et
 * readObject
 * @author Depoyant Guillaume & Ludmann Michaël
 *
 */
abstract class MyNode implements MutableTreeNode, Cloneable, Serializable
{

	/**
	 * Le parent du noeud courant 
	 * (peut être null si on est à la racine du graphe)
	 */
	protected MutableTreeNode parent;
	
	/**
	 * Le nom du noeud
	 */
	protected String name;
	
	/**
	 * Constructeur par défaut protégé d'un noeud (utilisé uniquement dans les 
	 * classes filles).
	 */
	protected MyNode()
	{
		this(null);
	}
	
	/**
	 * Constructeur protégé d'un noeud avec un parent (utilisé uniquement dans 
	 * les classes filles).
	 * @param parent le parent de ce noeud.
	 */
	protected MyNode(MutableTreeNode parent)
	{
		this.parent = parent;
		if (this.parent != null)
		{
			this.parent.insert(this, this.parent.getChildCount());
		}
		else
		{
			System.err.println(this + "::MyNode(null)");
		}
	}

	@Override
    public void removeFromParent()
    {
    	if (parent != null)
    	{
    		parent.remove(this);
    	}
    	else
    	{
    		System.err.println(this + "::removeFromParent : null parent");
    	}
    }

	@Override
    public void setParent(MutableTreeNode newParent)
    {
		parent = newParent;
		if (newParent == null)
		{
			System.err.println("MyNode::setParent : Caution null parent");
		}
    }

	@Override
    public TreeNode getParent()
    {
    	return parent;
    }
	
	@Override
    public void setUserObject(Object object)
    {
		// rien pour l'instant
    }

	// Serialization support.
	private void writeObject(ObjectOutputStream s) throws IOException
	{
		/*
		 * Write the non-static and non-transient fields of the current class 
		 * to the stream
		 */
		s.defaultWriteObject();
	}

	private void readObject(ObjectInputStream s) throws IOException,
	        ClassNotFoundException
	{
		/*
		 * Read the non-static and non-transient fields of the current class 
		 * from this stream
		 */
		s.defaultReadObject();
	}

	@Override
    public String toString()
    {
	    return name;
    }
}
