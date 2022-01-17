package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import shapes.Rect;

public class RectTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testRectIntIntIntInt() {
        Rect recto = new Rect(1,2,3,4);
        
        assertNotNull(recto);
        assertEquals(recto.getX(), 1);
    }

    @Test
    public void testRect() {
        Rect recto2 = new Rect();
        
        assertNotNull(recto2);
    }

}
