package expression;

public class Minus extends AbstractUnaryOperator {
    public Minus(CommonExpression expression) {
        super(expression);
    }

    protected double calculate(double a) {
        return -a;
    }

    protected int calculate(int a) {
        return -a;
    }

    public String toString() {
        return toString("-");
    }

    public boolean isImportant() {
        return true;
    }
}
