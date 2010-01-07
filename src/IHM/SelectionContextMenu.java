/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IHM;

import Listeners.CopyActionListener;
import Listeners.DeleteActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Class instantiating a context menu allowing to copy and delete
 * a node of the scene graph
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class SelectionContextMenu extends JPopupMenu {

    private JMenuItem copyItem;
    private JMenuItem delItem;

    /**
     *
     * @param drawZone
     */
    public SelectionContextMenu(DrawPanel drawZone) {
        super();
        copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(new CopyActionListener(drawZone));
        delItem = new JMenuItem("Delete");
        delItem.addActionListener(new DeleteActionListener(drawZone));
        this.add(copyItem);
        this.add(delItem);
    }

}
