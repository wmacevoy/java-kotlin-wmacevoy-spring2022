import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SampleTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getVolume() {
        Sample plywood = new Sample(3.0/8.0, 8.0*12.0, 4.0*12.0);

        double expect = 3.0/8.0*8.0*12.0*4.0*12.0;

        assertEquals(expect,plywood.getVolume(),0.000_000_1);
    }
}