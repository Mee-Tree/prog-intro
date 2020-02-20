package expression;

public class Multiply extends AbstractBinaryOperator {
    public Multiply(CommonExpression first, CommonExpression second) {
        super(first, second, 2);
    }

    protected int calculate(int a, int b) {
        return a * b;
    }

    public String toMiniString() {
        return toMiniString(" * ");
    }

    public String toString() {
        return toString(" * ");
    }
}

