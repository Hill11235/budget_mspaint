package controller;

import java.awt.Color;

import shapes.BasicShape;
import shapes.Cross;
import shapes.Ellipse;
import shapes.Line;
import shapes.Rect;
import shapes.Triangle;
import shapes.Triforce;

/**
 * Class that can be used to create a copy of shapes and colors.
 * Creates new instance and copies internal data.
 */
public class ShapeDeepCopier {

    private Control controller;

    /**
     * No args constructor.
     * Instantiates and links a controller.
     */
    public ShapeDeepCopier() {
        this.controller = new Control();
    }

    /**
     * Takes shape as input and creates new shape object with same internal data.
     * @param initShape - shape to be copied.
     * @return copied shape.
     */
    public BasicShape shapeDeepCopy(BasicShape initShape) {
        BasicShape newShape = null;

        //get internal data of shape to copied
        int x = controller.getShapeX(initShape);
        int y = controller.getShapeY(initShape);
        int height = controller.getShapeHeight(initShape);
        int width = controller.getShapeWidth(initShape);
        Color col = controller.getShapeColour(initShape);
        boolean filler = controller.getShapeFill(initShape);

        //horrible if chain which instantiates correct BasicShape subclass
        if (initShape instanceof Rect) {
            newShape = new Rect(x, y, width, height);

        } else if (initShape instanceof Line) {
            newShape = new Line(x, y, width, height);

        } else if (initShape instanceof Ellipse) {
            newShape = new Ellipse(x, y, width, height);

        } else if (initShape instanceof Cross) {
            newShape = new Cross(x, y, width, height);

        } else if (initShape instanceof Triangle) {
            newShape = new Triangle(x, y, width, height);

        } else if (initShape instanceof Triforce) {
            newShape = new Triforce(x, y, width, height);

        }

        //set colour and fill  boolean of new shape
        if (newShape != null) {
            controller.setShapeColour(newShape, colorDeepCopy(col));
            controller.setShapeFill(newShape, filler);
        }

        return newShape;
    }

    /**
     * Takes color and create new color object that is the same color.
     * If color is null, sets color to default black.
     * @param col - color to be copied.
     * @return copied color object.
     */
    private Color colorDeepCopy(Color col) {
        Color newCol = null;

        if (col == null) {
            newCol = new Color(0, 0, 0);
            return newCol;
        }
        int rgb = col.getRGB();
        newCol = new Color(rgb);

        return newCol;
    }

}
