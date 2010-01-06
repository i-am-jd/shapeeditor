package Draw;

public class Rotation extends UnaryOperation
{

    public Rotation(SceneGraph child, double angle) {
        super("Rotation", TransFlag.Rotate, child);
        this.setRotateAnchor(child.getBarycenterX(), child.getBarycenterY());
    }

}
