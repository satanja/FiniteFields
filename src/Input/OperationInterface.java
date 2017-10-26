package Input;

public interface OperationInterface {
    String getCommand();

    String getReturnDescription();

    Object calculate();

    void execute();

    String computationOutput();
}
