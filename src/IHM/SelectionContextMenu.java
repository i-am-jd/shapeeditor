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
 *
 * @author Boris
 */
public class SelectionContextMenu extends JPopupMenu {

    private JMenuItem copyItem;
    private JMenuItem delItem;

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
