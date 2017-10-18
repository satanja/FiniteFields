package Values;


import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {
    Monomial m1 = new Monomial(new ZmodP(2,2),4);
    Monomial m2 = new Monomial(new ZmodP(3,2),2);
    Monomial m3 = new Monomial(new ZmodP(5,2),1);
    Monomial m4 = new Monomial(new ZmodP(1,3),3);
    Monomial m5 = new Monomial(new ZmodP(5,2),2);
    Polynomial p1 = new Polynomial(new Monomial[]{m1,m2}, m1.getCoefficient());
    Polynomial p2 = new Polynomial(new Monomial[]{m2,m1}, m1.getCoefficient());
    Polynomial p3 = new Polynomial(new Monomial[]{m1,m3}, m1.getCoefficient());
    Polynomial p4 = new Polynomial(new Monomial[]{m2,m4}, m4.getCoefficient());
    Polynomial p5 = new Polynomial(new Monomial[]{m1,m2,m3}, m1.getCoefficient());
    Polynomial p6 = new Polynomial(new Monomial[]{m3,m1,m2}, m1.getCoefficient());
    Polynomial p7 = new Polynomial(new Monomial[]{m1,m5}, m1.getCoefficient());


    void testAdd(Polynomial p, Polynomial q, Polynomial expected){
        assertTrue(p.add(q).equals(expected));
    }

    @org.junit.jupiter.api.Test
    void add() {
        //testAdd(p1,p2,new Polynomial(new Monomial[]{}, m1.getCoefficient()));
        testAdd(p1,p3,new Polynomial(new Monomial[]{m2,m3}, m2.getCoefficient()));
        testAdd(p1,p7, new Polynomial(new Monomial[]{}, m1.getCoefficient()));
        testAdd(p1,p1, new Polynomial(new Monomial[]{}, m1.getCoefficient()));
        testAdd(p5,p1.add(p7), new Polynomial(new Monomial[]{m2,m3}, m1.getCoefficient()));
    }

    void testSub(Polynomial p, Polynomial q, Polynomial expected){
        assertTrue(p.sub(q).equals(expected));
    }
    @org.junit.jupiter.api.Test
    void sub() {
        testSub(p1,p2,new Polynomial(new Monomial[]{}, m1.getCoefficient()));
        testSub(p1,p3,new Polynomial(new Monomial[]{m2,m3}, m2.getCoefficient()));
    }

    void testMultiply(Polynomial p, Polynomial q, Polynomial expected){
        assertTrue(p.multiply(q).equals(expected));
    }

    @org.junit.jupiter.api.Test
    void multiply() {
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

    @org.junit.jupiter.api.Test
    void longDivision() {

    }

    void testEuclid(Polynomial p, Polynomial q, Polynomial expected){
        assertTrue(p.euclid(q).equals(expected));
    }

    @org.junit.jupiter.api.Test
    void euclid() {

    }

    void testExtendedEuclid(Polynomial p, Polynomial q, Polynomial expected){
        assertTrue(p.extendedEuclid(q).equals(expected));
    }

    @org.junit.jupiter.api.Test
    void extendedEuclid() {

    }

    void testMod(Polynomial p, Polynomial q, Polynomial expected){
        assertTrue(p.mod(q).equals(expected));
    }

    @org.junit.jupiter.api.Test
    void mod() {

    }

    void testEqualMod(Polynomial p, Polynomial q, Polynomial mod, boolean expected){
        assertTrue(p.equalMod(q, mod) == (expected));
    }

    @org.junit.jupiter.api.Test
    void equalMod() {

    }

}