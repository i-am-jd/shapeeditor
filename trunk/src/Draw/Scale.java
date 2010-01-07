package Draw;

/**
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class Scale extends UnaryOperation
{

    /**
     *
     * @param child
     */
    public Scale(SceneGraph child)
    {
        super("Scale", TransFlag.Scale, child);
    }
}
