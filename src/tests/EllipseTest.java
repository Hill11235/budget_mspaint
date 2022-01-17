package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import shapes.Ellipse;

public class EllipseTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEllipseIntIntIntInt() {
        Ellipse ellip = new Ellipse(1,2,3,4);
        
        assertNotNull(ellip);
        assertEquals(ellip.getX(), 1);
    }

    @Test
    public void testEllipse() {
        Ellipse ellip2 = new Ellipse();
        
        assertNotNull(ellip2);
    }

}
