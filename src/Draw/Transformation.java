package Draw;

/**
 * Noeud du graphe de scène représentant une transformation géométrique.
 * Une transformation est unaire ({link UnaryOperation}) ou bien binaire
 * ({link BinaryOperation}).
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
