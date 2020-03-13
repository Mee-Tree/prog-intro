package expression;

public class Digits extends AbstractUnaryOperator {
    public Digits(CommonExpression expression) {
        super(expression);
    }

    protected double calculate(double a) {
        String str = Double.toString(a);
        double sum = 0;
        for (int i = 0; i < str.length(); ++i) {
        	if (str.charAt(i) != '.') {
        		sum += (str.charAt(i) - '0');
        	}
        }
        return sum;
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
