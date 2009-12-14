package Draw;


public class Circle extends Ellipse {
	
	public Circle (int rad)
	{
		super(rad, rad);
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
