package expression;

public class Multiply extends AbstractBinaryOperator {
    public Multiply(PriorityExpression first, PriorityExpression second) {
        super(first, second, 2);
    }

    protected int calculate(int a, int b) {
        return a * b;
    }

    protected double calculate(double a, double b) {
        return a * b;
    }

    public String toMiniString() {
        return toMiniString(" * ");
    }

    public String toString() {
        return toString(" * ");
    }
}

