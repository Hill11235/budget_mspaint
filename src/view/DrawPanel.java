package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.Control;
import controller.ShapeDeepCopier;
import shapes.BasicShape;
import shapes.Cross;
import shapes.Ellipse;
import shapes.Line;
import shapes.Rect;
import shapes.Triangle;
import shapes.Triforce;

/**
 * JPanel class which is the panel where drawing takes place.
 */
public class DrawPanel extends JPanel {

    private GuiView view;
    private Control controller;
    private BasicShape shape;
    private Color col;
    private boolean fillSwitch;
    private ShapeDeepCopier sdc;
    private ArrayList<BasicShape> drawnShapes;
    private ArrayList<BasicShape> undoneShapes;

    /**
     * Constructor for panel.
     * Instantiates all variables.
     * Add mouselisteners that use helper methods and draw as required.
     * @param view - associated view class (frame).
     */
    public DrawPanel(GuiView view) {

        super();
        this.view = view;
        this.controller = view.getController();
        this.sdc = new ShapeDeepCopier();
        this.shape = view.getShape();
        this.drawnShapes = new ArrayList<BasicShape>();
        this.undoneShapes = new ArrayList<BasicShape>();
        this.col = colorUpdate();

        //mouseListener added which ensures drawing takes place.
        addMouseListener(new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
                updateShape();
                fillUpdate();

                boolean filler = getFillSwitch();
                Color currColor = colorUpdate();

                BasicShape s = getShape();
                controller.setShapeStart(s, e.getX(), e.getY());
                controller.setShapeColour(s, currColor);
                controller.setShapeFill(s, filler);

                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                updateShape();
                BasicShape s = getShape();

                controller.setShapeStart(s, e.getX(), e.getY());
                controller.adjustShapeDim(s, 20, 20);

                repaint();
                shapeReset(s);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                updateShape();
                fillUpdate();
                BasicShape s = getShape();
                int[] coords = coordHelper(s, e.getX(), e.getY());

                controller.adjustShapeDim(s, coords[0], coords[1]);

                BasicShape shapeCopy = sdc.shapeDeepCopy(s);
                drawnShapes.add(shapeCopy);

                repaint();
                shapeReset(s);
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });

        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                updateShape();
                BasicShape s = getShape();
                int[] coords = coordHelper(s, e.getX(), e.getY());

                controller.adjustShapeDim(s, coords[0], coords[1]);
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) { }
        });
    }

    /**
     * Takes drawn shape which is stored in the drawnShapes ArrayList.
     * Removes it and adds it to the redo ArrayList so that it can be redone.
     */
    public void undoLastShape() {
        if (drawnShapes.size() > 0) {
            BasicShape s = drawnShapes.get(drawnShapes.size() - 1);
            undoneShapes.add(s);
            drawnShapes.remove(drawnShapes.size() - 1);
        }
    }

    /**
     * Takes undone shape which is stored in the undoneShapes ArrayList.
     * Removes it and adds it to the drawnShapes ArrayList so that it can be drawn.
     */
    public void redoLastShape() {
        if (undoneShapes.size() > 0) {
            BasicShape s = undoneShapes.get(undoneShapes.size() - 1);
            drawnShapes.add(s);
            undoneShapes.remove(undoneShapes.size() - 1);
        }
    }

    /**
     * Clears drawnShapes ArrayList which wipes all drawn shapes from the panel.
     * Also clears undoneShapes ArrayList so that user can't redo after clearing.
     */
    public void clearPanel() {
        drawnShapes.clear();
        undoneShapes.clear();
    }

    /**
     * Getter method for shape field in panel.
     * @return current shape associated with the panel.
     */
    public BasicShape getShape() {
        return this.shape;
    }

    /**
     * Updates this panel's associated shape based on what the shape is with the view.
     * This is to account for button clicks.
     */
    public void updateShape() {
        this.shape = this.view.getShape();
    }

    /**
     * Updates panel color field to match view color field.
     * @return panel color field.
     */
    public Color colorUpdate() {
        this.col = this.view.getColor();
        return this.col;
    }

    /**
     * Updates panel fill boolean based on view.
     */
    public void fillUpdate() {
        this.fillSwitch = this.view.getFillShape();
    }

    /**
     * Gets boolean indicating whether shape should be filled or not.
     * @return boolean from panel indicating whether shape should be filled.
     */
    public boolean getFillSwitch() {
        return this.fillSwitch;
    }

    /**
     * Paints the current shape that is being drawn and all of the previous shapes that have been drawn.
     * @param g - graphic to be drawn.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.updateShape();
        BasicShape shapeToDraw = this.getShape();

        shapePainter(g2d, shapeToDraw);

        for (BasicShape shape: drawnShapes) {
            shapePainter(g2d, shape);
        }
    }

    /**
     * Method which draws each individual shape based on what type of shape it is.
     * @param g2d - graphic to be drawn.
     * @param shape - shape to be drawn.
     */
    public void shapePainter(Graphics2D g2d, BasicShape shape) {
        int x = controller.getShapeX(shape);
        int y = controller.getShapeY(shape);
        int height = controller.getShapeHeight(shape);
        int width = controller.getShapeWidth(shape);
        Shape toBeDrawn = null;

        g2d.setColor(controller.getShapeColour(shape));

        //accounts for reflections
        int[] coords = drawHelper(x, y, x + width, y + height);

        //individual shape check and drawing
        if (shape instanceof Rect) {
            toBeDrawn = new Rectangle2D.Double(coords[0], coords[1], coords[2] - coords[0], coords[3] - coords[1]);
            g2d.draw(toBeDrawn);

        } else if (shape instanceof Line) {
            toBeDrawn = new Line2D.Double(x, y, x + width, y + height);
            g2d.draw(toBeDrawn);

        } else if (shape instanceof Ellipse) {
            toBeDrawn = new Ellipse2D.Double(coords[0], coords[1], coords[2] - coords[0], coords[3] - coords[1]);
            g2d.draw(toBeDrawn);

        } else if (shape instanceof Cross) {
            crossPainter(g2d, x, y, width, height, shape);

        } else if (shape instanceof Triangle) {
            trianglePainter(g2d, x, y, width, height, shape);

        } else if (shape instanceof Triforce) {
            triforcePainter(g2d, x, y, width, height, shape);

        }

        //set fill
        if (controller.getShapeFill(shape) && toBeDrawn != null) {
            g2d.fill(toBeDrawn);
        }
    }

    /**
     * Method which draws a cross shape using a Path object to trace outline.
     * @param g2d - graphic to be drawn.
     * @param x - x coordinate of shape,
     * @param y - y coordinate of shape,
     * @param width - width of shape.
     * @param height - height of shape.
     * @param shape - shape to be drawn.
     */
    public void crossPainter(Graphics2D g2d, int x, int y, int width, int height, BasicShape shape) {

        g2d.setColor(controller.getShapeColour(shape));

        //create increments used for coordinate of draw path
        double widthInc = ((double) (width)) / 6;
        double heightInc = ((double) (height)) / 6;

        //follow path to draw a chunky cross
        Path2D path = new Path2D.Double();
        path.moveTo(x + 2 * widthInc, y + 0 * heightInc);
        path.lineTo(x + 3 * widthInc, y + 1 * heightInc);
        path.lineTo(x + 4 * widthInc, y + 0 * heightInc);
        path.lineTo(x + 6 * widthInc, y + 2 * heightInc);
        path.lineTo(x + 5 * widthInc, y + 3 * heightInc);
        path.lineTo(x + 6 * widthInc, y + 4 * heightInc);
        path.lineTo(x + 4 * widthInc, y + 6 * heightInc);
        path.lineTo(x + 3 * widthInc, y + 5 * heightInc);
        path.lineTo(x + 2 * widthInc, y + 6 * heightInc);
        path.lineTo(x + 0 * widthInc, y + 4 * heightInc);
        path.lineTo(x + 1 * widthInc, y + 3 * heightInc);
        path.lineTo(x + 0 * widthInc, y + 2 * heightInc);
        path.lineTo(x + 2 * widthInc, y + 0 * heightInc);
        path.closePath();

        g2d.draw(path);
        if (controller.getShapeFill(shape)) {
            g2d.fill(path);
        }
    }

    /**
     * Method which draws a triangle using a Path object to trace outline.
     * @param g2d - graphic to be drawn.
     * @param x - x coordinate of shape,
     * @param y - y coordinate of shape,
     * @param width - width of shape.
     * @param height - height of shape.
     * @param shape - shape to be drawn.
     */
    public void trianglePainter(Graphics2D g2d, int x, int y, int width, int height, BasicShape shape) {
        g2d.setColor(controller.getShapeColour(shape));

        //follow path to draw a triangle
        Path2D path = new Path2D.Double();
        path.moveTo(x + width / 2, y);
        path.lineTo(x + width, y + height);
        path.lineTo(x, y + height);
        path.lineTo(x + width / 2, y);
        path.closePath();

        g2d.draw(path);
        if (controller.getShapeFill(shape)) {
            g2d.fill(path);
        }
    }

    /**
     * Method which draws a triforce from zelda using a Path object to trace outline.
     * @param g2d - graphic to be drawn.
     * @param x - x coordinate of shape,
     * @param y - y coordinate of shape,
     * @param width - width of shape.
     * @param height - height of shape.
     * @param shape - shape to be drawn.
     */
    public void triforcePainter(Graphics2D g2d, int x, int y, int width, int height, BasicShape shape) {
        g2d.setColor(controller.getShapeColour(shape));

        //follow path to draw a triforce pattern
        Path2D path = new Path2D.Double();
        path.moveTo(x + 0.50 * width, y + 0.00 * height);
        path.lineTo(x + 0.75 * width, y + 0.50 * height);
        path.lineTo(x + 0.25 * width, y + 0.50 * height);
        path.lineTo(x + 0.50 * width, y + 0.00 * height);
        path.lineTo(x + 0.00 * width, y + 1.00 * height);
        path.lineTo(x + 1.00 * width, y + 1.00 * height);
        path.lineTo(x + 0.75 * width, y + 0.50 * height);
        path.lineTo(x + 0.50 * width, y + 1.00 * height);
        path.lineTo(x + 0.25 * width, y + 0.50 * height);
        path.closePath();

        //fills only outer triangles
        g2d.draw(path);
        if (controller.getShapeFill(shape)) {
            g2d.fill(path);
        }
    }

    /**
     * Resets coordinates, width and height of shape to zero.
     * @param shape - shape to be reset.
     */
    private void shapeReset(BasicShape shape) {
        controller.setShapeStart(shape, 0, 0);
        controller.adjustShapeDim(shape, 0, 0);
    }

    /**
     * Given the coordinates and dimensions of a shape, determine coordinates to draw accurately.
     * Allows shapes to be dragged in all directions.
     * @param x - x coordinate of shape.
     * @param y - y coordinate of shape.
     * @param newX - x coordinate of second point.
     * @param newY - y coordinate of second point.
     * @return integer array containing the necessary points to be drawn.
     */
    private int[] drawHelper(int x, int y, int newX, int newY) {
        int[] coords = new int[4];

        int minX = Math.min(x, newX);
        int minY = Math.min(y, newY);
        int maxX = Math.max(x, newX);
        int maxY = Math.max(y, newY);

        coords[0] = minX;
        coords[1] = minY;
        coords[2] = maxX;
        coords[3] = maxY;

        return coords;
    }

    /**
     * Determines correct width and height to be stored based on mouse position.
     * Allows shapes to be dragged and drawn in all directions.
     * Also checks if aspect lock is in place.
     * @param shape - shape to be drawn.
     * @param newX - x coordinates of mouse position.
     * @param newY - y coordinates of mouse position.
     * @return int array containing correct width and height based on mouse position.
     */
    private int[] coordHelper(BasicShape shape, int newX, int newY) {
        int[] coords = new int[2];
        boolean aspectLock = view.getAspectLock();
        int width = newX - controller.getShapeX(shape);
        int height = newY - controller.getShapeY(shape);

        if (aspectLock) {
            int minCoord = Math.min(width, height);

            width = minCoord;
            height = minCoord;

            //corrects for instances where mouse is in top right or bottom left quadrant (initial x, y origin)
            if ((newY - controller.getShapeY(shape) < 0) && (newX - controller.getShapeX(shape) > 0)) {
                width = Math.abs(width);
            } else if ((newY - controller.getShapeY(shape) > 0) && (newX - controller.getShapeX(shape) < 0)) {
                height = Math.abs(height);
            }
        }
        coords[0] = width;
        coords[1] = height;

        return coords;
    }
}
