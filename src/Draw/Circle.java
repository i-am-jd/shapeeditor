package Draw;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Circle extends Ellipse {

    /**
     *
     * @param v
     * @param x
     * @param y
     * @param r
     */
    public Circle(View v, double x, double y, double r) {
        super(v, x, y, r, r);

        setUserObject("Circle");
    }
}
