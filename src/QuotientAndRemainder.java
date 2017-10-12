public class QuotientAndRemainder {
    private  Polynomial quotient;
    private  Polynomial remainder;

    public QuotientAndRemainder(Polynomial q, Polynomial r) {
        this.quotient = q;
        this.remainder = r;
    }

    public Polynomial getQuotient() {
        return quotient;
    }

    public Polynomial getRemainder() {
        return remainder;
    }

}
