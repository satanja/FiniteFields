package Values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by s152124 on 17-10-2017.
 */
class ZmodPTest {

    final ZmodP a1p2 = new ZmodP(0, 2);
    final ZmodP a2p2 = new ZmodP(1, 2);
    final ZmodP a3p2 = new ZmodP(2, 2);
    final ZmodP a4p2 = new ZmodP(3, 2);
    final ZmodP b1p2 = new ZmodP(0, 3);
    final ZmodP b2p2 = new ZmodP(1, 3);
    final ZmodP b3p2 = new ZmodP(2, 3);
    final ZmodP b4p2 = new ZmodP(3, 3);

    @Test
    void add() {

        addTest(a1p2, a1p2, new ZmodP(0, 2));
        addTest(a1p2, a2p2, new ZmodP(1, 2));
        addTest(a2p2, a2p2, new ZmodP(0, 2));
        addTest(a3p2, a4p2, new ZmodP(1, 2));




    }

    void addTest(ZmodP a, ZmodP b, ZmodP expected) {

        ZmodP result = a.add(b);
        assertEquals(result.equals((expected)), 0);
    }

    @Test
    void sub() {

        subTest(a1p2, a1p2, new ZmodP(0, 2));
        subTest(a1p2, a2p2, new ZmodP(1, 2));
        subTest(a2p2, a2p2, new ZmodP(0, 2));
        subTest(a3p2, a4p2, new ZmodP(1, 2));

    }

    void subTest(ZmodP a, ZmodP b, ZmodP expected) {

        ZmodP result = a.sub(b);
        assertEquals(result.equals(expected), 0);

    }

    @Test
    void multiply() {


    }

    void multiplyTest(ZmodP a, ZmodP b, ZmodP expected) {

        ZmodP result = a.multiply(b);
        assertEquals(result.equals(expected), 0);

    }

    @Test
    void div() {

    }

    void divTest(ZmodP a, ZmodP b, ZmodP expected) {

        ZmodP result = a.div(b);
        assertEquals(result.equals(expected), 0);

    }

    @Test
    void remainder() {

    }

    void remainderTest(ZmodP a, ZmodP b, ZmodP expected) {

        ZmodP result = a.remainder(b);
        assertEquals(result.equals(expected), 0);

    }

    @Test
    void equals() {

    }

    void equalsTest(ZmodP a, ZmodP b, boolean expected) {

        assertEquals(a.equals(b) == expected, 0);

    }

    @Test
    void isPrime() {
        testPrime(2, true);
        testPrime(3, true);
        testPrime(4, false);
        testPrime(5, true);
        testPrime(6, false);
        testPrime(11, true);
        testPrime(51, false);
    }

    void testPrime(int i, boolean isPrime) {
        assertEquals(ZmodP.isPrime(i), isPrime);
    }

}