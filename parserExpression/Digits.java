package parserExpression;

public class Digits extends AbstractUnaryOperator {
    public Digits(CommonExpression expression) {
        super(expression);
    }

    protected int calculate(int a) {
        int sum = 0;
        while (a != 0) {
            sum += a % 10;
            a /= 10;
        }
        return sum > 0 ? sum : -sum;
    }

    public String toString() {
        return toString("digits ");
    }

    public boolean isImportant() {
        return true;
    }
}
