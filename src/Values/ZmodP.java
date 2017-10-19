package Values;

import Values.Exceptions.PNotPrimeException;
import Values.Exceptions.PValuesNotEqualException;

/*
 * An immutable value object for objects in Z mod p.
 */
public class ZmodP {

    private int p;
    private int value;
    private int[] classes;

    public ZmodP(int value, int p) {
        if (! isPrime(p)) {
            throw new PNotPrimeException("p should be prime");
        }
        if (value >= p || value < 0) {
            value = value % p;
        }

        this.p = p;
        this.value = value;
    }

    public int getP() {
        return p;
    }

    public int getValue() {
        return value;
    }

    /**
     * Adds the value of two Values.ZmodP objects.
     *
     * @param b The value to be added to the value of {@code this}.
     * @return A new Values.ZmodP object with the combined value of {@code this} and {@code b}.
     */
    public ZmodP add(ZmodP b) {
        if (b.getP() != getP()) {
            throw new PValuesNotEqualException("p value should be equal");
        }

        int newValue = getValue() + b.getValue();

        if (newValue >= p) {
            newValue -= p;
        }

        return new ZmodP(newValue, p);
    }

    /**
     * Subtracts the value of two Values.ZmodP objects.
     *
     * @param b The value to be subtracted from the value of {@code this}.
     * @return A new Values.ZmodP object with the value of {@code b} subtracted from the value of {@code this}.
     */
    public ZmodP sub(ZmodP b) {
        if (b.getP() != getP()) {
            throw new PValuesNotEqualException("p value should be equal");
        }

        int newValue = getValue() - b.getValue();

        if (newValue < 0) {
            newValue += p;
        }

        return new ZmodP(newValue, p);
    }

    //TODO: Contract
    public ZmodP multiply(ZmodP b) {
        if (b.getP() != getP()) {
            throw new PValuesNotEqualException("p value should be equal");
        }

        int newValue = (getValue() * b.getValue()) % p; // @todo Replace with a more efficient method.

        return new ZmodP(newValue, p);
    }

    //TODO: Contract
    public ZmodP div(ZmodP b) {
        if (b.getP() != getP()) {
            throw new PValuesNotEqualException("p value should be equal");
        }

        int newValue = getValue() / b.getValue(); // @todo Replace with a more efficient method.

        return new ZmodP(newValue, p);
    }

    //TODO: Contract
    public ZmodP remainder(ZmodP b) {
        if (b.getP() != getP()) {
            throw new PValuesNotEqualException("p value should be equal");
        }

        int newValue = getValue() % b.getValue(); // @todo Replace with a more efficient method.

        return new ZmodP(newValue, p);
    }

    /**
     *
     * @param obj object to compare equals against
     * @return  {@code (this == obj || obj.getP() == this.p &&  obj.getValue - this.value = kp)}
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj instanceof ZmodP) {

            ZmodP b = (ZmodP) obj;
            boolean answer = false;

            if(b.getP() == p) {
                //same prime

                int diff = value - b.getValue();
                if(diff % p == 0) {
                    //value - b.getValue() is a multiple of p =>
                    //value is congruent with b.getValue() =>
                    //b.getValue() is in the same class as value, and so they are equal
                    answer = true;
                }
            }
            return answer;
        }

        return false;


    }

    /**
     *
     * @return 0
     */
    public int hashCode() {
        return 0;
    }

    /**
     * Checks if n is prime.
     *
     * @param n The value to be checked.
     * @return True if n is prime, else false.
     */
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        } else if (n <= 3) {
            return true;
        } else if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }

            i += 6;
        }

        return true;
    }

    private void ConstructClasses() {

        classes = new int[p - 1];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = i;
        }
    }

    @Override
    public String toString() {
        return getValue() + " mod " + getP();
    }
}