package Input;

import Values.ZmodP;

public interface OperationInterface {
    String getCommand();

    String getReturnDescription();

    ZmodP calculate();

    void execute();
}
