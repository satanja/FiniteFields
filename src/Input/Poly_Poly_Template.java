package Input;

import Values.Polynomial;

public abstract class Poly_Poly_Template implements OperationInterface {

    private Input input;
    protected Polynomial a;
    protected Polynomial b;

    public Poly_Poly_Template(Input input) {
        this.input = input;
    }

    @Override
    public void execute() {
        System.out.println(input.getOutputPrefix() + "Expects two polynomials a and b as input.");
        System.out.println(input.getOutputPrefix() + getReturnDescription());

        a = input.readPolynomial("a");
        b = input.readPolynomial("b");

        try {
            Polynomial result = calculate();
            System.out.println(input.getOutputPrefix() + result.toString()); // Print the output is calculation was successful.
        } catch (Exception e) {
            System.out.println(input.getOutputPrefix() + "FAILED: Computation failed, reason: '" + e.getMessage() + "'");
        }
    }

    public abstract String getCommand();

    @Override
    public String getReturnDescription() {
        return "Returns a polynomial with value " + getReturnValueDescription();
    }

    protected abstract String getReturnValueDescription();

    public abstract Polynomial calculate();
}
