package Values;

/**
 * Created by s152124 on 10-10-2017.
 */
public class Monomial {

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

}
