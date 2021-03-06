package Values;

/**
 * Authors:
 * Wessel van der Heijden - 0951686
 * Stefan Tanja - 0955022
 *
 *
 */
public class FiniteField {



    private ZmodP F;
    private Polynomial f;
    private Polynomial element; //element is in F/(f(X)), element may be null for addition and multiplication tables

    public FiniteField(Polynomial g, ZmodP field, Polynomial a) {
        f = g;
        F = field;
        element = a;
    }

    public int getOrder() {

        int order = F.getP();

        for (int i = 0; i < f.getDegree(); i++) {
            order *= order;
        }


        return order;
    }

    public int getClassCount() {
        return getOrder() / F.getP();
    }

    public Polynomial getModulus() {
        return f;
    }

    public ZmodP getField() {
        return F;
    }

    public Polynomial getElement() {
        return element;
    }




    public FiniteField add(FiniteField b) {
		
		//check if operations are allowed
        if (!(b.getModulus().equals(this.getModulus()) && b.getField().equals(this.getField()))) {

            throw new IllegalArgumentException("Elements are not part of the the same finite field");

        }

        Polynomial g = element.add(b.getElement());

        if (g.getDegree() >= f.getDegree()) {
            g = g.mod(f);
        }

        return new FiniteField(f, F, g);


    }

    public FiniteField multiply(FiniteField b) {
		
		//check if operations are allowed
        if (!(b.getModulus().equals(this.getModulus()) && b.getField().equals(this.getField()))) {
            throw new IllegalArgumentException("Elements are not part of the the same finite field");
        }

        Polynomial g = element.multiply(b.getElement());

        if (g.getDegree() >= f.getDegree()) {
            g = g.mod(f);
        }

        return new FiniteField(f, F, g);

    }

    public FiniteField qoutient(FiniteField b) {
		
		//check if operations are allowed
        if (!(b.getModulus().equals(this.getModulus()) && b.getField().equals(this.getField()))) {
            throw new IllegalArgumentException("Elements are not part of the the same finite field");
        }

        if(b != null) {

            ZmodP zero = new ZmodP(0, b.getField().getP());
            if (b.getElement().getDegree() == 0 && b.getElement().getMonomialAtIndex(0).equals(zero)) {

            }
        } else {
            //b == null
        }

        //get the inverse of b
        FiniteField bInverse;

        if(gcdIsConstant(b)) {
            //gcd(b, f) = 1
            Polynomial inverse = b.getElement().extendedEuclid(b.getModulus()).getP1();
            bInverse = new FiniteField(b.getModulus(), b.getField(), inverse);
            return multiply(bInverse);


        } else {
            //gcd(b, f) != 1
            //raise exception
            return null;
        }


    }



    private boolean gcdIsConstant(FiniteField b) {
		
		//check if operations are allowed
        if (!(b.getModulus().equals(this.getModulus()) && b.getField().equals(this.getField()))) {
            throw new IllegalArgumentException("Elements are not part of the the same finite field");
        }

        Polynomial p = b.getElement().euclid(b.getModulus());
        return p.getDegree() == 0;

    }

    /**
     * Returns all elements in the field
     *
     *
     * @return residue class ring of {@code this}
     */
    public Polynomial[] getClassRing(){
        int degree = this.f.getDegree();
        int mod = this.F.getP();
        int nrofElements = 1;
        for(int i=0; i<degree; i++){
            nrofElements *= mod;
        }
        Polynomial[] result = new Polynomial[nrofElements];
        for(int n=0; n<nrofElements; n++) {
            Polynomial poly = new Polynomial(new Monomial[]{},new ZmodP(0,this.getField().getP()));;
            for (int i = 0; i < degree; i++) {
                Monomial m = new Monomial(new ZmodP((int)(n / ((Math.pow((double)mod,(double)i)))%mod), F.getP()), i);
                poly = poly.add(new Polynomial(new Monomial[]{m}, F));
            }
            result[n] = poly;
        }
        return result;
    }

    /**
     *
     * @return addition table of {@code this}
     */
    public Polynomial[][] additionTable(){
        Polynomial[] elements = this.getClassRing();
        Polynomial[][] table = new Polynomial[elements.length][elements.length];
        for(int i=0; i<elements.length; i++){
            for(int j=0; j<elements.length; j++){
                FiniteField newField1 = new FiniteField(this.f,this.F,elements[i]);
                FiniteField newField2 = new FiniteField(this.f,this.F,elements[j]);
                FiniteField resultField = newField1.add(newField2);
                table[i][j] = resultField.getElement();
            }
        }
        return table;
    }

    /**
     *
     * @return multiplication table of {@code this}
     */
    public Polynomial[][] multiplicationTable(){
        Polynomial[] elements = this.getClassRing();
        Polynomial[][] table = new Polynomial[elements.length][elements.length];
        for(int i=0; i<elements.length; i++){
            for(int j=0; j<elements.length; j++){
                FiniteField newField1 = new FiniteField(this.f,this.F,elements[i]);
                FiniteField newField2 = new FiniteField(this.f,this.F,elements[j]);
                FiniteField resultField = newField1.multiply(newField2);
                table[i][j] = resultField.getElement();

            }
        }
        return table;
    }

    @Override
    public String toString() {
        return "(" + element + ") Z/" + F.getP() + " Z/(" + f + ")";
    }
}
