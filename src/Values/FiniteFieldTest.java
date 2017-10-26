package Values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FiniteFieldTest {
    @Test
    void getOrder() {
    }

    @Test
    void getModulus() {
    }

    @Test
    void add() {
    }

    @Test
    void multiply() {
    }

    @Test
    void qoutient() {
    }

    void testGetElements(FiniteField f, Polynomial[] p){
        Polynomial[] result = f.getClassRing();
        for(int i=0; i<result.length; i++){
            assertEquals(result[i],p[i]);
        }
    }

    Monomial m0 = new Monomial(new ZmodP(1,2),0);
    Monomial m1 = new Monomial(new ZmodP(1,2),1);
    Monomial m2 = new Monomial(new ZmodP(1,2),2);
    Monomial m3 = new Monomial(new ZmodP(1,2),3);
    Polynomial p00 = new Polynomial(new Monomial[]{},new ZmodP(0,2));
    Polynomial p01 = new Polynomial(new Monomial[]{m0},new ZmodP(0,2));
    Polynomial p10 = new Polynomial(new Monomial[]{m1},new ZmodP(0,2));
    Polynomial p11 = p10.add(new Polynomial(new Monomial[]{m0},new ZmodP(0,2)));
    Polynomial p20 = new Polynomial(new Monomial[]{m2},new ZmodP(0,2));
    Polynomial p21 = p20.add(p01);
    Polynomial p22 = p20.add(p10);
    Polynomial p23 = p22.add(p01);
    Polynomial p37 = (new Polynomial(new Monomial[]{m3},new ZmodP(0,2))).add(p23);
    FiniteField f0 = new FiniteField(p23,new ZmodP(0,2),p23);
    FiniteField f1 = new FiniteField(p37,new ZmodP(0,2),p37);

    @Test
    void getElements() {


        testGetElements(f0,new Polynomial[]{p00,p01,p10,p11});
        testGetElements(f1,new Polynomial[]{p00,p01,p10,p11,p20,p21,p22,p23});
    }

    void testAdditionTable(FiniteField f, Polynomial[][] p){
        Polynomial[][] result = f.additionTable();
        for(int i=0; i<result.length; i++){
            for(int j=0; j<result[i].length; j++) {
                assertEquals(result[i][j], p[i][j]);
            }
        }
    }

    @Test
    void additionTable() {
        Polynomial[][] addTable = new Polynomial[][]{
                {p00,p01,p10,p11},
                {p01,p00,p11,p10},
                {p10,p11,p00,p01},
                {p11,p10,p01,p00}
        };
        testAdditionTable(f0,addTable);
    }

    void testMultiplicationTable(FiniteField f, Polynomial[][] p){
        Polynomial[][] result = f.multiplicationTable();
        for(int i=0; i<result.length; i++){
            for(int j=0; j<result[i].length; j++) {
                assertEquals(result[i][j], p[i][j]);
            }
        }
    }

    @Test
    void multiplicationTable() {
        Polynomial[][] multTable = new Polynomial[][]{
                {p00,p00,p00,p00},
                {p00,p01,p10,p11},
                {p00,p10,p11,p01},
                {p00,p11,p01,p10}
        };
        testMultiplicationTable(f0,multTable);
    }

}