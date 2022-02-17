import static org.junit.Assert.*;

import org.junit.Test;

public class PrimitivesTest {

    @Test
    public void root() {
        int x = 4;
        // Integer xObject = new Integer(33);
        Integer xObject = x; // auto-boxing;
        x = xObject; // auto-unboxing
        int y = (int) Math.sqrt(x);
        // string s = "bill"; // s is stored on stack
        // s.length();
        // string *p = new string("bill");
        // p->length();
        // String s = "bill";
        // s.length() <===> s->length();
        Primitives p = new Primitives();
        // double x = 4.0;
        double expect = 2.0;
        double actual = p.root(x);
        assertEquals(expect,actual,0.0);
    }

    @Test
    public void logic() {
    }
}