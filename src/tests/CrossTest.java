package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import shapes.Cross;

public class CrossTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCrossIntIntIntInt() {
        Cross x = new Cross(1,2,3,4);
        
        assertNotNull(x);
        assertEquals(x.getX(), 1); 
    }

    @Test
    public void testCross() {
        Cross x2 = new Cross();
        
        assertNotNull(x2);
    }

}
