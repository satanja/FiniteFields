package Values;

import java.util.ArrayList;
import java.util.List;

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

    //TODO: Contract
    public Polynomial add(Polynomial g) {
        return add(g,false);
    }

    public Polynomial sub(Polynomial g){
        return add(g,true);
    }

    private Polynomial add(Polynomial g, boolean negative){
        Monomial[] monomialsg = g.getMonomials();

        List<Monomial> monomialsListh = new ArrayList<Monomial>();

        //Add monomials to this if they do not exist
        for(Monomial mg : monomialsg) {
            if(!this.hasExponent(mg)){
                monomialsListh.add(new Monomial(new ZmodP(0,mg.getCoefficient().getP()),mg.getExponent()));
            }
        }

        for (Monomial mf: monomials)
        {
            for (Monomial mg: monomialsg) {

                if (mf.getExponent() == mg.getExponent()) {
                    ZmodP cof;
                    if(negative){ //TODO: Test this
                        cof = mf.getCoefficient().sub(mg.getCoefficient());
                    } else {
                        cof = mf.getCoefficient().add(mg.getCoefficient());
                    }

                    if(cof.getValue() != 0) {
                        Monomial u = new Monomial(cof, mf.getExponent());
                        monomialsListh.add(u);
                    }

                    //A monomial with the same exponent only appears exactly one
                    break;
                }

            }
        }

        Monomial[] monomialsh = convertListToArray(monomialsListh);
        return new Polynomial(monomialsh, this.F);
    }


    //TODO: Contract
    public Polynomial multiply(Polynomial g) {

        Monomial[] monomialsg = g.getMonomials();
        List<Monomial> monomialsListh = new ArrayList<Monomial>();

        for (Monomial mf: monomials)
        {
            for (Monomial mg: monomialsg) {

                ZmodP cof = mf.getCoefficient().multiply(mg.getCoefficient());
                if(cof.getValue() != 0) {
                    Monomial u = new Monomial(cof, mf.getExponent() + mg.getExponent());
                    monomialsListh.add(u);
                }
            }
        }

        Monomial[] monomialsh = convertListToArray(monomialsListh);
        return new Polynomial(monomialsh, this.F);
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

    //TODO: Contract
    public boolean equals(Polynomial g) {

        if (g.getMonomials().length != monomials.length) {
            return false;
        }
        // both polynomials have an equal number of monomials

        for(int i = 0; i < monomials.length; i++) {
            if(monomials[i].getExponent() != g.getMonomialAtIndex(i).getExponent()) {
                return false;
            }
            //both monomials have the same exponent

            if(!monomials[i].getCoefficient().equals(g.getMonomialAtIndex(i).getCoefficient())) {
                return false;
            }
        }

        //both polynomials have an equal number of monomials, and for each monomial they are identical.
        return true;

    }

    public int getDegree(){
        int deg=0;
        for(Monomial m : this.monomials){
            if(m.getExponent() > deg){
                deg = m.getExponent();
            }
        }
        return deg;
    }

    public ZmodP getLc(){
        ZmodP coef = null;
        int deg = 0;
        for(Monomial m : this.monomials){
            if(m.getExponent() > deg){
                deg = m.getExponent();
                coef = m.getCoefficient();
            }
        }
        if(this.monomials == null || this.monomials.length < 1 || coef == null){
            throw new IllegalArgumentException("Values.Monomial does not contain coefficients");
        } else {
            return coef;
        }
    }

    /**
     *
     * @param b
     * @return
     * @throws IllegalArgumentException if {@code this.getField().getP() == b.getField().getP()}
     */
    public PolyPair longDivision(Polynomial b) throws IllegalArgumentException{
        if(this.getField().getP() != b.getField().getP()){
            throw new IllegalArgumentException("Not equal coefficient primes");
        }

        Polynomial q = new Polynomial(new Monomial[0], this.getField());
        Polynomial r = this;
        while(r.getDegree() >= b.getDegree()){
            // q
            ZmodP coef = new ZmodP(r.getLc().getValue()/b.getLc().getValue(), this.F.getP());
            int deg = r.getDegree()-b.getDegree();
            for(Monomial qm : q.getMonomials()){
                if(qm.getExponent() == deg){
                    qm.getCoefficient().add(coef);
                    break;
                }
            }
            // r
            coef.multiply(b.getField());
            for(Monomial qm : r.getMonomials()) {
                if (qm.getExponent() == deg) {
                    qm.getCoefficient().sub(coef);
                    break;
                }
            }
        }
        return new PolyPair(q,r);
    }

    public Polynomial euclid(Polynomial b){
        while(b.getMonomials().length>0){
            Polynomial r = longDivision(b).getP2();
            this.monomials = b.getMonomials();
            b = r;
        }
        return this;
    }

    public PolyPair extendedEuclid(Polynomial b){
        Polynomial x = new Polynomial(new Monomial[] {new Monomial(new ZmodP(1,this.getField().getP()),0)},this.getField());
        Polynomial v = new Polynomial(new Monomial[] {new Monomial(new ZmodP(1,this.getField().getP()),0)},this.getField());
        Polynomial y = new Polynomial(new Monomial[0],this.getField());
        Polynomial u = new Polynomial(new Monomial[0],this.getField());

        while(b.getMonomials().length>0){
            PolyPair qAndR = this.longDivision(b);
            Polynomial q = qAndR.getP1();
            this.monomials = b.getMonomials();
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

    public Polynomial mod(Polynomial mod){
        //Get the remainder from long division
        return this.longDivision(mod).getP2();
    }

    public boolean equalMod(Polynomial b, Polynomial mod){
        return this.mod(mod).equals(b.mod(mod));
    }


    //TODO: Contract
    private Monomial[] convertListToArray(List<Monomial> list) {
        Monomial[] h = new Monomial[list.size()];
        return  list.toArray(h);
    }
}
