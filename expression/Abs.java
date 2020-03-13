package expression;

public class Abs extends AbstractUnaryOperator {
    public Abs(CommonExpression expression) {
        super(expression);
    }

    protected double calculate(double a) {
        return a < 0.0 ? -a : a;
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
