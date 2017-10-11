import java.lang.management.MonitorInfo;
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
    public ZmodP GetField() {

        return F;
    }
    //TODO: Contract
    public Polynomial add(Polynomial g) {

        Monomial[] monomialsg = g.getMonomials();

        List<Monomial> monomialsListh = new ArrayList<Monomial>();

        for (Monomial mf: monomials)
        {
            for (Monomial mg: monomialsg) {

                if (mf.getExponent() == mg.getExponent()) {

                    ZmodP cof = mf.getCoefficient().add(mg.getCoefficient());

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
    public boolean equals(Polynomial g, Polynomial h) {

//        if (g.getMonomials().length != monomials.length) {
//
//            return false;
//        }
//        // both polynomials have an equal number of monomials
//
//        for(int i = 0; i < monomials.length; i++) {
//
//            if(monomials[i].getExponent() != g.getMonomialAtIndex(i).getExponent()) {
//                return false;
//            }
//            //both monomials have the same exponent
//
//            if(!monomials[i].getCoefficient().equals(g.getMonomialAtIndex(i).getCoefficient())) {
//                return false;
//            }
//        }

        //both polynomials have an equal number of monomials, and for each monomial they are identical.
        return true;



    }

    //TODO: Contract
    private Monomial[] convertListToArray(List<Monomial> list) {
        Monomial[] h = new Monomial[list.size()];
        return  list.toArray(h);
    }
}
