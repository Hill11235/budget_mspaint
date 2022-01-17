package controller;

import java.awt.Color;

import shapes.BasicShape;

/**
 * Controller class in MVC structure.
 * Takes input from view and interacts with BasicShape class.
 */
public class Control {

    /**
     * No args constructor.
     */
    public Control() {
    }

    /**
     * Takes given shape and updates its x and y coordinates.
     * @param shape - input shape.
     * @param x - coordinate.
     * @param y - coordinate.
     */
    public void setShapeStart(BasicShape shape, int x, int y) {
        shape.setX(x);
        shape.setY(y);
    }

    /**
     * Takes a given shape and updates its width and height.
     * @param shape - input shape.
     * @param width - width of shape.
     * @param height - height of shape.
     */
    public void adjustShapeDim(BasicShape shape, int width, int height) {
        shape.setWidth(width);
        shape.setHeight(height);
    }

    /**
     * Get x coordinate of given shape.
     * @param shape - input shape.
     * @return x coordinate of shape.
     */
    public int getShapeX(BasicShape shape) {
        return shape.getX();
    }

    /**
     * Get y coordinate of given shape.
     * @param shape - input shape.
     * @return y coordinate of shape.
     */
    public int getShapeY(BasicShape shape) {
        return shape.getY();
    }

    /**
     * Get height for given shape.
     * @param shape - input shape.
     * @return height of shape.
     */
    public int getShapeHeight(BasicShape shape) {
        return shape.getHeight();
    }

    /**
     * Get shape width for given shape.
     * @param shape - input shape.
     * @return width of shape.
     */
    public int getShapeWidth(BasicShape shape) {
        return shape.getWidth();
    }

    /**
     * Sets colour of given shape.
     * @param shape - input shape.
     * @param col - color of shape.
     */
    public void setShapeColour(BasicShape shape, Color col) {
        shape.setCol(col);
    }

    /**
     * Get shape colour for given shape.
     * @param shape - input shape.
     * @return color of shape.
     */
    public Color getShapeColour(BasicShape shape) {
        return shape.getCol();
    }

    /**
     * Set boolean indicating whether shape should be filled.
     * @param shape - input shape.
     * @param filler - fill boolean.
     */
    public void setShapeFill(BasicShape shape, boolean filler) {
        shape.setFill(filler);
    }

    /**
     * Returns fill boolean for given shape.
     * @param shape - input shape.
     * @return boolean indicating whether shape should be filled.
     */
    public boolean getShapeFill(BasicShape shape) {
        return shape.getFill();
    }
}
