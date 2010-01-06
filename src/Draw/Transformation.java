package Draw;



public abstract class Transformation extends Group {
    public Transformation(View v) {
        super(v, "Transformation");
    }

    public Transformation(View v, String s){
        super(v, s);
    }
    
}
