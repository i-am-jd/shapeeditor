package Draw;


public class Square extends Rectangle {

	public Square (int w)
	{
		super(w, w);
	}
        public void setSide (int s) {
            setWidth(s);
            setHeight(s);
        }
        public int getSide() {
            return getWidth();
        }

}
