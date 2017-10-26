package Values;
/**
 * Authors:
 * Wessel van der Heijden - 0951686
 *
 *
 *
 */
public class PolyPair {
    private  Polynomial p1;
    private  Polynomial p2;

    public PolyPair(Polynomial q, Polynomial r) {
        this.p1 = q;
        this.p2 = r;
    }

    //Also Quotient
    public Polynomial getP1() {
        return p1;
    }

    //Also remainder
    public Polynomial getP2() {
        return p2;
    }

    public boolean equals(PolyPair pp){
        return pp.getP1().equals(p1) && pp.getP2().equals(p2);
    }

}
