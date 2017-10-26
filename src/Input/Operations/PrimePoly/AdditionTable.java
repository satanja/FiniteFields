package Input.Operations.PrimePoly;

import Input.Input;
import Input.PrimePolyTemplate;
import Values.Polynomial;

public class AdditionTable extends PrimePolyTemplate {
    public AdditionTable(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/addtable";
    }

    @Override
    public String getReturnValueDescription() {
        return "addition table";
    }

    @Override
    public Polynomial[][] calculate() {
        return f.additionTable();
    }
}
