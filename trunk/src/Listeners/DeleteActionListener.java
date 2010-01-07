package Listeners;

import IHM.DrawPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class DeleteActionListener implements ActionListener {

    private DrawPanel drawZone;

    /**
     *
     * @param drawZone
     */
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
