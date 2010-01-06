package Draw;


public class Square extends Rectangle {

    public Square(View v, double x, double y, double w) {
        super(v, x, y, w, w);

        setUserObject("Square");
    }
    
}
