/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import IHM.DrawPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Boris
 */
public class DeleteActionListener implements ActionListener {

    private DrawPanel drawZone;

    public DeleteActionListener(DrawPanel drawZone) {
        super();
        this.drawZone = drawZone;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        drawZone.deleteCurrentSelection();
        drawZone.repaintPanel();
    }
}
