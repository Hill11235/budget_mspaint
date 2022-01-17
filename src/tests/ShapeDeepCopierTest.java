package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import controller.ShapeDeepCopier;
import shapes.BasicShape;
import shapes.Rect;

public class ShapeDeepCopierTest {

    @Before
    public void setUp() throws Exception {   
    }

    @Test
    public void testObjectCopier() {
        ShapeDeepCopier sdcTest = new ShapeDeepCopier();
        assertNotNull(sdcTest);
    }

    @Test
    public void testShapeDeepCopy() {
        ShapeDeepCopier sdc = new ShapeDeepCopier();
        BasicShape recto = new Rect(1, 2, 3, 4);   
        BasicShape rectoCopy = sdc.shapeDeepCopy(recto);
        assertEquals(Color.black, rectoCopy.getCol());
        assertEquals(recto.getX(), rectoCopy.getX());
        assertEquals(recto.getY(), rectoCopy.getY());
        assertEquals(recto.getWidth(), rectoCopy.getWidth());
        assertEquals(recto.getHeight(), rectoCopy.getHeight());
        assertEquals(recto.getFill(), rectoCopy.getFill());
    }

}
