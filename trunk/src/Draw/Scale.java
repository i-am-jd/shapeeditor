package Draw;

public class Scale extends UnaryOperation
{

    public Scale(SceneGraph child)
    {
        super("Scale", TransFlag.Scale, child);
    }
}
