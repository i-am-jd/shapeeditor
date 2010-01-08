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
 * Implantation générique d'une feuille du graphe de scène.
 * Elle contient en fait deux formes géométriques de type 
 * <tt>Shape</tt>.
 * <p>
 *
 * <tt>baseShape</tt> est la forme géométrique de base telle qu'elle a été
 * dessinée par l'utilisateur, sans aucune des transformations unaires
 * subséquentes.
 * <p>
 *
 * <tt>shape</tt> est la forme géométrique résultant des transformations unaires  appliquées
 * à <tt>baseShape</tt>. Elle est affichée à l'écran et mise à jour au fur et
 * à mesure que l'utilisateur modifie le graphe de scène.
 * <p>
 *
 * Note: Les transformations unaires appliquées à <tt>baseShape</tt> sont celles contenues
 * dans la chaîne de noeuds menant de la racine du graphe de scène à cette feuille.
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
     * unaires à la forme de base.
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

    @Override
    public boolean contains(Point2D p)
    {
        return shape.contains(p);
    }

    @Override
    public Rectangle2D getBounds2D()
    {
        return shape.getBounds2D();
    }

    /**
     * Applique à <tt>baseShape</tt> les transformations unaires contenues dans
     * une pile, retourne le résultat dans <tt>shape</tt>
     * @param ops
     */
    @Override
    public void applyUnaryOperations(Stack<UnaryOperation> ops)
    {
        shape = baseShape;
        int i = ops.size();
        while(i --> 0) {
            UnaryOperation uop = ops.get(i);
            ArrayList<AffineTransform> afl = uop.toAffineTransforms(shape);
            for(AffineTransform af : afl) {
                shape = af.createTransformedShape(shape);
            }
        }
    }

    /**
     * Dessine la forme géométrique en prenant en compte les paramètres de <tt>view</tt>
     * @param g2d objet graphique
     * @see Graphics2D
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
     * Calcule la coordonnée horizontale du barycentre de la figure
     * @return coordonnée horizontale du barycentre de la figure
     */
    @Override
    public double getBarycenterX()
    {
        return shape.getBounds2D().getCenterX();
    }

    /**
     * Calcule la coordonnée verticale du barycentre de la figure
     * @return coordonnée verticale du barycentre
     */
    @Override
    public double getBarycenterY()
    {
        return shape.getBounds2D().getCenterY();
    }

    /**
     * @return la forme géométrique à afficher
     */
    @Override
    public Shape getShape()
    {
        return shape;
    }
}
