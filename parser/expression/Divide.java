package expression;

public class Divide extends AbstractBinaryOperator {
    public Divide(CommonExpression first, CommonExpression second) {
        super(first, second, 2);
    }

    protected int calculate(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    public String toMiniString() {
        return toMiniString(" / ");
    }

    public String toString() {
        return toString(" / ");
    }

    public boolean isImportant() {
        return true;
    }
}
