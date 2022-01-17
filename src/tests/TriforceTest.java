package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import shapes.Triforce;

public class TriforceTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testTriforceIntIntIntInt() {
        Triforce tri = new Triforce(1,2,3,4);
        
        assertNotNull(tri);
        assertEquals(tri.getX(), 1);
    }

    @Test
    public void testTriforce() {
        Triforce tri2 = new Triforce();
        
        assertNotNull(tri2);
    }

}
