package Draw;

public class Circle extends Ellipse {

    public Circle(View v, double x, double y, double r) {
        super(v, x, y, r, r);
    }

    public double getRadius() {
        return (int) getSemiMajorAxis();
    }
}
