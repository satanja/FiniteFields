package Values;

/**
 * Created by s152124 on 10-10-2017.
 */
public class Monomial implements Comparable<Monomial> {

    private  ZmodP coefficient;
    private  int exponent;

    public Monomial(ZmodP c, int e) {
        coefficient = c;
        exponent = e;
    }

    public ZmodP getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    @Override
    public int compareTo(Monomial o) {
        return getExponent() - o.getExponent();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Monomial) {
            Monomial m = (Monomial) obj;

            if (!coefficient.equals(m.getCoefficient())) {
                return false;
            }

            if (exponent != m.getExponent()) {
                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
