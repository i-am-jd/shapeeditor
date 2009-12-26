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

public class DrawPanel extends JPanel
        implements MouseListener, MouseMotionListener {

    private int x;
    private int y;
    private Shape shape = null;
    private InfoBar infoBar;
    //private TreePanel treeZone;
    private String currentShapeType;
    private int nbSides;
    private ArrayList<int[]> polygon;
    private Vector<? extends SceneGraph> selection;

    public DrawPanel(InfoBar infoBar) {
        super();

        //this.setMaximumSize(new Dimension(JWIDTH, JHEIGHT));
        //this.setAutoscrolls(true);
        this.nbSides = 0;
        this.polygon = new ArrayList();
        this.selection = new Vector();

        this.infoBar = infoBar;
        /*this.treeZone = treeZone;*/
        this.currentShapeType = "Rectangle";
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        Window.origin[0] = this.getWidth() / 2;
        Window.origin[1] = this.getHeight() / 2;

        addMouseListener(this);
        addMouseMotionListener(this);
        //this.setLayout(new BorderLayout());
        //this.add(new JScrollPane()); //, BorderLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) {
        // caract�ristiques graphiques : mise en place de l'antialiasing
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // taille de la zone de dessin
        Dimension d = getSize();
        // on commence par effacer le fond
        g2d.clearRect(0, 0, d.width, d.height);

        for (Enumeration en = Window.sceneGraph.children(); en.hasMoreElements();) {
            ((Shape) en.nextElement()).drawShape(g2d);
        }

        if (shape != null) {
            shape.drawShape(g2d);
        }
        if (polygon.isEmpty() == false) {
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
            g2d.drawLine(polygon.get(size - 1)[0], polygon.get(size - 1)[1], this.x, this.y);
        }
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
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        View currentView = Window.sceneGraph.getView();
        /* Verification de la correction du nombre de cotes voulu (si necessaire) */
        if (currentShapeType.equals("Regular Polygon") || currentShapeType.equals("Other Star")) {
            if (this.nbSides < 3) {
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(null, "Please enter a valid number of sides in the appropriate text field.",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        if (currentShapeType.equals("Irregular Polygon")) {
            if (polygon.isEmpty() == false) {
                int[] head = polygon.get(0);
                if (Math.abs(head[0] - e.getX()) < 8 && Math.abs(head[1] - e.getY()) < 8) {
                    int[][] tab = new int[2][polygon.size()];
                    for (int i = 0; i < polygon.size(); i++) {
                        tab[0][i] = polygon.get(i)[0];
                        tab[1][i] = polygon.get(i)[1];
                    }
                    polygon.clear();
                    shape = new Shape(new View(currentView), new IrregularPolygon(tab));
                    shape.insertShape(Window.sceneGraph);
                    shape = null;
                    //verifier que l'on finit le polygone avant de faire autre chose
                } else {
                    polygon.add(new int[]{e.getX(), e.getY()});
                }
            } else {
                polygon.add(new int[]{e.getX(), e.getY()});
            }
        } else if (currentShapeType.equals("Square")) {
            shape = new Shape(new View(currentView),
                    new Square(Math.abs(e.getY() - Window.origin[1])));
        } else if (currentShapeType.equals("Rectangle")) {
            shape = new Shape(new View(currentView),
                    new Rectangle(Math.abs(e.getX() - Window.origin[0]), Math.abs(e.getY() - Window.origin[1])));
        } else if (currentShapeType.equals("Equilateral Triangle")) {
            shape = new Shape(new View(currentView),
                    new RegularPolygon((int) Math.sqrt(Math.pow(e.getX() - Window.origin[0], 2) + Math.pow(e.getY() - Window.origin[1], 2)), 3));
        /*} else if (currentShapeType.equals("Isosceles Triangle")) {
            shape = new Shape(new View(currentView),
                    new IsoscelesTriangle(Math.abs(e.getX() - Window.origin[0]), Math.abs(e.getY() - Window.origin[1])));*/
        /*} else if (currentShapeType.equals("Right-angled Triangle")) {
            shape = new Shape(new View(currentView),
                    new RightAngledTriangle(Math.abs(e.getX() - Window.origin[0]), Math.abs(e.getY() - Window.origin[1])));*/
        } else if (currentShapeType.equals("Five-pointed Star")) {
            shape = new Shape(new View(currentView),
                    new Star((int) Math.sqrt(Math.pow(e.getX() - Window.origin[0], 2) + Math.pow(e.getY() - Window.origin[1], 2)), 5));
        } else if (currentShapeType.equals("Six-pointed Star")) {
            shape = new Shape(new View(currentView),
                    new Star((int) Math.sqrt(Math.pow(e.getX() - Window.origin[0], 2) + Math.pow(e.getY() - Window.origin[1], 2)), 6));
        } else if (currentShapeType.equals("Other Star")) {
            shape = new Shape(new View(currentView),
                    new Star((int) Math.sqrt(Math.pow(e.getX() - Window.origin[0], 2) + Math.pow(e.getY() - Window.origin[1], 2)), nbSides));
        } else if (currentShapeType.equals("Circle")) {
            shape = new Shape(new View(currentView),
                    new Circle((int) Math.sqrt(Math.pow(e.getX() - Window.origin[0], 2) + Math.pow(e.getY() - Window.origin[1], 2))));
        } else if (currentShapeType.equals("Ellipse")) {
            shape = new Shape(new View(currentView),
                    new Ellipse(Math.abs(e.getX() - Window.origin[0]), Math.abs(e.getY() - Window.origin[1])));
        } else if (currentShapeType.equals("Regular Polygon")) {
            shape = new Shape(new View(currentView),
                    new RegularPolygon((int) Math.sqrt(Math.pow(e.getX() - Window.origin[0], 2) + Math.pow(e.getY() - Window.origin[1], 2)), nbSides));
        }
        infoBar.printMessage("Release to draw the shape");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (shape != null) {
            shape.insertShape(Window.sceneGraph);
            shape = null;
            repaint();
        } // stockage de la droite
        // liberation de la droite temporaire
        // destruction de sa r�f�rence sachant qu'elle est
        // sauvegardee dans la classe dessin

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
     
        // affichage des coordonn�es
        infoBar.printCoordinates(x, y);

        if (shape == null) {
            return;
        }
        // deplacement de l'extr�mit� de la droite
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
            el.setSemiMajorAxis(Math.abs(e.getX() - Window.origin[0]));
            el.setSemiMinorAxis(Math.abs(e.getY() - Window.origin[1]));
        }

        // dessin du graphe de scene et de la forme en construction
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        infoBar.printCoordinates(e.getX(), e.getY());
        repaint();
    }

    public void setCurrentShapeType(String s) {
        this.currentShapeType = s;
    }
}
