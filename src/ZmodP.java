public class ZmodP {

    private int p;
    private int[] classes;

    public ZmodP(int k) {

        if (isPrime(k)) {
            this.p = k;
            ConstructClasses();
        }

    }


    private boolean isPrime(int k) {

        return true;
    }

    private void ConstructClasses() {

        classes = new int[p - 1];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = i;
        }
    }

    public int mod(int num){
        return num%p;
    }
}