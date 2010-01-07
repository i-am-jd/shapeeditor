package Draw;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Rotation extends UnaryOperation {

    /**
     *
     * @param child
     * @param angle
     */
    public Rotation(SceneGraph child, double angle) {
        super("Rotation", TransFlag.Rotate, child);
        this.setRotateAnchor(child.getBarycenterX(), child.getBarycenterY());
    }
}
