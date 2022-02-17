import static org.junit.Assert.*;

import org.junit.Test;

public class PrimitivtesTest {

    @Test
    public void root() {
        Primitivtes p = new Primitivtes();
        double x = 4.0;
        double expect = 2.0;
        double actual = p.root(x);
        assertEquals(expect,actual,0.0);
    }

    @Test
    public void logic() {
    }
}