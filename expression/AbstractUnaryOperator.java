package expression;

public abstract class AbstractUnaryOperator implements CommonExpression {
    protected final CommonExpression expression;

    protected AbstractUnaryOperator(CommonExpression expression) {
        this.expression = expression;
    }

    protected abstract int calculate(int a);

    protected abstract double calculate(double a);

    public int evaluate(int var) {
        return calculate(expression.evaluate(var));
    }

    public double evaluate(double var) {
        return calculate(expression.evaluate(var));
    }

    public int evaluate(int x, int y, int z) {
        return calculate(expression.evaluate(x, y, z));
    }

    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            AbstractUnaryOperator that = (AbstractUnaryOperator) obj;
            return this.expression.equals(that.expression);
        }
        return false;
    }

    public int hashCode() {
        return 701 * expression.hashCode() + toString().hashCode();
    }

    public String toString(String operation) {
        return "(" + operation + expression.toString() + ")";
    }
}
