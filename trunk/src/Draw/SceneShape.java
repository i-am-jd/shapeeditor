package Draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;


import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Stack;

/**
 * Implante une feuille du graphe de scène.
 * Contient en deux formes géométriques.
 * <p>
 *
 * <tt>baseShape</tt> est la forme géométrique de base telle qu'elle a été
 * dessinée par l'utilisateur, sans aucune des transformations géométriques
 * subséquentes.
 *
 * <tt>shape</tt> est la forme géométrique résultant des transformations appliquées
 * à la forme de base; c'est celle qui sera affichée à l'écran.
 * Elle est mis à jour au fur et à mesure que l'utilisateur modifie le graphe de scène.
 * <p>
 *
 * Les transformations à appliquer sont celles contenues dans la chaîne de noeuds
 * menant de la racine du graphe de scène à cette feuille.
 * 
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public abstract class SceneShape extends SceneGraph {

    /**
     * Forme géométrique initiale.
     */
    protected Shape baseShape;

    /**
     * Forme géométrique résultant de l'application successive des transformations
     * géométriques à la forme de base.
     */
    protected Shape shape;

    /**
     * Constructeur de la classe
     * @param view  vue (paramètres de dessin)
     * @param name  nom de la forme géométrique à afficher (rectangle, cercle...)
     * Le nom spécifié apparait dans le JTree contenant la structure du graphe de scène.
     */
    public SceneShape(View view, String name)
    {
        super(view, name);
        this.view = view;
    }

    /**
     * Largeur de la forme géométrique à afficher
     * @return largeur
     */
    public double getWidth()
    {
        return shape.getBounds2D().getWidth() / 2;
    }

    /**
     * Largeur de la forme géométrique à afficher
     * @return largeur
     */
    public double getHeight()
    {
        return shape.getBounds2D().getHeight() / 2;
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public boolean contains(Point2D p)
    {
        return shape.contains(p);
    }

    /**
     *
     * @return
     */
    @Override
    public Rectangle2D getBounds2D()
    {
        return shape.getBounds2D();
    }

    /**
     * Applique successivement les transformations contenues dans une pile
     * à <tt>baseShape</tt>, retourne le résultat dans <tt>shape</tt>.
     * @param ops
     */
    @Override
    public void applyUnaryOperations(Stack<UnaryOperation> ops)
    {
        shape = baseShape;
        for(Enumeration<UnaryOperation> en = ops.elements(); en.hasMoreElements();) {
            ArrayList<AffineTransform> afl = en.nextElement().toAffineTransforms(shape);
            for(AffineTransform af : afl) {
                shape = af.createTransformedShape(shape);
            }
        }
    }

    /**
     * Dessine la forme géométrique en prenant en compte la vue.
     * @param g2d objet graphique
     */
    @Override
    public void draw(Graphics2D g2d)
    {
        if (view.getFillPattern() == null) {
            g2d.setPaint(view.getFillColor());
        } else {
            g2d.setPaint(view.getFillPattern());
        }
        g2d.fill(shape);

        g2d.setStroke(new BasicStroke(view.getLineWidth()));
        g2d.setColor(view.getLineColor());
        g2d.draw(shape);
    }

    /**
     * Calcule une approximation du barycentre de la figure
     * @return coordonnée horizontale du barycentre
     */
    @Override
    public double getBarycenterX()
    {
        return shape.getBounds2D().getCenterX();
    }

    /**
     * Calcule une approximation du barycentre de la figure
     * @return coordonnée verticale du barycentre
     */
    @Override
    public double getBarycenterY()
    {
        return shape.getBounds2D().getCenterY();
    }

    /**
     * Retourne la forme géométrique à afficher
     * @return
     */
    @Override
    public Shape getShape()
    {
        return shape;
    }
}
