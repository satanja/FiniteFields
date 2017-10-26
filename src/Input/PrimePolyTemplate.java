package Input;

import Values.FiniteField;
import Values.Monomial;
import Values.Polynomial;
import Values.ZmodP;

public abstract class PrimePolyTemplate implements OperationInterface {

    private Input input;
    protected int p;
    protected Polynomial q;
    protected FiniteField f;

    public PrimePolyTemplate(Input input) {
        this.input = input;
    }

    @Override
    public void execute() {
        input.printOutput("Expects a polynomial q and a prime integer p as input.");
        input.printOutput(getReturnDescription());

        p = input.readPrime("p");
        q = input.readPolynomial("q");

        ZmodP one = new ZmodP(1, p);
        Monomial[] monoOne = new Monomial[]{new Monomial(one, 0)};
        Polynomial q2 = new Polynomial(monoOne, one);
        f = new FiniteField(q, one, q2);

        try {
            input.printOutput(computationOutput()); // Print the output is calculation was successful.
        } catch (Exception e) {
            input.printOutput("FAILED: Computation failed, reason: '" + e.getMessage() + "'");
        }
    }

    public abstract String getCommand();

    @Override
    public String getReturnDescription() {
        return "Returns an " + getReturnValueDescription();
    }

    protected abstract String getReturnValueDescription();

    public abstract Object calculate();

    @Override
    public String computationOutput() {
        Polynomial[][] result = (Polynomial[][]) calculate();
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < result.length; i++) {
            s.append("|");
            for (int j = 0; j < result[i].length; j++) {
                s.append(result[i][j]);
                s.append("|");
            }

            s.append("\n");
        }

        return s.toString();
    }
}
