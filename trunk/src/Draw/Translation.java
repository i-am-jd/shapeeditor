package Draw;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Translation extends UnaryOperation {

    double dx = 0;
    double dy = 0;
    
    /**
     *
     * @param child
     */
    public Translation(SceneGraph child) {
        super("Translation", TransFlag.Translate, child);
    }
}