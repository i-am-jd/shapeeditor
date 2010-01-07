package IHM;

import Draw.*;


import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Point;
import java.awt.Color;

/**
 * Class instantiating the draw panel
 * @author Boris Dadachev & Jean-Denis Koeck
 */
public class DrawPanel extends JPanel
        implements MouseListener, MouseMotionListener {

    /** Referencing the information bar */
    final private InfoBar infoBar;
    /** Referencing the JTree panel */
    final private TreePanel treeZone;
    /** Shape under building */
    private SceneShape shape = null;
    /** Type of the shape to create */
    private String currentShapeType;
    /** Number of sides of the polygon to create */
    private int nbSides;
    /** Vertices of the irregular polygon under construction */
    private ArrayList<int[]> polygon;
    /** Current selection */
    private Vector<SceneGraph> selection;
    /** Points used */
    private Point mouseDown, mouseHere, mouseLastDrag;
    /** Scene graph node to modify */
    private SceneGraph node;
    /** Shape to drag */
    private SceneGraph sceneGraphToDrag = null;
    /** Current mode */
    private UserMode mode = UserMode.Drawing;
    /** Context menu to display after a right click */
    private SelectionContextMenu popupMenu;

    /**
     * Returns a draw panel, associated with an information bar and
     * a JTree panel
     * @param infoBar   Reference of the information bar
     * @param treeZone  Reference of the tree zone
     */
    public DrawPanel(InfoBar infoBar, TreePanel treeZone) {
        super();

        this.nbSides = 0;
        this.polygon = new ArrayList();
        this.selection = new Vector();

        this.infoBar = infoBar;
        this.treeZone = treeZone;
        this.currentShapeType = "Rectangle";
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        this.popupMenu = new SelectionContextMenu(this);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * Sets the current mode
     * @param mode  The mode to set
     */
    public void setMode(UserMode mode) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.mode = mode;
    }

    /**
     * Switches the current mode to the drawing mode
     */
    public void switchDrawingMode() {
        this.mode = UserMode.Drawing;
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        selection.clear();
        infoBar.printMessage("Click to initiate a shape.");
    }

    /**
     * Switches the current mode to the selecting mode
     */
    public void switchSelectionMode() {
        this.mode = UserMode.Selecting;
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        infoBar.printMessage("Click on a shape to select it.");
    }

    /**
     * Switches the current mode to the rotating mode
     */
    public void switchRotationMode() {
        this.mode = UserMode.Rotating;
        setCursor(new Cursor(Cursor.MOVE_CURSOR));
        if (selection.isEmpty()) {
            infoBar.printMessage("Please select a shape or a transformation first.");
        } else {
            infoBar.printMessage("Click to rotate the selection.");
        }
    }

    /**
     * Switches the current mode to the scaling mode
     */
    public void switchScaleMode() {
        this.mode = UserMode.Scaling;
        System.out.println("Taille de la sélection" + (new Integer(selection.size())).toString());
        setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        if (selection.isEmpty()) {
            infoBar.printMessage("Please select a shape or a transformation first.");
        } else {
            infoBar.printMessage("Click to scale the selection.");
        }
    }

    /**
     * Switches the current mode to the shearing mode
     */
    public void switchShearMode() {
        this.mode = UserMode.Shearing;
        setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
        if (selection.isEmpty()) {
            infoBar.printMessage("Please select a shape or a transformation first.");
        } else {
            infoBar.printMessage("Click to shear the selection.");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // caract�ristiques graphiques : mise en place de l'antialiasing
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // taille de la zone de dessin
        Dimension d = getSize();
        // on commence par effacer le fond
        g2d.clearRect(0, 0, d.width, d.height);

        //Appelle récursivement les opérations de dessin sur les sous-arbres
        Window.sceneGraph.draw(g2d);

        if (shape != null) {
            shape.draw(g2d);
        }

        if (!polygon.isEmpty()) {
            View currentView = Window.sceneGraph.getView();
            g2d.setStroke(new BasicStroke(currentView.getLineWidth()));
            g2d.setColor(currentView.getLineColor());
            int size = polygon.size();
            if (size >= 2) {
                for (int i = 0; i < size - 1; i++) {
                    int[] init = polygon.get(i);
                    int[] end = polygon.get(i + 1);
                    g2d.drawLine(init[0], init[1], end[0], end[1]);
                }
            }
            g2d.drawLine(polygon.get(size - 1)[0], polygon.get(size - 1)[1],
                    (int) mouseHere.getX(), (int) mouseHere.getY());
        }

        //Rectangles de sélection
        g2d.setStroke(new BasicStroke(1.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL,
                1.0f,
                new float[]{2.0f, 6.0f},
                0.0f));
        g2d.setColor(Color.white);
        g2d.setXORMode(Color.black);
        for (Enumeration<SceneGraph> en = selection.elements(); en.hasMoreElements();) {
            SceneGraph sg = en.nextElement();
            Rectangle2D r = sg.getBounds2D();
            g2d.draw(r);

            //Affichage d'une croix à la position du barycentre
            int middleX = (int) sg.getBarycenterX();
            int middleY = (int) sg.getBarycenterY();
            g2d.drawLine(middleX, (int) r.getMinY(), middleX, (int) r.getMaxY());
            g2d.drawLine((int) r.getMinX(), middleY, (int) r.getMaxX(), middleY);
        }
    }

    /**
     *  Repaints the draw panel and the associated Jtree panel
     */
    public void repaintPanel() {
        repaint();
        //Enfin, on rafraichit la vue du JTree
        treeZone.repaintJTree();
    }

    /**
     *  Calculates the origin of the draw panel
     */
    public void calculateOrigin() {
        Window.origin[0] = this.getWidth() / 2;
        Window.origin[1] = this.getHeight() / 2;

        repaintPanel();
    }

    /**
     * Sets the number of sides of the polygon to create
     * @param nb   The number of sides
     */
    public void setNbSides(int nb) {
        this.nbSides = nb;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.mode == UserMode.Selecting) {

            SceneGraph s = getSceneGraphAt(e.getPoint());
            if (s != null) {
                System.out.println(s.toString() + "selected");

                //Mettre dans la sélection
                if (!e.isControlDown()) {
                    selection.clear();
                }
                selection.add(s);

                repaintPanel();
            } else {
                selection.clear();
            }
            return;
        }

        if (!currentShapeType.equals("Irregular Polygon")) {
            return;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            // Si on fait un clic droit, on annule la création du polygone
            polygon.clear();
            repaintPanel();
            return;
        }

        Point2D p = e.getPoint();
        int x = (int) p.getX();
        int y = (int) p.getY();
        View view = Window.sceneGraph.getView();

        if (!polygon.isEmpty() && mode == UserMode.Drawing) {
            int[] head = polygon.get(0);
            //Si le curseur est suffisamment proche du premier point du polygone, la construction
            //de ce dernier est terminée
            if (Math.abs(head[0] - x) < 8 && Math.abs(head[1] - y) < 8) {
                int[][] tab = new int[2][polygon.size()];
                for (int i = 0; i < polygon.size(); i++) {
                    tab[0][i] = polygon.get(i)[0];
                    tab[1][i] = polygon.get(i)[1];
                }
                //Insertion du nouveau polygône
                shape = new IrregularPolygon(new View(view), tab);
                Window.sceneGraph.add(shape);

                polygon.clear();
                shape = null;
            } else {
                //Sinon on ajoute un nouveau sommet au polygone
                polygon.add(new int[]{x, y});
            }
        } else {
            polygon.add(new int[]{x, y});
        }
    }

    /**
     * Returns the shape located at the p coordinates
     * @param p The point to search at
     * @return
     */
    public SceneGraph getSceneGraphAt(Point2D p) {
        SceneGraph foundGraph = null;
        int i = Window.sceneGraph.getChildCount();
        //Utilisation de l'opérateur flèche
        while (i-- > 0) {
            SceneGraph g = (SceneGraph) Window.sceneGraph.getChildAt(i);
            if (g.contains(p)) {
                foundGraph = g;
                System.out.println("Graphe trouvé: " + g.toString());
                break;
            }
        }
        return foundGraph;
    }

    /**
     * Groups the current selection
     */
    public void groupCurrentSelection() {
        if (!selection.isEmpty()) {

            Group gr = new Group(new View(Window.sceneGraph.getView()));

            int i = selection.size();
            while (i-- > 0) {
                SceneGraph sg = selection.get(i);
                gr.add(sg);
            }

            Window.sceneGraph.add(gr);

            selection.clear();
            selection.add(gr);

            repaintPanel();
        }
    }

    /**
     * Intersects the current selection
     */
    public void intersectCurrentSelection() {
        if (selection.size() == 2) {
            Intersection inters = new Intersection(selection.get(0), selection.get(1));
            Window.sceneGraph.add(inters);

            selection.clear();
            selection.add(inters);
        }

        repaintPanel();
    }

    /**
     * Substracts the current selection
     */
    public void substractCurrentSelection() {
        if (selection.size() == 2) {
            Substraction subst = new Substraction(selection.get(0), selection.get(1));
            Window.sceneGraph.add(subst);

            selection.clear();
            selection.add(subst);
        }

        repaintPanel();
    }

    /**
     * Excludes the current selection
     */
    public void exclusionCurrentSelection() {
        if (selection.size() == 2) {
            Exclusion excl = new Exclusion(selection.get(0), selection.get(1));
            Window.sceneGraph.add(excl);

            selection.clear();
            selection.add(excl);
        }

        repaintPanel();
    }

    /**
     * Unifies the current selection
     */
    public void unionCurrentSelection() {
        if (selection.size() == 2) {
            Union union = new Union(selection.get(0), selection.get(1));
            Window.sceneGraph.add(union);

            selection.clear();
            selection.add(union);
        }

        repaintPanel();
    }

    /**
     * Includes the current selection
     */
    public void inclusionCurrentSelection() {
        if (selection.size() == 2) {
            Inclusion incl = new Inclusion(selection.get(0), selection.get(1));
            Window.sceneGraph.add(incl);

            selection.clear();
            selection.add(incl);
        }

        repaintPanel();
    }

    /**
     * Interpolates the current selection
     */
    public void interpolateCurrentSelection() {
        if (selection.size() == 2) {
            SceneGraph sg1 = selection.get(0);
            SceneGraph sg2 = selection.get(1);

            if (sg1 instanceof Interpolatable && sg2 instanceof Interpolatable) {
                if (((Interpolatable) sg1).getPointsNb() == ((Interpolatable) sg2).getPointsNb()) {
                    Interpolation interp = new Interpolation(sg1, sg2);
                    Window.sceneGraph.add(interp);

                    selection.clear();
                    selection.add(interp);
                }
            }
        }
        repaintPanel();
    }

    /**
     * Copies the current selection
     */
    public void copyCurrentSelection() {
        if (!selection.isEmpty()) {
            for (Enumeration<SceneGraph> en = selection.elements(); en.hasMoreElements();) {
                Window.sceneGraph.add(en.nextElement().clone());
            }
            selection.removeAllElements();
            repaintPanel();
        }
    }

    /**
     * Deletes the current selection
     */
    public void deleteCurrentSelection() {
        if (!selection.isEmpty()) {
            for (Enumeration<SceneGraph> en = selection.elements(); en.hasMoreElements();) {
                Window.sceneGraph.remove(en.nextElement());
            }
            selection.removeAllElements();
            repaintPanel();
        }
    }

    /**
     * Degroup the current selection
     */
    public void degroupCurrentSelection() {
        if (selection.size() == 1) {
            SceneGraph sg = selection.get(0);
            if (sg instanceof Group) {
                selection.clear();
                for (Enumeration<SceneGraph> en = sg.children(); en.hasMoreElements();) {
                    selection.add(en.nextElement());
                }
                ((Group) sg).ungroup();
                Window.sceneGraph.update();
                repaintPanel();
                this.switchSelectionMode();
            }
        }
    }

    /**
     * Creates a shape (the type of the shape is "currentShapeType")
     * @param x The x coordinate
     * @param y The y coordinate
     * @param w The width of the shape
     * @param h The height of the shape
     */
    public void createShape(double x, double y, double w, double h) {
        System.out.println("createShape");
        View view = Window.sceneGraph.getView();

        //Verification de la correction du nombre de cotes voulu (si necessaire)
        if (currentShapeType.equals("Regular Polygon") || currentShapeType.equals("Other Star")) {
            if (this.nbSides < 3) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of sides in the appropriate text field.",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        if (currentShapeType.equals("Irregular Polygon")) {
            return;
            /*if (polygon.isEmpty() == false) {
            int[] head = polygon.get(0);
            if (Math.abs(head[0] - x) < 8 && Math.abs(head[1] - y) < 8) {
            int[][] tab = new int[2][polygon.size()];
            for (int i = 0; i < polygon.size(); i++) {
            tab[0][i] = polygon.get(i)[0];
            tab[1][i] = polygon.get(i)[1];
            }
            polygon.clear();
            shape = new IrregularPolygon(new View(view), tab);
            Window.sceneGraph.add(shape);

            shape = null;
            //verifier que l'on finit le polygone avant de faire autre chose
            } else {
            polygon.add(new int[]{(int) x, (int) y});
            }
            } else {
            polygon.add(new int[]{(int) x, (int) y});
            }*/
        }

        if (currentShapeType.equals("Square")) {
            shape = new Square(new View(view), x, y, Math.min(w, h));
        } else if (currentShapeType.equals("Rectangle")) {
            shape = new Rectangle(new View(view), x, y, w, h);
        } else if (currentShapeType.equals("Equilateral Triangle")) {
            shape = new RegularPolygon(new View(view), x, y, Math.min(w, h), 3);
        } else if (currentShapeType.equals("Isosceles Triangle")) {
            shape = new IsoscelesTriangle(new View(view), x, y, w, h);
        } else if (currentShapeType.equals("Right-angled Triangle")) {
            shape = new RightAngledTriangle(new View(view), x, y, w, h);
        } else if (currentShapeType.equals("Five-pointed Star")) {
            shape = new Star(new View(view), x, y, Math.min(w, h), 5);
        } else if (currentShapeType.equals("Six-pointed Star")) {
            shape = new Star(new View(view), x, y, Math.min(w, h), 6);
        } else if (currentShapeType.equals("Other Star")) {
            shape = new Star(new View(view), x, y, Math.min(w, h), nbSides);
        } else if (currentShapeType.equals("Circle")) {
            shape = new Circle(new View(view), x, y, Math.min(w, h));
        } else if (currentShapeType.equals("Ellipse")) {
            shape = new Ellipse(new View(view), x, y, w, h);
        } else if (currentShapeType.equals("Regular Polygon")) {
            shape = new RegularPolygon(new View(view), x, y, Math.min(w, h), nbSides);
        }

        infoBar.printMessage("Release to draw the shape");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = e.getPoint();
        mouseLastDrag = e.getPoint();

        displayPopupMenu(e);
        if (e.getButton() == MouseEvent.BUTTON1 && this.mode == UserMode.Selecting) {
            sceneGraphToDrag = getSceneGraphAt(mouseDown);
        }
        if (this.mode == UserMode.Rotating) {
            if (!selection.isEmpty()) {
                SceneGraph sg = selection.get(0);
                //SceneGraph parent = (SceneGraph)child.getParent();
                Rotation r;
                if (sg instanceof Rotation) {
                    //Si le noeud parent est deja une rotation on la modifie directement
                    r = (Rotation) sg;
                    r.beginEdit();
                } else {
                    //sinon on ajoute un nouveau noeud dans le graphe
                    r = new Rotation(selection.get(0));
                    selection.remove(sg);
                    selection.add(r);
                    Window.sceneGraph.add(r);
                }
                //Ajouter r au dessus de la feuille de selection(0)
                node = r;
            }
        } else if (this.mode == UserMode.Scaling) {
            System.out.println("Beginning scaling");
            if (!selection.isEmpty()) {
                SceneGraph sg = selection.get(0);
                Scale s;
                if (sg instanceof Scale) {
                    s = (Scale) sg;
                    s.beginEdit();
                } else {
                    //sinon on ajoute un nouveau noeud dans le graphe
                    s = new Scale(sg);
                    selection.remove(sg);
                    selection.add(s);
                    Window.sceneGraph.add(s);
                }
                //Ajouter r au dessus de la feuille de selection(0)
                node = s;
            }
        } else if (this.mode == UserMode.Shearing) {
            if (!selection.isEmpty()) {
                SceneGraph sg = selection.get(0);
                Shear s;
                if (sg instanceof Shear) {
                    //Si le noeud parent est deja une rotation on la modifie directement
                    s = (Shear) sg;
                    s.beginEdit();
                } else {
                    //sinon on ajoute un nouveau noeud dans le graphe
                    s = new Shear(sg);
                    selection.remove(sg);
                    selection.add(s);
                    Window.sceneGraph.add(s);
                }
                //Ajouter r au dessus de la feuille de selection(0)
                node = s;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        displayPopupMenu(e);
        sceneGraphToDrag = null;
        node = null;
        if (shape != null) {
            Window.sceneGraph.add(shape);
            shape = null;
            repaintPanel();
        }
        if (this.mode == UserMode.Drawing) {
            infoBar.printMessage("Click to initiate a shape.");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        infoBar.printDefaultCoordinates();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        mouseHere = e.getPoint();

        if (sceneGraphToDrag != null && mode == UserMode.Selecting) {
            //L'utilisateur avait pressé le curseur sur un élément du graphe
            //Celui-ci remplace la sélection courante
            if (selection.isEmpty()) {
                selection.clear();
                selection.add(sceneGraphToDrag);
                sceneGraphToDrag.moveToFront();
                Window.sceneGraph.update();
            }
        }

        if (sceneGraphToDrag == null && mode == UserMode.Selecting) {
            selection.clear();
        }

        if (selection.size() == 1 && this.mode == UserMode.Selecting) {
            //Déplacement de la shape sélectionnée
            double dx = mouseHere.getX() - mouseLastDrag.getX();
            double dy = mouseHere.getY() - mouseLastDrag.getY();

            SceneGraph sg = selection.get(0);
            if (sg instanceof Translation) {
                ((Translation) sg).translate(dx, dy);
                Window.sceneGraph.update();
            } else {
                //sinon on ajoute un nouveau noeud dans le graphe
                Translation t = new Translation(sg);
                selection.remove(sg);
                selection.add(t);
                System.out.println("Translate");
                Window.sceneGraph.add(t);
                Window.sceneGraph.update();
            }

            mouseLastDrag = mouseHere;
        } else if (this.mode == UserMode.Rotating) {
            SceneGraph son = (SceneGraph) node.getChildAt(0);
            ((Rotation) node).rotateFromTo(mouseLastDrag, mouseHere);
            mouseLastDrag = mouseHere;
        } else if (this.mode == UserMode.Scaling) {
            System.out.println("Scaling");
            ((Scale) node).scaleTo(mouseHere);
            //calculateScaleFactor(son, mouseHere);
            //((Scale) node).setScaleFactors(1,1);
            Window.sceneGraph.update();
        } else if (this.mode == UserMode.Shearing) {
            System.out.println("Scaling");
            ((Shear) node).shearTo(mouseHere);
            //calculateScaleFactor(son, mouseHere);
            //((Scale) node).setScaleFactors(1,1);
            Window.sceneGraph.update();
        } else if (this.mode == UserMode.Drawing) {
            double downX = mouseDown.getX();
            double downY = mouseDown.getY();
            double hereX = mouseHere.getX();
            double hereY = mouseHere.getY();

            double l = Math.min(downX, hereX);
            double t = Math.min(downY, hereY);
            double w = Math.abs(downX - hereX);
            double h = Math.abs(downY - hereY);
            //rect = new Rectangle2D.Double(l, t, w, h);

            double dia = Math.min(w, h);
            if (dia >= 5) {
                l = downX < hereX ? downX : downX - w;
                t = downY < hereY ? downY : downY - h;

                //Création d'une forme géométrique
                createShape(l, t, w, h);
            } else {
                shape = null;
            }
        }

        // affichage des coordonn�es
        infoBar.printCoordinates(mouseHere);
        /*
        // deplacement de l'extrémité de la droite
        if (shape.getGeometry() instanceof Square) {
        Square s = (Square) shape.getGeometry();
        s.setSide(Math.abs(e.getY() - Window.origin[1]));
        } else if (shape.getGeometry() instanceof Rectangle) {
        Rectangle r = (Rectangle) shape.getGeometry();
        r.setWidth(Math.abs(e.getX() - Window.origin[0]));
        r.setHeight(Math.abs(e.getY() - Window.origin[1]));
        } else if (shape.getGeometry() instanceof Triangle) {
        Triangle t = (Triangle) shape.getGeometry();
        t.setWidth(Math.abs(e.getX() - Window.origin[0]));
        t.setHeight(Math.abs(e.getY() - Window.origin[1]));
        } else if (shape.getGeometry() instanceof Circle) {
        Circle r = (Circle) shape.getGeometry();
        r.setRadius((int) Math.sqrt(Math.pow(e.getX() - Window.origin[0], 2) + Math.pow(e.getY() - Window.origin[1], 2)));
        } else if (shape.getGeometry() instanceof Ellipse) {
        Ellipse el = (Ellipse) shape.getGeometry();
        //el.setSemiMajorAxis(Math.abs(e.getX() - Window.origin[0]));
        //el.setSemiMinorAxis(Math.abs(e.getY() - Window.origin[1]));
        }
         */

        // dessin du graphe de scene et de la forme en construction
        repaintPanel();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        infoBar.printCoordinates(e.getX(), e.getY());
        mouseHere = e.getPoint();
        repaintPanel();
    }

    /**
     * Sets the current shape type
     * @param s The name of the type to set
     */
    public void setCurrentShapeType(String s) {
        this.currentShapeType = s;
    }

    /**
     * Displays a context menu when a right click is done
     * @param e The mouse event associated to the right click
     */
    public void displayPopupMenu(MouseEvent e) {
        if (e.isPopupTrigger() && this.mode == UserMode.Selecting) {
            if (selection.size() > 0) {
                popupMenu.show(this, e.getX(), e.getY());
            }
        }
    }

    /**
     * Returns the current selection
     * @return 
     */
    public Vector<SceneGraph> getSelection() {
        return selection;
    }
}
