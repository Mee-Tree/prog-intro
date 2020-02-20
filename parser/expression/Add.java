package expression;

public class Add extends AbstractBinaryOperator {
    public Add(CommonExpression first, CommonExpression second) {
        super(first, second, 1);
    }

    protected int calculate(int a, int b) {
        return a + b;
    }

    public String toMiniString() {
        return toMiniString(" + ");
    }

    public String toString() {
        return toString(" + ");
    }
}
