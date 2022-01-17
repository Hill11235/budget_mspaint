package shapes;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Abstract class for shapes. Contains all basic information for each shape.
 * Extended by more specific shapes.
 */
public abstract class BasicShape {

    private int xPos;
    private int yPos;
    private Color col;
    private boolean fill;
    private PropertyChangeSupport notifier;
    private int width;
    private int height;

    /**
     * Constructor with coordinates, width and height.
     * Creates PropertyChangeSupport notifier to track and notify listeners.
     * @param x - x coordinate.
     * @param y - y coordinate.
     * @param width - width of shape.
     * @param height - height of shape.
     */
    public BasicShape(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        notifier = new PropertyChangeSupport(this);
    }

    /**
     * No args constructor.
     * Creates PropertyChangeSupport notifier to track and notify listeners.
     */
    public BasicShape() {
        notifier = new PropertyChangeSupport(this);
    }

    /**
     * X coordinate setter method.
     * @param x - x coordinate.
     */
    public void setX(int x) {
        this.xPos = x;
    }

    /**
     * Getter method for shape x coordinate.
     * @return shape x coordinate.
     */
    public int getX() {
        return this.xPos;
    }

    /**
     * Y coordinate setter method.
     * @param y - y coordinate.
     */
    public void setY(int y) {
        this.yPos = y;
    }

    /**
     * Getter method for shape y coordinate.
     * @return shape y coordinate.
     */
    public int getY() {
        return this.yPos;
    }

    /**
     * Sets color of shape.
     * @param col - color to be set.
     */
    public void setCol(Color col) {
        this.col = col;
    }

    /**
     * Getter method for shape color.
     * @return color of shape.
     */
    public Color getCol() {
        return this.col;
    }

    /**
     * Getter method for shape height.
     * @return height of shape.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Set height of shape.
     * @param height - height to be set.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter method for shape width.
     * @return width of shape.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets width of shape.
     * @param width - width to be set.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Add listener to class.
     * @param listener - listener to be added.
     */
    public void addObserver(PropertyChangeListener listener) {
        notifier.addPropertyChangeListener(listener);
    }

    /**
     * Getter method for notifier.
     * @return notifier associated with class.
     */
    public PropertyChangeSupport getNotifier() {
        return this.notifier;
    }

    /**
     * Getter method for shape fill boolean.
     * @return boolean indicating whether shape should be filled or not.
     */
    public boolean getFill() {
        return fill;
    }

    /**
     * Sets boolean fill parameter.
     * @param fill - boolean parameter indicating whether shape should be filled.
     */
    public void setFill(boolean fill) {
        this.fill = fill;
    }
}
