package Draw;

import java.awt.geom.Ellipse2D;


public class Ellipse extends SceneShape {

    private Ellipse2D.Double ellipse;

    public Ellipse(View v, double x, double y, double width, double height) {
        super(v);
        ellipse = new Ellipse2D.Double(x, y, width, height);
        shape = ellipse;
        baseShape = ellipse;
    }

    public Ellipse (Ellipse e) {
        super(e.view);
        ellipse = new Ellipse2D.Double(e.ellipse.x+5, e.ellipse.y+5, e.ellipse.width, e.ellipse.height);
        shape = ellipse;
    }
}
