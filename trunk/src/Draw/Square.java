package Draw;


/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Square extends Rectangle {

    /**
     *
     * @param v
     * @param x
     * @param y
     * @param w
     */
    public Square(View v, double x, double y, double w) {
        super(v, x, y, w, w);

        setUserObject("Square");
    }
    
}
