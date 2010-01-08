/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import IHM.DrawPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener user for the context menu displayed when the user performs a
 * right click on a selected shape or a node of the JTree and wants to copy it
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class CopyActionListener implements ActionListener {

    private DrawPanel drawZone;

    /**
     * Returns a listener
     * @param drawZone
     */
    public CopyActionListener(DrawPanel drawZone) {
        super();
        this.drawZone = drawZone;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        drawZone.copyCurrentSelection();
        drawZone.repaintPanel();
    }
}
