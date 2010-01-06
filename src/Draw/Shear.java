package Draw;

public class Shear extends UnaryOperation {

    public Shear(SceneGraph child)
    {
        super("Shear", TransFlag.Shear, child);
    }

}
