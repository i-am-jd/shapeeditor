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
import java.awt.geom.AffineTransform;
import java.awt.Image;
import java.awt.Toolkit;

public class DrawPanel extends JPanel
        implements MouseListener, MouseMotionListener {

    final private InfoBar infoBar;
    final private TreePanel treeZone;

    //Shape en cours de construction
    private SceneShape shape = null;
    private String currentShapeType;
    private int nbSides;

    //Polygone irrégulier en cours de construction
    private ArrayList<int[]> polygon;

    //Sélection courante
    private Vector<SceneGraph> selection;

    //Modifié au fur et à mesure des interactions avec l'utilisateur
    private Point mouseDown,  mouseHere,  mouseLastDrag;

    //Noeud du graphe de scene en cours de modification
    private SceneGraph node;

    //Forme à déplacer
    private SceneGraph sceneGraphToDrag = null;

    //État courant (sélection ou création d'une forme
    private UserMode mode = UserMode.Drawing;

    public DrawPanel(InfoBar infoBar, TreePanel treeZone) {
        super();

        //this.setMaximumSize(new Dimension(JWIDTH, JHEIGHT));
        //this.setAutoscrolls(true);
        this.nbSides = 0;
        this.polygon = new ArrayList();
        this.selection = new Vector();

        this.infoBar = infoBar;
        this.treeZone = treeZone;
        this.currentShapeType = "Rectangle";
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        //Window.origin[0] = this.getWidth() / 2;
        //Window.origin[1] = this.getHeight() / 2;

        addMouseListener(this);
        addMouseMotionListener(this);
        //this.setLayout(new BorderLayout());
        //this.add(new JScrollPane()); //, BorderLayout.CENTER);
    }

    public void setMode(UserMode mode)
    {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.mode = mode;
    }

    public void switchDrawingMode()
    {
        this.mode = UserMode.Drawing;
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    public void switchSelectionMode()
    {
        /*if(mode == UserMode.Selecting) {
            this.mode = UserMode.Drawing;
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        } else {*/
            this.mode = UserMode.Selecting;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        /*}*/
    }

    public void switchRotationMode()
    {
        this.mode = UserMode.Rotating;
        setCursor(new Cursor(Cursor.MOVE_CURSOR));
        //Toolkit toolkit = Toolkit.getDefaultToolkit();
        //Image image = toolkit.getImage("../images/cursor-rotate.png");
        //Point hotSpot = new Point(8, 8);
        //Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "Rotate");
        //setCursor(cursor);
    }

    public void switchScaleMode()
    {
        this.mode = UserMode.Scaling;
        setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
    }
    public void switchShearMode()
    {
        this.mode = UserMode.Shearing;
        setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
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
                 new float[] {2.0f, 6.0f},
                 0.0f));
        g2d.setColor(Color.white);
        g2d.setXORMode(Color.black);
        for(Enumeration<SceneGraph> en = selection.elements(); en.hasMoreElements();) {
            SceneGraph sg = en.nextElement();
            Rectangle2D r = sg.getBounds2D();
            g2d.draw(r);

            //Affichage d'une croix à la position du barycentre
            int middleX = (int) sg.getBarycenterX();
            int middleY = (int) sg.getBarycenterY();
            g2d.drawLine(middleX, (int) r.getMinY(), middleX, (int) r.getMaxY());
            g2d.drawLine((int) r.getMinX(), middleY, (int) r.getMaxX(), middleY);
        }

        //Enfin, on rafraichit la vue du JTree
        treeZone.repaintJTree();
    }

    public void calculateOrigin() {
        Window.origin[0] = this.getWidth() / 2;
        Window.origin[1] = this.getHeight() / 2;
     
        repaint();
    }

    public void setNbSides(int nb) {
        this.nbSides = nb;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(this.mode == UserMode.Selecting) {

            SceneGraph s = getSceneGraphAt(e.getPoint());
            if(s != null) {
                System.out.println(s.toString() + "selected");

                //Mettre dans la sélection
                if(!e.isControlDown()) {
                    selection.clear();
                }
                selection.add(s);

                repaint();
            } else {
                selection.clear();
            }
            return;
        } 

        if (!currentShapeType.equals("Irregular Polygon"))
            return;
        if (e.getButton() == MouseEvent.BUTTON3) {
            // Si on fait un clic droit, on annule la création du polygone
            polygon.clear();
            repaint();
            return;
        }

        Point2D p = e.getPoint();
        int x = (int) p.getX();
        int y = (int) p.getY();
        View view = Window.sceneGraph.getView();

        if (!polygon.isEmpty()) {
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

    public SceneGraph getSceneGraphAt(Point2D p)
    {
        SceneGraph foundGraph = null;
        int i = Window.sceneGraph.getChildCount();
        //Utilisation de l'opérateur flèche
        while(i --> 0) {
            SceneGraph g = (SceneGraph) Window.sceneGraph.getChildAt(i);
            if(g.contains(p)) {
                foundGraph = g;
                break;
            }
        }
        return foundGraph;
    }

    public void groupCurrentSelection()
    {
        if(!selection.isEmpty()) {
            
            Group gr = new Group();
            for(Enumeration<SceneGraph> en = selection.elements(); en.hasMoreElements();) {
                SceneGraph g = en.nextElement();
                Window.sceneGraph.remove(g);
                gr.add(g);
            }

            Window.sceneGraph.add(gr);

            selection.clear();
            selection.add(gr);

            repaint();
        }
    }

    public void intersectCurrentSelection()
    {
        if(selection.size() == 2) {
            View view = Window.sceneGraph.getView();
            Intersection inters = new Intersection(new View(view), selection.get(0), selection.get(1));
            Window.sceneGraph.add(inters);

            selection.clear();
            selection.add(inters);
        }

        repaint();
    }

    public void substractCurrentSelection()
    {
        if(selection.size() == 2) {
            View view = Window.sceneGraph.getView();
            Substraction subst = new Substraction(selection.get(0), selection.get(1));
            Window.sceneGraph.add(subst);

            selection.clear();
            selection.add(subst);
        }

        repaint();
    }
    
    public void copyCurrentSelection()
    { 
        if(!selection.isEmpty()) {
            for(Enumeration<SceneGraph> en = selection.elements(); en.hasMoreElements();) {
               Window.sceneGraph.add(en.nextElement().clone());
            }
            selection.removeAllElements();
            repaint();
        }
    }

    public void deleteCurrentSelection()
    {
        if(!selection.isEmpty()) {
            for(Enumeration<SceneGraph> en = selection.elements(); en.hasMoreElements();) {
                Window.sceneGraph.remove(en.nextElement());
            }
            selection.removeAllElements();
            repaint();
        }
    }

    public void degroupCurrentSelection()
    {
        if(selection.size() == 1) {
            SceneGraph sg = selection.get(0);
            if(sg instanceof Group) {
                selection.clear();
                for(Enumeration<SceneGraph> en = sg.children(); en.hasMoreElements();) {
                    selection.add(en.nextElement());
                }
                ((Group) sg).ungroup();
                Window.sceneGraph.applyTransform(new AffineTransform());
                repaint();
                this.switchSelectionMode();
            }
        }
    }

    public void createShape(double x, double y, double w, double h)
    {
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
            shape = new Ellipse(new View(view), x, y, Math.min(w,h), Math.min(w,h));
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

         if (e.getButton() == MouseEvent.BUTTON1 && this.mode == UserMode.Selecting) {
            sceneGraphToDrag = getSceneGraphAt(mouseDown);
         }
         if (this.mode == UserMode.Rotating) {
            if (!selection.isEmpty()) {
                SceneGraph child = selection.get(0);
                SceneGraph parent = (SceneGraph)child.getParent();
                Rotation r;
                if ( parent instanceof Rotation ) {
                    //Si le noeud parent est deja une rotation on la modifie directement
                    r = (Rotation)parent;
                    r.resetAnchor();
                } else {
                    //sinon on ajoute un nouveau noeud dans le graphe
                    r = new Rotation(selection.get(0), 0);
                    selection.remove(child);
                    selection.add(r);
                    Window.sceneGraph.add(r);
                }
                //Ajouter r au dessus de la feuille de selection(0)
                node = r;
            }
        } else if (this.mode == UserMode.Scaling) {
            System.out.println("Beginning scaling");
            if (!selection.isEmpty()) {
                SceneGraph child = selection.get(0);
                SceneGraph parent = (SceneGraph)child.getParent();
                Scale s;
                if ( parent instanceof Scale ) {
                    //Si le noeud parent est deja une rotation on la modifie directement
                    s = (Scale)parent;
                } else {
                    //sinon on ajoute un nouveau noeud dans le graphe
                    s = new Scale(1, 1);
                    s.add(child);
                    selection.remove(child);
                    selection.add(s);
                    Window.sceneGraph.add(s);
                }
                //Ajouter r au dessus de la feuille de selection(0)
                node = s;
            }
        } else if (this.mode == UserMode.Shearing) {
            if (!selection.isEmpty()) {
                SceneGraph parent = (SceneGraph)selection.get(0).getParent();
                Shear s;
                if ( parent instanceof Shear ) {
                    //Si le noeud parent est deja une rotation on la modifie directement
                    s = (Shear)parent;
                } else {
                    //sinon on ajoute un nouveau noeud dans le graphe
                    s = new Shear(1, 1);
                    s.add(selection.get(0));
                    Window.sceneGraph.add(s);
                }
                //Ajouter r au dessus de la feuille de selection(0)
                node = s;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        sceneGraphToDrag = null;
        node = null;
        if (shape != null) {
            Window.sceneGraph.add(shape);
            shape = null;
            repaint();
        }
        infoBar.printMessage("Click to initiate a shape.");
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

        if(sceneGraphToDrag != null) {
            //L'utilisateur avait pressé le curseur sur un élément du graphe
            //Celui-ci remplace la sélection courante
            selection.clear();
            selection.add(sceneGraphToDrag);
            Window.sceneGraph.moveToFront(sceneGraphToDrag);
        }

        if (selection.size() == 1 && this.mode == UserMode.Selecting) {
            //Déplacement de la shape sélectionnée
            double dx = mouseHere.getX() - mouseLastDrag.getX();
            double dy = mouseHere.getY() - mouseLastDrag.getY();
            selection.get(0).translate(dx, dy);
            mouseLastDrag = mouseHere;
        } else if (this.mode == UserMode.Rotating) {
            SceneGraph son = (SceneGraph) node.getChildAt(0);
            ((Rotation) node).rotate(calculateAngle(son, mouseLastDrag, mouseHere));
            mouseLastDrag = mouseHere;
        } else if (this.mode == UserMode.Scaling) {
            System.out.println("Scaling");
            SceneGraph son = (SceneGraph) node.getChildAt(0);
            ((Scale) node).setFactors(calculateScaleFactor(son, mouseHere));
        } else if (this.mode == UserMode.Shearing) {
            // A FAIRE
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
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        infoBar.printCoordinates(e.getX(), e.getY());
        mouseHere = e.getPoint();
        repaint();
    }

    public void setCurrentShapeType(String s) {
        this.currentShapeType = s;
    }

    public double calculateAngle(SceneGraph s, Point last, Point here)
    {
        double xS = s.getBarycenterX();
        double yS = s.getBarycenterY();

        double angleLast = Math.atan2(last.getX()-xS, last.getY()-yS);
        double angleHere = Math.atan2(here.getX()-xS, here.getY()-yS);

        return angleLast - angleHere;
    }

    public double[] calculateScaleFactor(SceneGraph s, Point p)
    {
        double width  = s.getBounds2D().getWidth();
        double height = s.getBounds2D().getHeight();

        double factorX = (p.getX() - s.getBarycenterX())/(width / 2);
        double factorY = (p.getY() - s.getBarycenterY())/(height / 2);
        System.out.println(factorX);
        System.out.println(factorY);
        return new double[]{Math.abs(factorX), Math.abs(factorY)};
    }

    public Vector<SceneGraph> getSelection()
    {
        return selection;
    }
}
