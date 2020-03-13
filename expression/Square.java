package expression;

public class Square extends AbstractUnaryOperator {
    public Square(CommonExpression expression) {
        super(expression);
    }

    protected double calculate(double a) {
        return a * a;
    }

    protected int calculate(int a) {
        return a * a;
    }

    public String toString() {
        return toString("square ");
    }

    public boolean isImportant() {
        return true;
    }
}
