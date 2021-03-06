package Values;

import Values.Exceptions.PValuesNotEqualException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Authors:
 * Wessel van der Heijden - 0951686
 * Stefan Tanja - 0955022
 *
 *
 */
public class Polynomial  {

    private ZmodP F;
    private Monomial[] monomials;

    public Polynomial(Monomial[] m, ZmodP F) {
        monomials = m;
        this.F = F;
    }

    //TODO: Contract
    public Monomial getMonomialAtIndex(int i) {
        return monomials[i];
    }

    //TODO: Contract
    public Monomial[] getMonomials() {
        return monomials;
    }

    //TODO: Contract
    public ZmodP getField() {
        return F;
    }

    public boolean hasExponent(Monomial m){
        for(Monomial thisM : this.getMonomials()){
            if(thisM.getExponent() == m.getExponent()){
                return true;
            }
        }
        return false;
    }

    public boolean hasMonomial(Monomial m) {
        for(Monomial thisM : this.getMonomials()){

            if(thisM.getExponent() == m.getExponent() && thisM.getCoefficient() == m.getCoefficient()){
                return true;
            }
        }
        return false;

    }


    public Polynomial add(Polynomial g) {
        return add(g,false);
    }

    public Polynomial sub(Polynomial g){
        return add(g,true);
    }

    /** Add or subtract two polynomials
     *
     * @param g
     * @param negative
     * @pre {@code g.getField().getP() == this.getField().getP()} &&
     * {@code \forall i,j in this with i!=j: i.degree != j.degree} &&
     * {@code \forall i,j in g with i!=j: i.degree != j.degree}
     * @throws PValuesNotEqualException if {@code g.getField().getP() != this.getField().getP()}
     * @return if negative then {@code this-g} else {@code this+g}
     */
    private Polynomial add(Polynomial g, boolean negative){
        if(g.getField().getP() != this.getField().getP()){
            throw new PValuesNotEqualException("p value should be equal: ("+g.getField().getP()+","+this.getField().getP()+")");
        }

        Monomial[] monomialsg = g.getMonomials();

        List<Monomial> monomialsListh = new ArrayList<Monomial>();

        //Add everything in {@Code this}. If the element is not in g, add the element as it is in {@Code this}.
        for(Monomial m : monomials){
            if(!g.hasExponent(m) && m.getCoefficient().getValue() != 0){
                monomialsListh.add(m);
            } else {
                for (Monomial n : monomialsg) {
                    if (m.getExponent() == n.getExponent()) {
                        ZmodP value;
                        if(negative){ //Is it addition or subtraction?
                            value = m.getCoefficient().sub(n.getCoefficient());
                        } else {
                            value = m.getCoefficient().add(n.getCoefficient());
                        }
                        if (value.getValue() != 0) {// Do not add Monomial if its value is 0
                            monomialsListh.add(new Monomial(value, m.getExponent()));
                        }
                        break;
                    }
                }
            }
        }

        //Add all elements which were not added yet (having an exponent which is not in {@Code this}).
        for(Monomial n : monomialsg){
            if(!this.hasExponent(n) && n.getCoefficient().getValue() != 0){
                if(negative){
                    ZmodP zero = new ZmodP(0,n.getCoefficient().getP());
                    ZmodP value = zero.sub(n.getCoefficient());
                    monomialsListh.add(new Monomial(value,n.getExponent()));
                } else {
                    monomialsListh.add(n);
                }
            }
        }

        Monomial[] monomialsh = convertListToArray(monomialsListh);
        return new Polynomial(monomialsh, this.F);
    }


    //TODO: Contract
    public Polynomial multiply(Polynomial g) {

        Polynomial result = new Polynomial(new Monomial[]{},this.getField());
        Monomial[] monomialsg = g.getMonomials();

        for (Monomial mf: monomials)
        {
            for (Monomial mg: monomialsg) {
                ZmodP cof = mf.getCoefficient().multiply(mg.getCoefficient());
                if(cof.getValue() != 0) {
                    Monomial u = new Monomial(cof, mf.getExponent() + mg.getExponent());
                    result = result.add(new Polynomial(new Monomial[]{u},this.getField()));
                }
            }
        }

        return result;
    }

    //TODO: Contract
    public Polynomial scarlarMultiply(int c) {
        // c mod p
        ZmodP cof = new ZmodP(c, F.getP());
        List<Monomial> monomialsListh = new ArrayList<Monomial>();

        // (a * (c mod p)) mod p
        // given (a mod p) = a,
        // (a * (c mod p)) mod p == (ac) mod p
        for(int i = 0; i < monomials.length; i++) {
             cof =  monomials[i].getCoefficient().multiply(cof);
             if(cof.getValue() != 0) {
                 Monomial u = new Monomial(cof, monomials[i].getExponent());
                 monomialsListh.add(u);
             }
        }
        Monomial[] monomialsh = convertListToArray(monomialsListh);
        return new Polynomial(monomialsh, this.F);
    }

    /**
     *
     * @param obj
     * @return true iff obj is the same object as this,
     * or object is an instanceof with
     * <ul>
     *     <li>obj has an equal number of monomials compared to this</li>
     *     <li>for every pair of monomials of this and obj, if the exponent is the same then the coefficient is also the same</li>
     *     <li>obj has the same hashcode as this</li>
     * </ul>
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Polynomial) {
            Polynomial g = (Polynomial) obj; //down cast


            if (g.getField().getP() != getField().getP()) {
                return false;
            }

            if (g.getMonomials().length == 0 && monomials.length == 0) {
                //both are the zero Polynomial
                return true;
            } else if (g.getMonomials().length == 0 && monomials.length > 0) {
                //one is the zero polynomial but the other isn't
                return false;
            } else if (g.getMonomials().length >= 0 && monomials.length == 0) {
                //one is the zero polynomial but the other isn't
                return  false;
            }

            //both polynomials have at least one monomial

           
            g = g.sort();
            Polynomial f = this.sort();

            for (Monomial m: g.getMonomials()) {

                if (!f.hasMonomial(m) && m.getCoefficient().getValue() != 0) {
                    return false;
                }

            } //all monomials in g are in f

            for (Monomial m: f.getMonomials()) {

                if (!g.hasMonomial(m) && m.getCoefficient().getValue() != 0) {
                    return false;
                }

            } //all monomials in f are in g

            //both polynomials are equal

            if(this.hashCode() != g.hashCode()) {
                return false;
            }
            //same hashcode, therefore obj is equal to this
            return true;
        }
        return false;
    }

    /**
     * Gets the highest exponent of a monomial of this polynomial, aka the degree of this
     * @return the degree of this
     */
    public int getDegree(){
        int deg = 0;
        for(Monomial m : this.monomials){
            if(m.getExponent() > deg && m.getCoefficient().getValue() != 0){
                deg = m.getExponent();
            }
        }
        return deg;
    }

    /**
     * Gets the leading coefficient of this polynomial
     * @return the leading coefficient of this polynomial
     */
    public ZmodP getLc(){


        ZmodP coef = null;
        int deg = -1;
        for(Monomial m : this.monomials){

            if(m.getExponent() > deg){

                deg = m.getExponent();

                if(m.getCoefficient().getValue() != 0) {
                    coef = m.getCoefficient();
                }
            }
        }

        if(this.monomials == null) {
            throw new IllegalArgumentException("monomials is null");
        }

        if (coef == null && this.monomials.length < 1) {

            return new ZmodP(0, this.getField().getP());

        } else {

            return coef;
        }

    }

    /**
     * Perform Long division on two Polynomials
     *
     * @param b polynomial
     * @pre {@code this.getDegree() >= b.getDegree() && this.getField().getP() == b.getField().getP() && this.lc % b.lc == 0}
     * @return PolyPair(q,r) where q is the quotient and r is the remainder
     * @throws Values.Exceptions.PNotPrimeException if {@code this.getField().getP() != b.getField().getP()}
     * @throws IllegalArgumentException if {@code this.getDegree() < b.getDegree()}
     * @throws IllegalArgumentException if {@code this.lc % b.lc != 0}
     */
    public PolyPair longDivision(Polynomial b) throws IllegalArgumentException{
        if(this.getField().getP() != b.getField().getP()){
            throw new Values.Exceptions.PNotPrimeException("Not equal coefficient primes");
        } else if(this.getDegree() < b.getDegree()){
            throw new IllegalArgumentException("'this'.degree is smaller than b.degree: ("+this.getDegree()+"<"+b.getDegree()+")");
        } else if((this.getLc().getValue()%b.getLc().getValue()) != 0){
            throw new IllegalArgumentException("Leading coefficients are not divisible");
        }

        Polynomial q = new Polynomial(new Monomial[0], this.getField());
        Polynomial r = this;

        while(r.getDegree() >= b.getDegree()){
            ZmodP coef1 = new ZmodP(r.getLc().getValue(), this.F.getP());
            ZmodP coef2 = new ZmodP(b.getLc().getValue(), this.F.getP());
            ZmodP coef = coef1.div(coef2);
            int deg = r.getDegree() - b.getDegree();

            Polynomial s = new Polynomial(new Monomial[]{new Monomial(coef, deg)}, b.getField());

            q = q.add(s);
            Polynomial temp = b.multiply(s);
            r = r.sub(temp);

            //r can be empty
            //this means r = 0
            //so q does increase the next iteration, and r should not decrease
            //which means we are done
            if(r.getMonomials().length == 0) {
                break;
            } else if (r.getDegree() == 0 && r.getMonomialAtIndex(0).getCoefficient().getValue() == 0) {
                break;
            }
        }
        return new PolyPair(q,r);
    }

    /**
     * Calculate the gcd by the euclidean algorithm
     *
     * @param b Polynomial
     * @pre {@code this.getField().getP() == b.getField().getP()}
     * @throws Values.Exceptions.PNotPrimeException if {@code this.getField().getP() != b.getField().getP()}
     * @return gcd(this,b)
     */
    public Polynomial euclid(Polynomial b){
        if(this.getField().getP() != b.getField().getP()){
            throw new Values.Exceptions.PNotPrimeException("Not equal coefficient primes");
        }
        Polynomial a = this;
        //Monomial[] mons = new Monomial[]{};
        Polynomial q;

        while(b.getDegree() != 0 || b.getMonomialAtIndex(0).getCoefficient().getValue() != 0){

            Polynomial r = a.longDivision(b).getP2();
            a = b;
            b = r;

            if (b == null || b.getMonomials().length <= 0) {
                break;
            }
            // b's monomials can be indexed


        }
        return a;
    }

    /**
     *
     * @param b Polynomial
     * @pre {@code this.getField().getP() == b.getField().getP()}
     * @throws Values.Exceptions.PNotPrimeException if {@code this.getField().getP() != b.getField().getP()}
     * @return PolyPair(x,y) where gcd(this,b) = this*x+b*y
     */
    public PolyPair extendedEuclid(Polynomial b){
        if(this.getField().getP() != b.getField().getP()){
            throw new Values.Exceptions.PNotPrimeException("Not equal coefficient primes");
        }
        Polynomial x = new Polynomial(new Monomial[] {new Monomial(new ZmodP(1,this.getField().getP()),0)},this.getField());
        Polynomial v = new Polynomial(new Monomial[] {new Monomial(new ZmodP(1,this.getField().getP()),0)},this.getField());
        Polynomial y = new Polynomial(new Monomial[0],this.getField());
        Polynomial u = new Polynomial(new Monomial[0],this.getField());
        Polynomial thisObject = this;

        while(b.getMonomials().length>0){
            PolyPair qAndR = thisObject.longDivision(b);
            Polynomial q = qAndR.getP1();
            thisObject = new Polynomial(b.getMonomials(),thisObject.getField());
            b = qAndR.getP2();
            Polynomial xPrime = x;
            Polynomial yPrime = y;
            x = u;
            y = v;
            u = xPrime.sub(q.multiply(u));
            v = yPrime.sub(q.multiply(v));
        }
        return new PolyPair(x,y);
    }

    /**
     *
     * @param m
     * @pre {@code this.getField().getP() == m.getField().getP()}
     * @throws Values.Exceptions.PNotPrimeException if {@code this.getField().getP() != m.getField().getP()}
     * @return Polynomial p for which p == {@code this (mod m)}
     */
    public Polynomial mod(Polynomial m){
        if(this.getField().getP() != m.getField().getP()){
            throw new Values.Exceptions.PNotPrimeException("Not equal coefficient primes");
        }
        //Get the remainder from long division
        if(this.getDegree() >= m.getDegree()) {
            return this.longDivision(m).getP2();
        } else {
            return this;
        }
    }

    /**
     *
     * @param b
     * @param m
     * @pre {@code this.getField().getP() == b.getField().getP() && b.getField().getP() == m.getField().getP()}
     * @throws Values.Exceptions.PNotPrimeException if {@code !(this.getField().getP() == b.getField().getP() && b.getField().getP() == m.getField().getP())}
     * @return boolean {@code this (mod m) == b (mod m)}
     */
    public boolean equalMod(Polynomial b, Polynomial m){
        if(!(this.getField().getP() == b.getField().getP() && b.getField().getP() == m.getField().getP())){
            throw new Values.Exceptions.PNotPrimeException("Not equal coefficient primes");
        }
        return this.mod(m).equals(b.mod(m));
    }


    //TODO: Contract
    private Monomial[] convertListToArray(List<Monomial> list) {
        Monomial[] h = new Monomial[list.size()];
        return  list.toArray(h);
    }

    /**
     * Sorts the monomial list of the inputted polynomial in ascending order based on exponent
     *
     * @return sorts the monomials in this in ascending order based on exponent
     */
    public Polynomial sort() {


        Monomial[] result = this.getMonomials();

        //insertion sort
        for (int i = 1; i < this.getMonomials().length; i++) {

            for(int j = i; j > 0; j--) {

                if(result[j-1].getExponent() > result[j].getExponent()) {
                    Monomial temp = result[j];
                    result[j] = result[j - 1];
                    result[j - 1] = temp;
                }

            }

        }

        return new Polynomial(result, this.getField());

    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        Monomial[] monomials = getMonomials();

        boolean first = true;
        for(Monomial m : monomials) {
            ZmodP zmodp = m.getCoefficient();
            int c = zmodp.getValue();
            int e = m.getExponent();

            // Skip this monomial if its coefficient is zero anyway.
            if (c == 0) {
                continue; // Zero factor.
            }

            // Split the coefficients with a plus.
            if (first) {
                first = false;
            } else {
                b.append("+ ");
            }

            //draw if c is the constant term, or if c != 1
            if(e == 0 || c != 1) {
                b.append(c);
            }

            // Append the exponent.
            if (e == 0) { // Do not print X if the exponent is zero.
                b.append(" ");
            } else if (e == 1) { // Do not print the exponent if it is one.
                b.append("X ");
            } else {
                b.append("X").append(e).append(" ");
            }
        }

        // Ensure that at least something prior to the mod is printed.
        if (b.length() == 0) {
            b.append("0 ");
        }

        // Add the modulo part.
        int p = getField().getP();
        b.append("mod ").append(p);

        return b.toString();
    }

    /**
     * Returns whether this is an irreducible polynomial (using algorithm 4.1.4)
     * @return
     */
    public boolean isIrreducible() {
        int t = 0;
        ZmodP one = new ZmodP(1, F.getP());
        ZmodP negOne = new ZmodP (-1, F.getP());
        Monomial[] monos = new Monomial[2];
        Polynomial g;
        Polynomial h;

        do {
            t++;
            monos[0] = new Monomial(one, power(F.getP(), t));
            monos[1] = new Monomial(negOne, 1);
            g = new Polynomial(monos, F);

            if (g.getDegree() >= this.getDegree()) {
                h = g.euclid(this);
            } else {
                h = this.euclid(g);
            }


        } while(h.getDegree() == 0 && t < this.getDegree() + 1); //a unit returns degree 0, which means the gcd(this, g) = 1
        return t == this.getDegree();
    }

    /**
     * Returns an irreducible polynomial of {@code this.getDegree()}
     * @return
     */
    public Polynomial generateIrreducible() {

        //get the desired degree for the irreducible polynomial
        int deg = this.getDegree();
        Polynomial g;

        ZmodP coef;
        Random rand = new Random();


        do {

            //reset the monomials
            List<Monomial> monomialsList = new ArrayList<Monomial>();


            //make a this.getDegree() order monomial
            coef = new ZmodP(rand.nextInt(F.getP() - 1) + 1, F.getP());
            monomialsList.add(new Monomial(coef, deg));



            for (int i = deg - 1; i >= 0; i--) {

                //generate a random coefficient
                coef = new ZmodP(rand.nextInt(F.getP()), F.getP());


                //only add if coefficient is not 0
                if (coef.getValue() != 0) {

                    monomialsList.add(new Monomial(coef, i));

                }


            }

            Monomial[] monos = convertListToArray(monomialsList);
            //create new polynomial
            g = new Polynomial(monos, F);

        } while (!g.isIrreducible()); //test irreducibility

        //g is irreducible

        return g;

    }


    /**
     * Returns q^t
     * @param q
     * @param t
     * @return
     */

    private int power(int q, int t) {
        int p = 1;

        for(int i = 0; i < t; i++) {
            p *= q;
        }

        return p;
    }
}
