package expression;

public class Abs extends AbstractUnaryOperator {
    public Abs(CommonExpression expression) {
        super(expression);
    }

    protected int calculate(int a) {
        return a < 0 ? -a : a;
    }

    public String toString() {
        return toString("abs ");
    }

    public boolean isImportant() {
        return true;
    }
}
