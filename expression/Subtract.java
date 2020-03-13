package expression;

public class Subtract extends AbstractBinaryOperator {
    public Subtract(CommonExpression first, CommonExpression second) {
        super(first, second, 1);
    }

    protected int calculate(int a, int b) {
        return a - b;
    }

    protected double calculate(double a, double b) {
        return a - b;
    }

    public String toMiniString() {
        return toMiniString(" - ");
    }

    public String toString() {
        return toString(" - ");
    }

    public boolean isImportant() {
        return true;
    }
}
