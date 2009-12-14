package Draw;


public class Scale extends UnaryOperation {
    private float scaleFactor;

    public Scale (float scaleFactor)
    {
        this.scaleFactor = scaleFactor;
    }

    public float getScaleFactor()
    {
        return this.scaleFactor;
    }

    public void setScaleFactor(float scaleFactor)
    {
        this.scaleFactor = scaleFactor;
    }
}
