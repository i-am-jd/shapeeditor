package Draw;


public class Square extends Rectangle {

	public Square (int width)
	{
		super(width, width);
	}
	public Square (int x, int y, int width)
	{
		super(x, y, width, width);
	}
        public void setSide (int s) {
            setWidth(s);
            setHeight(s);
        }
        public int getSide() {
            return getWidth();
        }

}
