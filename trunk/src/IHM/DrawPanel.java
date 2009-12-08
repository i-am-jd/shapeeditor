package IHM;

import Draw.*;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Enumeration;
import javax.swing.JPanel;

public class DrawPanel extends JPanel 
        implements MouseListener, MouseMotionListener
{

    private float x = 100;
    private float y = 100;
    private Shape shape = null;
    private InfoBar infoBar;
    //private TreePanel treeZone;
    private SceneGraph sceneGraph;
    private String currentShapeType;
    
    public DrawPanel(InfoBar infoBar, SceneGraph sceneGraph) {
        super();

        this.infoBar = infoBar;
        this.sceneGraph = sceneGraph;
        /*this.treeZone = treeZone;*/
        this.currentShapeType = "Rectangle";
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        addMouseListener(this);
	addMouseMotionListener(this);
        //this.setLayout(new BorderLayout());
        //this.add(new JScrollPane()); //, BorderLayout.CENTER);
    }

    public void paintComponent(Graphics g) {
        // caract�ristiques graphiques : mise en place de l'antialiasing
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // taille de la zone de dessin
        Dimension d = getSize();
        // on commence par effacer le fond
        g2d.clearRect(0, 0, d.width, d.height);

        for (Enumeration en = sceneGraph.children(); en.hasMoreElements();) {
            ((Shape)en.nextElement()).drawShape(g2d);
        }

        if (shape != null) {
            shape.drawShape(g2d);
        }
        /*int x1 = this.getWidth()/4;
        int y1 = this.getHeight()/4;   
        g.drawOval(x1, y1, this.getWidth()/2, this.getHeight()/2);*/

        /*Circle c = new Circle(100);
        g2d.draw(new Ellipse2D.Double((int)c.getX(), (int)c.getY(), (int)c.getRadius(), (int)c.getRadius()));
        
        View v = new View(Color.YELLOW, 10, Color.BLUE, null);
        Triangle t = new Triangle(new int[]{10,10,110}, new int[]{10,110,110});
        g2d.setColor(v.getFillColor());
        g.fillPolygon(t.getX(), t.getY(), 3);
        BasicStroke st = new BasicStroke(v.getLineWidth());
        g2d.setStroke(st);
        g2d.setColor(v.getLineColor());
        g.drawPolygon(t.getX(), t.getY(), 3);*/

        /*Geometry ge1 = new Rectangle(100, 100, 100, 100);
        View v = new View(Color.YELLOW, 10, Color.BLUE, null);
        Shape rect = new Shape(v, ge1);
        rect.drawShape(g2d);

        Geometry ge2 = new Circle(400, 400, 50);
        Shape circ = new Shape(v, ge2);
        circ.drawShape(g2d);*/
        
        /*Rectangle r = new Rectangle(10,10,10,10);
        g.fillRect(r.getX(), r.getY(), r.getHeight(), r.getWidth());
        Square s =  new Square(500);
        g.fillRect(s.getX(), s.getY(), s.getHeight(), s.getWidth());*/
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        if (currentShapeType.equals("Square")) {
            shape = new Shape(sceneGraph.getView(), new Square((int)x, (int)y, 0));
        } else if (currentShapeType.equals("Rectangle")) {
            shape = new Shape(sceneGraph.getView(), new Rectangle((int)x, (int)y, 0, 0));
                //(int)x-e.getX(), (int)y-e.getY()));
        } else if (currentShapeType.equals("Circle")) {
            shape = new Shape(sceneGraph.getView(), new Circle((int)x, (int)y, 0));
        } else if (currentShapeType.equals("Ellipse")) {
            shape = new Shape(sceneGraph.getView(), new Ellipse((int)x, (int)y, 0, 0));
        }
        infoBar.printMessage("Release to draw the shape");
        System.out.println("mousePressed");
        /* x += 10;
        y += 10;
        Geometry ge = new Rectangle((int)x, (int)y, 100, 100);
        View v = new View(Color.YELLOW, 3, Color.BLUE, null);
        new Shape(v, ge).insertShape(sceneGraph);
        repaint();*/
        //treeZone.reloadJTree();
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shape.insertShape(sceneGraph); // stockage de la droite
        // lib�ration de la droite temporaire
        // destruction de sa r�f�rence sachant qu'elle est
        // sauvegard�e dans la classe dessin
        shape = null;

        repaint();

        infoBar.printMessage("Cliquez pour initier une figure");
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        infoBar.printDefaultCoordinates();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        int x = e.getX();
	int y = e.getY();

        // affichage des coordonn�es
        infoBar.printCoordinates(x, y);

        // d�placement de l'extr�mit� de la droite
        if (shape.getGeometry() instanceof Square) {
            Square s = (Square) shape.getGeometry();
            if (x-this.x>0 && y-this.y>0) {
                s.setX((int)this.x);
                s.setY((int)this.y);
                s.setSide(y-(int)this.y);
            } else if (x-this.x>0) {
                s.setX((int)this.x);
                s.setY(y);
                s.setSide((int)this.y-y);
            } else if (y-this.y>0) {
                s.setX(x);
                s.setY((int)this.y);
                s.setSide(y-(int)this.y);
                //System.out.println(s.getX()+" "+s.getY()+" "+s.getSide());
             } else {
                s.setX(x);
                s.setY(y);
                s.setSide((int)this.y-y);
            }
        } else if (shape.getGeometry() instanceof Rectangle) {
            Rectangle r = (Rectangle) shape.getGeometry();
            if (x-this.x>0 && y-this.y>0) {
                r.setX((int)this.x);
                r.setY((int)this.y);
                r.setHeight(y-(int)this.y);
                r.setWidth(x-(int)this.x);
            } else if (x-this.x>0) {
                r.setX((int)this.x);
                r.setY(y);
                r.setHeight(y-(int)this.y);
                r.setWidth((int)this.x-x);
            } else if (y-this.y>0) {
                r.setX(x);
                r.setY((int)this.y);
                r.setHeight((int)this.y-y);
                r.setWidth(x-(int)this.x);
            } else {
                r.setX(x);
                r.setY(y);
                r.setHeight((int)this.y-y);
                r.setWidth((int)this.x-x);
            }
        } else if (shape.getGeometry() instanceof Circle) {
            Circle r = (Circle) shape.getGeometry();
            r.setX((int)this.x);
            r.setY((int)this.y);
            r.setRadius((int)Math.sqrt(Math.pow(x-this.x,2)+Math.pow(y-this.y,2)));
        } else if (shape.getGeometry() instanceof Ellipse) {
            Ellipse el = (Ellipse) shape.getGeometry();
            if (x-this.x>0 && y-this.y>0) {
                el.setSemiMajorAxis(x-(int)this.x);
                el.setSemiMinorAxis(y-(int)this.y);
            } else if (x-this.x>0) {
                el.setSemiMajorAxis(x-(int)this.x);
                el.setSemiMinorAxis((int)this.y-y);
            } else if (y-this.y>0) {
                el.setSemiMajorAxis((int)this.x-x);
                el.setSemiMinorAxis(y-(int)this.y);
            } else {
                el.setSemiMajorAxis((int)this.x-x);
                el.setSemiMinorAxis((int)this.y-y);
            }
        }

        // dessin du graphe de scene et de la forme en construction
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        infoBar.printCoordinates(e.getX(), e.getY());
    }

    public void setCurrentShapeType(String s) {
        this.currentShapeType = s;
    }
}
