package Values;

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

}
