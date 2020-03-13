package parserExpression;

public class LeftShift extends AbstractBinaryOperator {
    public LeftShift(CommonExpression first, CommonExpression second) {
        super(first, second, 0);
    }

    protected int calculate(int a, int b) {
        return a << b;
    }

    public String toMiniString() {
        return toMiniString(" << ");
    }

    public String toString() {
        return toString(" << ");
    }

    public boolean isImportant() {
        return false;
    }
}
