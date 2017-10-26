package Input.Operations.Poly;

import Input.Input;
import Input.IntTemplate;
import Values.Monomial;
import Values.Polynomial;
import Values.ZmodP;

public class Generate extends IntTemplate {
    public Generate(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/generate";
    }

    @Override
    public String getReturnValueDescription() {
        return "an irreducible polynomial .";
    }

    @Override
    public Polynomial calculate() {
        final ZmodP c = new ZmodP(1, 2);
        final Monomial[] m = {new Monomial(c, a)};
        final Polynomial p = new Polynomial(m, c);

        return p.generateIrreducible();
    }
}
