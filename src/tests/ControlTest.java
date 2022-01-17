package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import controller.Control;
import shapes.BasicShape;
import shapes.Rect;
import view.GuiView;

public class ControlTest {

    public GuiView view;
    public Control con;
    public BasicShape shape;
    
    @Before
    public void setUp() throws Exception {
        view = new GuiView();
        shape = new Rect();
        con = new Control();
        shape.setX(1);
        shape.setY(2);
        shape.setWidth(3);
        shape.setHeight(4);
    }

    @Test
    public void testControl() {
        //test object instantiated
        //test control shape equals view shape
        Control conTest = new Control();
        assertNotNull(conTest);
    }

    @Test
    public void testGetShapeX() {
        assertEquals(con.getShapeX(shape), 1);
    }

    @Test
    public void testGetShapeY() {
        assertEquals(con.getShapeY(shape), 2);
    }

    @Test
    public void testGetShapeHeight() {
        assertEquals(con.getShapeHeight(shape), 4);
    }

    @Test
    public void testGetShapeWidth() {
        assertEquals(con.getShapeWidth(shape), 3);
    }
    
    @Test
    public void testSetShapeStart() {
        con.setShapeStart(shape, 37, 43);
        assertEquals(con.getShapeX(shape), 37);
        assertEquals(con.getShapeY(shape), 43);
    }

    @Test
    public void testAdjustShapeDim() {
        con.adjustShapeDim(shape, 300, 200);
        assertEquals(con.getShapeWidth(shape), 300);
        assertEquals(con.getShapeHeight(shape), 200);
    }

    @Test
    public void testSetShapeColour() {
        Color col = Color.black;
        con.setShapeColour(shape, col);
        assertEquals(Color.black, shape.getCol());
    }

    @Test
    public void testGetShapeCol() {
        Color col = Color.black;
        con.setShapeColour(shape, col);
        assertEquals(con.getShapeColour(shape), Color.black);
    }
    
    @Test
    public void testSetShapeFill() {
        boolean fillMe = false;
        con.setShapeFill(shape, fillMe);
        assertFalse(shape.getFill());
    }
    
    @Test
    public void testGetShapeFill() {
        boolean fillMe = true;
        con.setShapeFill(shape, fillMe);
        assertTrue(con.getShapeFill(shape));
    }

}
