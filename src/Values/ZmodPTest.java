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

    }

    @Test
    void multiply() {

    }

    @Test
    void div() {

    }

    @Test
    void remainder() {

    }

    @Test
    void equals() {

    }

}