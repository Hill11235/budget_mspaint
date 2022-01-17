package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import shapes.Line;

public class LineTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testLineIntIntIntInt() {
        Line liner = new Line(1,2,3,4);
        
        assertNotNull(liner);
        assertEquals(liner.getX(), 1);
    }

    @Test
    public void testLine() {
        Line liner2 = new Line();
        
        assertNotNull(liner2);
    }

}
