package Draw;

/**
 * Transformation géométrique de un ou deux sous-graphes de scène.
 * Une transformation est unaire (@see UnaryOperation) ou bien binaire
 * (@see BinaryOperation).
 * <p>
 *
 * Cette classe abstraite sert à distinguer groupes simples (regroupant
 * plusieurs sous-graphes) et transformations géométriques.
 *
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public abstract class Transformation extends Group {

    /**
     * Constructeur d'une transformation
     * @param view paramètres de dessin
     * @param name nom de la transformation
     */
    public Transformation(View view, String name) {
        super(view, name);
    }
}
