package Draw;


public class Circle extends Ellipse {
	
	public Circle (int xC, int yC, int rad)
	{
		super(xC, yC, rad, rad);
	}

	public int getRadius()
	{
		return getSemiMajorAxis();
	}
        public void setRadius(int r)
        {
		setSemiMajorAxis(r);
                setSemiMinorAxis(r);
	}
}
