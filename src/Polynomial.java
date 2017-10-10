

public class Polynomial {

    private Field F;
    private Monomial[] monomials;

    public Polynomial(Monomial[] m,  Field F) {
        monomials = m;
        this.F = F;
    }

    public Monomial getMonomialAtIndex(int i) {
        return monomials[i];
    }

    public Monomial[] getMonomials() {
        return monomials;
    }

    public Field GetField() {
        return F;
    }


}
