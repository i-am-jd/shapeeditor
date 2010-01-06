package Draw;

public class Translation extends UnaryOperation {

    double dx = 0;
    double dy = 0;
    
    public Translation(SceneGraph child) {
        super("Translation", TransFlag.Translate, child);
    }
}