package Input.Operations.PrimePoly;

import Input.Input;
import Input.PrimePolyTemplate;
import Values.Polynomial;

public class MultiplicationTable extends PrimePolyTemplate {
    public MultiplicationTable(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/multable";
    }

    @Override
    public String getReturnValueDescription() {
        return "multiplication table table";
    }

    @Override
    public Polynomial[][] calculate() {
        return f.additionTable();
    }
}
