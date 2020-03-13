package exceptionsExpression.exceptions;

public class DivisionByZeroException extends EvaluatingException {
    public DivisionByZeroException() {
        super("Division by zero.");
    }
}
