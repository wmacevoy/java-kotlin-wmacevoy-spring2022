package origami;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class SwanTest {

    Swan defSwan;
    Swan foilSwan;

    String [] names = {"black", "grey", "white", "ugly" };

    Swan getSwan(String name) {
        switch(name) {
            case "black" : return new Swan("felt",3,5);
            case "grey" : {
                Swan grey = new Swan("felt",3,5);
                grey.addCygnet(new Swan("foil", 1,2));
                return grey;
            }
            case "white" : return new Swan("paper",3,5);
            case "ugly" : return new Swan("papyrus", 3, 5);
        }
        throw new IllegalArgumentException();
    }


    @Before
    public void setUp() throws Exception {
        defSwan = new Swan();
        foilSwan = new Swan("foil", 8.5, 11.0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void swanHash() {
        for (int id1 = 0; id1 < names.length; ++id1) {
            Swan swan1 = getSwan(names[id1]);
            for (int id2 = 0; id2 < names.length; ++id2) {
                Swan swan2 = getSwan(names[id2]);
                if (id1 != id2) {
                    assertNotEquals(swan1.hashCode(), swan2.hashCode());
                    assertFalse(swan1.equals(swan2));
                } else {
                    assertEquals(swan1.hashCode(), swan2.hashCode());
                    assertTrue(swan1.equals(swan2));
                }
            }
        }
    }


    @Test
    public void swanCompare() {
        ArrayList<Swan> swans = new ArrayList<>();
        for (int id = 0; id < names.length; ++id) {
            swans.add(getSwan(names[id]));
        }
        Collections.sort(swans);
        for (int i = 0; i < names.length; ++i) {
            for (int j = 0; j < names.length; ++j) {
                Swan swan1 = swans.get(i);
                Swan swan2 = swans.get(j);
                if (i == j) {
                    assertTrue(swan1.compareTo(swan2) == 0);
                    assertTrue(swan1.equals(swan2));
                } else {
                    assertFalse(swan1.equals(swan2));
                    if (i < j) {
                        assertTrue(swan1.compareTo(swan2) < 0);
                    } else {
                        assertTrue(swan1.compareTo(swan2) > 0);
                    }
                }
            }
        }
    }

    @Test
    public void swanSet() {
        int nSubsets=(int) Math.pow(2.0,(double) names.length);
        for (int type = 0; type < 2; ++type) {
            for (int subset = 0; subset < nSubsets; ++subset) {
                Set<Swan> swans = (type == 0) ?
                            new HashSet<Swan>() :
                            new TreeSet<Swan>();
                for (int id = 0; id < names.length; ++id) {
                    if ((subset & (1 << id)) != 0) {
                        swans.add(getSwan(names[id]));
                    }
                }
                for (int id = 0; id < names.length; ++id) {
                    boolean containsId = ((subset & (1 << id)) != 0);
                    assertEquals(containsId, swans.contains(getSwan(names[id])));
                }
            }
        }
    }

    @Test
    public void swanMap() {
        Map<Swan,Long> barCodes = new TreeMap<Swan,Long>();
        barCodes.put(getSwan("grey"),123_234_234_234_242L);
        barCodes.put(getSwan("white"),33L);
        assertEquals(null, barCodes.get(getSwan("black")));
        assertEquals(new Long(123_234_234_234_242L), barCodes.get(getSwan("grey")));

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