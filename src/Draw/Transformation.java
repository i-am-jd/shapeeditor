package Draw;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public abstract class Transformation extends Group {

    /**
     *
     * @param v
     */
    public Transformation(View v) {
        super(v, "Transformation");
    }

    /**
     *
     * @param v
     * @param s
     */
    public Transformation(View v, String s) {
        super(v, s);
    }
}
