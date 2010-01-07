package Draw;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Shear extends UnaryOperation {

    /**
     *
     * @param child Child of the shearing transformation
     */
    public Shear(SceneGraph child) {
        super("Shear", TransFlag.Shear, child);
    }
}
