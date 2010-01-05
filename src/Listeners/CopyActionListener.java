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
public class CopyActionListener implements ActionListener {

    private DrawPanel drawZone;

    public CopyActionListener(DrawPanel drawZone) {
        super();
        this.drawZone = drawZone;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        drawZone.copyCurrentSelection();
        drawZone.repaint();
    }
}
