public class Primitives {
    boolean truthy = true; // true or false
    // Boolean b;

    byte eightBits = 100; // -128 .. 127
    // Byte
    byte b2 = -0x80;
    byte b3 = 0b0110_1010;
    byte b4 = ((byte) 255);
    short sixteenBits; // -32_768 .. 32_767
    //Short
    int thirtyTwoBits; // -2_147_483_648 .. 2_147_483_647
    //Integer
    long sixtyFourBits; // -9_223_372_036_854_775_808 .. -9_223_372_036_854_775_807
    //Long
    char sixteenUnsignedBits; // 0..65_535
    //Character

    float f32 = 0.3f; // IEEE 754 single precision float 23 bits of mantessa and 8 bits of exponent
               // 1.mmmmmmmmm's x 2^(eeeee's-128)
    // Float

    // 234.1234567 = 0.2341234567 x 10^3 = 0.110101010110101010101010 x 2^xxxx
               //
               // about 7 decimal digits of precision, 10^(+/- 30)
    double d64 = 0.3; // IEEE 754 double 53 bits of mantessa and 10 bits of exponent
               // 2.mmmmmmmmmmmmmmm x 2^(eeeeeeee's-512)
                // about 15 digits of precision, 10^(+/- 300).
    //Double

    Object x = null;

    double root(double x) {
        return (x >= 0.0) ? Math.sqrt(x) : 0.0;
    }
    int root(int x) { return (x >= 0) ? (int) Math.sqrt(x) : 0;}

    void logic() {
        boolean p = true, q = false;
        boolean pAndQ = p && q;
        boolean pOrQ = p || q;
        boolean notP = !p;
        boolean pEqualsq = (p == q);

        boolean inOrder = (3 <= 5) && (5 <= 7);
    }
}
