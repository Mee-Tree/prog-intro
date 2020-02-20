package expression;

public class Add extends AbstractBinaryOperator {
    public Add(PriorityExpression first, PriorityExpression second) {
        super(first, second, 1);
    }

    protected int calculate(int a, int b) {
        return a + b;
    }

    protected double calculate(double a, double b) {
        return a + b;
    }

    public String toMiniString() {
        return toMiniString(" + ");
    }

    public String toString() {
        return toString(" + ");
    }
}
