package tests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.beans.PropertyChangeListener;

import shapes.BasicShape;
import shapes.Ellipse;
import shapes.Rect;
import view.GuiView;

public class BasicShapeTest {

    public BasicShape shapeFull;
    public BasicShape shapeEmpty;
    public GuiView view;
    
    @Before
    public void setUp() throws Exception {
        shapeFull = new Rect(1, 2, 3, 4);
        shapeEmpty = new Rect();
        view = new GuiView();
        shapeFull.addObserver(view);
        shapeEmpty.addObserver(view);
    }

    @Test
    public void testBasicShapeIntIntIntInt() {
        //abstract class but super calls in subclass constructors
        //abstract constructors tested via subclass
        BasicShape s = new Rect(4, 5, 6, 7);
        assertNotNull(s);
    }

    @Test
    public void testBasicShape() {
        //abstract class but super calls in subclass constructors
        //abstract constructors tested via subclass
        BasicShape s = new Rect();
        assertNotNull(s);
    }

    @Test
    public void testSetX() {
        shapeEmpty.setX(50);
        assertEquals(shapeEmpty.getX(), 50);
    }

    @Test
    public void testGetX() {
        assertEquals(shapeFull.getX(), 1);
    }

    @Test
    public void testSetY() {
        shapeEmpty.setY(60);
        assertEquals(shapeEmpty.getY(), 60);
    }

    @Test
    public void testGetY() {
        assertEquals(shapeFull.getY(), 2);
    }

    @Test
    public void testSetCol() {
        Color col = Color.black;
        shapeEmpty.setCol(col);
        assertEquals(Color.black, shapeEmpty.getCol());
    }

    @Test
    public void testGetCol() {
        Color col = Color.red;
        shapeFull.setCol(col);
        assertEquals(Color.red, shapeFull.getCol());
    }

    @Test
    public void testGetHeight() {
        assertEquals(shapeFull.getHeight(), 4);
    }

    @Test
    public void testSetHeight() {
        shapeEmpty.setHeight(70);
        assertEquals(shapeEmpty.getHeight(), 70);
    }

    @Test
    public void testGetWidth() {
        assertEquals(shapeFull.getWidth(), 3);
    }

    @Test
    public void testSetWidth() {
        shapeEmpty.setWidth(80);
        assertEquals(shapeEmpty.getWidth(), 80);
    }

    @Test
    public void getNotifier() {
        assertNotNull(shapeEmpty.getNotifier());
    }
    
    @Test
    public void testAddObserver() {
        GuiView view2 = new GuiView();
        BasicShape testShape = new Ellipse();
        testShape.addObserver(view2);
        PropertyChangeListener[] pcs = testShape.getNotifier().getPropertyChangeListeners();
        
        assertEquals(pcs.length, 1);
    }
    
    @Test
    public void testGetFill() {
        shapeEmpty.setFill(false);
        assertFalse(shapeEmpty.getFill());
    }
    
    @Test
    public void testSetFill() {
        shapeEmpty.setFill(true);
        assertTrue(shapeEmpty.getFill());
    }
}
