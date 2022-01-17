package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import shapes.Triangle;

public class TriangleTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testTriangleIntIntIntInt() {
        Triangle tri = new Triangle(1,2,3,4);
        
        assertNotNull(tri);
        assertEquals(tri.getX(), 1);
    }

    @Test
    public void testTriangle() {
        Triangle tri2 = new Triangle();
        
        assertNotNull(tri2);
    }

}
