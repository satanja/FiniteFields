package Values;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {
    Monomial m0 = new Monomial(new ZmodP(1,2),3);
    Monomial m1 = new Monomial(new ZmodP(2,2),4);
    Monomial m2 = new Monomial(new ZmodP(3,2),2);
    Monomial m3 = new Monomial(new ZmodP(5,2),1);
    Monomial m4 = new Monomial(new ZmodP(1,3),3);
    Monomial m5 = new Monomial(new ZmodP(5,2),2);
    Monomial m6 = new Monomial(new ZmodP(2,3),3);
    Monomial m7 = new Monomial(new ZmodP(2,5),2);
    Monomial m8 = new Monomial(new ZmodP(4,5),4);
    Monomial m9 = new Monomial(new ZmodP(6,5),2);
    Monomial m10 = new Monomial(new ZmodP(3,5),3);
    Monomial m11 = new Monomial(new ZmodP(3,5),0);

    Polynomial p1 = new Polynomial(new Monomial[]{m1,m2}, m1.getCoefficient());
    Polynomial p2 = new Polynomial(new Monomial[]{m2,m1}, m1.getCoefficient());
    Polynomial p3 = new Polynomial(new Monomial[]{m1,m3}, m1.getCoefficient());
    Polynomial p4 = new Polynomial(new Monomial[]{m6,m4}, m4.getCoefficient()); // Illegal, different mod.
    Polynomial p5 = new Polynomial(new Monomial[]{m1,m2,m3}, m1.getCoefficient());
    Polynomial p6 = new Polynomial(new Monomial[]{m3,m1,m2}, m1.getCoefficient());
    Polynomial p7 = new Polynomial(new Monomial[]{m1,m5}, m1.getCoefficient());
    Polynomial p8 = new Polynomial(new Monomial[]{m6}, m6.getCoefficient());
    Polynomial p9 = new Polynomial(new Monomial[]{m4}, m4.getCoefficient());
    Polynomial p10 = new Polynomial(new Monomial[]{m7,m8}, m7.getCoefficient());
    Polynomial p11 = new Polynomial(new Monomial[]{m9,m10}, m9.getCoefficient());
    Polynomial p12 = new Polynomial(new Monomial[]{m9,m10,m11}, m9.getCoefficient());



    void testAdd(Polynomial p, Polynomial q, Polynomial expected){
        assertTrue(p.add(q).equals(expected));
    }

    @org.junit.jupiter.api.Test
    void add() {
        //testAdd(p1,p2,new Polynomial(new Monomial[]{}, m1.getCoefficient()));
        testAdd(p1,p3,new Polynomial(new Monomial[]{m2,m3}, m2.getCoefficient()));
        testAdd(p1,p7, new Polynomial(new Monomial[]{}, m1.getCoefficient()));
        testAdd(p1,p1, new Polynomial(new Monomial[]{}, m1.getCoefficient()));
        testAdd(p9,p9, new Polynomial(new Monomial[]{m6}, m1.getCoefficient()));
        testAdd(p9,p9.add(p9), new Polynomial(new Monomial[]{}, m1.getCoefficient()));
        testAdd(p8,p8, new Polynomial(new Monomial[]{m4}, m1.getCoefficient()));
        testAdd(p9,p8, new Polynomial(new Monomial[]{}, m1.getCoefficient()));
        testAdd(p5,p1.add(p7), new Polynomial(new Monomial[]{m2,m3}, m1.getCoefficient()));
    }

    void testSub(Polynomial p, Polynomial q, Polynomial expected){
        assertTrue(p.sub(q).equals(expected));
    }
    Monomial ms4 = new Monomial(new ZmodP(5,11),1);
    Polynomial ps4 = new Polynomial(new Monomial[]{ms4}, ms4.getCoefficient());
    @org.junit.jupiter.api.Test
    void sub() {
        testSub(p1,p2,new Polynomial(new Monomial[]{}, m1.getCoefficient()));
        testSub(p1,p3,new Polynomial(new Monomial[]{m2,m3}, m2.getCoefficient()));
        testSub(ps4,ps4,new Polynomial(new Monomial[]{},ps4.getField()));
    }

    void testMultiply(Polynomial p, Polynomial q, Polynomial expected){
        assertTrue(p.multiply(q).equals(expected));
    }

    @org.junit.jupiter.api.Test
    void multiply() {
        Monomial mm0 = new Monomial(new ZmodP(1,2),0);
        Monomial mm1 = new Monomial(new ZmodP(1,2),1);
        Monomial mm2 = new Monomial(new ZmodP(1,2),2);
        Polynomial pm1 = new Polynomial(new Monomial[]{mm1,mm0}, mm1.getCoefficient());
        Polynomial pm2 = new Polynomial(new Monomial[]{mm2,mm0}, mm0.getCoefficient());
        testMultiply(pm1,pm1,pm2);
    }

    @org.junit.jupiter.api.Test
    void scarlarMultiply() {

    }

    void testEquals(Polynomial p, Polynomial q, boolean expected){
        assertTrue(p.equals(q) == expected);
    }

    @org.junit.jupiter.api.Test
    void equals() {
//        testEquals(p1, p1, true);
//        testEquals(p1, p2, true);
//        testEquals(p1, p3, false);
//        testEquals(p1, p5, false);
        testEquals(p5, p6, true);
    }

    void testLongDivision(Polynomial p, Polynomial q, PolyPair expected){
        assertTrue(p.longDivision(q).equals(expected));
    }

    Monomial ml0 = new Monomial(new ZmodP(1,2),0);
    Monomial ml1 = new Monomial(new ZmodP(1,2),1);
    Monomial ml2 = new Monomial(new ZmodP(1,2),2);
    Monomial ml3 = new Monomial(new ZmodP(1,2),3);
    Polynomial pl0 = new Polynomial(new Monomial[]{ml0,ml2}, ml0.getCoefficient());
    Polynomial pl1 = new Polynomial(new Monomial[]{ml0,ml3}, ml0.getCoefficient());
    Polynomial pl2 = new Polynomial(new Monomial[]{ml1}, ml1.getCoefficient());
    Polynomial pl3 = new Polynomial(new Monomial[]{ml0,ml1}, ml0.getCoefficient());

    Monomial ml4 = new Monomial(new ZmodP(5,11),1);
    Monomial ml5 = new Monomial(new ZmodP(1,11),0);
    Polynomial pl4 = new Polynomial(new Monomial[]{ml4}, ml4.getCoefficient());

    @org.junit.jupiter.api.Test
    void longDivision() {
        testLongDivision(pl1,pl0,new PolyPair(pl2,pl3));
        testLongDivision(pl1,pl1,new PolyPair(new Polynomial(new Monomial[]{ml0},pl1.getField()),new Polynomial(new Monomial[]{},pl1.getField())));
        testLongDivision(pl4,pl4,new PolyPair(new Polynomial(new Monomial[]{ml5},pl4.getField()),new Polynomial(new Monomial[]{},pl4.getField())));
    }

    void testEuclid(Polynomial p, Polynomial q, Polynomial expected){
        assertTrue(p.euclid(q).equals(expected));
    }

    @org.junit.jupiter.api.Test
    void euclid() {
        testEuclid(pl1,pl0,pl3);
    }

    void testExtendedEuclid(Polynomial p, Polynomial q, PolyPair expected){
        assertTrue(p.extendedEuclid(q).equals(expected));
    }

    @org.junit.jupiter.api.Test
    void extendedEuclid() {
        testExtendedEuclid(pl1,pl0,new PolyPair(new Polynomial(new Monomial[]{ml0},pl1.getField()),new Polynomial(new Monomial[]{ml1},pl0.getField())));
    }

    void testMod(Polynomial p, Polynomial q, Polynomial expected){
        assertTrue(p.mod(q).equals(expected));
    }

    @org.junit.jupiter.api.Test
    void mod() {
        Polynomial pm4 = new Polynomial(new Monomial[]{ml0,ml1,ml2}, ml0.getCoefficient());
        assertTrue(pl3.multiply(pl3).mod(pm4).equals(pl2));
    }

    void testEqualMod(Polynomial p, Polynomial q, Polynomial mod, boolean expected){
        assertTrue(p.equalMod(q, mod) == (expected));
    }

    @org.junit.jupiter.api.Test
    void equalMod() {

    }

    @Test
    void toStringTest() {
        testToString(p1, "X2 mod 2");
        testToString(p2, "X2 mod 2");
        testToString(p3, "X mod 2");
        testToString(p5, "X2 + X mod 2");
        testToString(p12, "X2 + 3X3 + 3 mod 5");
    }

    void testToString(Polynomial p, String e) {
        assertEquals(e, p.toString());
    }
}