package expression;

public class Reverse extends AbstractUnaryOperator {
    public Reverse(CommonExpression expression) {
        super(expression);
    }

    protected int calculate(int a) {
        int rev = 0;
        while (a != 0) {
            rev = 10 * rev + (a % 10);
            a /= 10;
        }
        return rev;
    }

    public String toString() {
        return toString("reverse ");
    }

    public boolean isImportant() {
        return true;
    }
}
