package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import controller.Control;
import shapes.BasicShape;
import shapes.Cross;
import shapes.Ellipse;
import shapes.Line;
import shapes.Rect;
import shapes.Triangle;
import shapes.Triforce;

/**
 * Class for frame and view of the GUI. Where all the view components come together.
 */
public class GuiView implements PropertyChangeListener, ActionListener {

    private static final int FRAME_HEIGHT = 500;
    private static final int FRAME_WIDTH = 1200;

    private JFrame mainFrame;
    private JToolBar toolbar;
    private JCheckBox fillCheckBox;
    private JButton colourButton;
    private JButton undoButton;
    private JButton redoButton;
    private JButton lineButton;
    private JButton rectButton;
    private JButton ellipButton;
    private JButton crossButton;
    private JButton triangleButton;
    private JButton triforceButton;
    private JButton clearButton;

    private BasicShape shape = new Rect();
    private Control controller  = new Control();;
    private boolean fillShape = false;
    private boolean aspectLock = false;
    private Color col;

    private DrawPanel drawArea = new DrawPanel(this);
    private keyAction aspectAction = new keyAction();

    /**
     * Constructor view and frame. Sets up all constituent components via helper methods.
     */
    public GuiView() {
        this.mainFrame = new JFrame();  // set up the main frame for this GUI
        toolbar = new JToolBar();
        drawArea.setBackground(Color.white);
        setupComponents();

        // add the view as an observer of BasicShape
        shape.addObserver(this);

    }

    /**
     * Getter method for controller field.
     * @return controller linked to this view.
     */
    public Control getController() {
        return this.controller;
    }

    /**
     * Getter method for shape associated with class.
     * @return shape currently linked to this view.
     */
    public BasicShape getShape() {
        return this.shape;
    }

    /**
     * Set view's shape field.
     * @param shape - shape to be set.
     */
    public void setShape(BasicShape shape) {
        this.shape = shape;
    }

    /**
     * Initialises the toolbar to contain the buttons.
     * Listeners are created for the buttons which translate user events to method calls.
     */
    private void setupToolbar() {

        fillCheckBox = new JCheckBox("Fill Shape");
        fillCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                toggleFillShape();
                update();
            }
        });

        colourButton = new JButton("Colour");
        colourButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Color col = JColorChooser.showDialog(null, "Choose a Color", drawArea.getForeground());
                setColor(col);
                colourButton.setForeground(col);
                update();
            }
        });

        undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                drawArea.undoLastShape();
                update();
            }
        });

        redoButton = new JButton("Redo");
        redoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                drawArea.redoLastShape();
                update();
            }
        });

        lineButton = new JButton("Line");
        lineButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setShape(new Line());
            }
        });

        rectButton = new JButton("Rectangle");
        rectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setShape(new Rect());
            }
        });

        ellipButton = new JButton("Ellipse");
        ellipButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setShape(new Ellipse());
            }
        });

        crossButton = new JButton("Cross");
        crossButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setShape(new Cross());
            }
        });

        triangleButton = new JButton("Triangle");
        triangleButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setShape(new Triangle());
            }

        });

        triforceButton = new JButton("Triforce");
        triforceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setShape(new Triforce());
            }

        });

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                drawArea.clearPanel();
                update();
            }
        });

        // add buttons to toolbar
        toolbar.add(fillCheckBox);
        toolbar.add(colourButton);
        toolbar.add(undoButton);
        toolbar.add(redoButton);
        toolbar.add(lineButton);
        toolbar.add(rectButton);
        toolbar.add(ellipButton);
        toolbar.add(crossButton);
        toolbar.add(triangleButton);
        toolbar.add(triforceButton);
        toolbar.add(clearButton);

        // add toolbar at top of frame
        mainFrame.add(toolbar, BorderLayout.NORTH);
        toolbar.setVisible(true);
    }

    /**
     * Method to setup the toolbar components and create key binding for aspect ratio lock.
     */
    private void setupComponents() {

        setupToolbar();
        mainFrame.add(drawArea, BorderLayout.CENTER);
        mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        //set keybinding for 'l' key
        JRootPane jrp = mainFrame.getRootPane();
        jrp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('l'), "aspectAction");
        jrp.getActionMap().put("aspectAction", this.aspectAction);
    }

    /**
     * This method contains code to update the GUI.
     * @param event - change to associated property.
     */
    public void propertyChange(final PropertyChangeEvent event) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                drawArea.repaint();
            }
        });
    }

    /**
     * Getter method for color chosen by user.
     * @return color chosen by user.
     */
    public Color getColor() {
        return col;
    }

    /**
     * Sets color field.
     * @param col - color to be set.
     */
    public void setColor(Color col) {
        this.col = col;
    }

    /**
     * Switch for aspect ratio boolean.
     */
    public void toggleAspectLock() {
        if (!this.aspectLock) {
            this.aspectLock = true;
         } else {
             this.aspectLock = false;
         }
    }

    /**
     * Returns aspect ratio lock boolean.
     * @return aspect ratio lock boolean.
     */
    public boolean getAspectLock() {
        return this.aspectLock;
    }

    /**
     * Switch for boolean which indicates whether or not shapes should be filled.
     */
    public void toggleFillShape() {
        if (!this.fillShape) {
           this.fillShape = true;
        } else {
            this.fillShape = false;
        }
    }

    /**
     * Getter method for fill boolean.
     * @return fill boolean.
     */
    public boolean getFillShape() {
        return this.fillShape;
    }

    /**
     * Whenever user performs an action, call update method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    /**
     * Performs re-layout and repainting of all components.
     */
    public void update() {
        mainFrame.validate();
        mainFrame.repaint();
        drawArea.repaint();
    }

    /**
     * Action class defined for key binding.
     * When action takes place, toggles aspect ratio lock boolean.
     */
    public class keyAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            toggleAspectLock();
            update();
        }
    }
}
