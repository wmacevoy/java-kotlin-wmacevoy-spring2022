package origami;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SwanTest {

    Swan defSwan;
    Swan foilSwan;

    @Before
    public void setUp() throws Exception {
        defSwan = new Swan();
        foilSwan = new Swan("foil", 8.5, 11.0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void make() {
        assertEquals(0, defSwan.getStep());
        assertEquals(Swan.DEFAULT_MATERIAL, defSwan.getMaterial());
        assertEquals(Swan.DEFAULT_HEIGHT, defSwan.getHeight(), 1e-14);
        assertEquals(Swan.DEFAULT_WIDTH, defSwan.getWidth(), 1e-14);
        assertEquals(0, foilSwan.getStep());
        assertEquals("foil", foilSwan.getMaterial());
        assertEquals(11.0, foilSwan.getHeight(), 1e-14);
        assertEquals(8.5, foilSwan.getWidth(), 1e-14);
    }

    @Test
    public void finished() {

    }

    @Test
    public void areaOfCircle() {
    }

    @Test
    public void areaOfRectangle() {
    }

    @Test
    public void getMaterial() {
    }

    @Test
    public void getStep() {
    }

    @Test
    public void getWidth() {
    }

    @Test
    public void getHeight() {
    }

    @Test
    public void advance() {
        for (int i = 0; i < Swan.STEPS; ++i) {
            defSwan.advance();
            assertEquals(i + 1, defSwan.getStep());
            assertEquals(i == Swan.STEPS - 1, defSwan.finished());
        }
    }

    @Test
    public void directions() {
        String [] expected = new String [] {
                "get the paper",
                "fold it up.",
                "fold it more.",
                "look up on oragami.org for step $s"
        };

        for (int step = 0; step < Swan.STEPS; ++step) {
            String expect = expected[step < 3 ? step : 3];
            expect = expect.replace("$s","" + step);
            assertEquals(expect, defSwan.directions());
            defSwan.advance();
        }
    }

    @Test
    public void testFinished() {
    }
}