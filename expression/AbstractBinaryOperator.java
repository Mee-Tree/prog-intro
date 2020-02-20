package expression;

public abstract class AbstractBinaryOperator implements PriorityExpression {
    protected final PriorityExpression first;
    protected final PriorityExpression second;
    protected final int priority;

    protected AbstractBinaryOperator(PriorityExpression first, PriorityExpression second, int priority) {
        this.first = first;
        this.second = second;
        this.priority = priority;
    }

    protected abstract int calculate(int a, int b);

    protected abstract double calculate(double a, double b);

    public int evaluate(int var) {
        return calculate(first.evaluate(var), second.evaluate(var));
    }

    public double evaluate(double var) {
        return calculate(first.evaluate(var), second.evaluate(var));
    }

    public int evaluate(int x, int y, int z) {
        return calculate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    public int getPriority() {
        return priority;
    }

    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            AbstractBinaryOperator that = (AbstractBinaryOperator) obj;
            return this.first.equals(that.first) && this.second.equals(that.second);
        }
        return false;
    }

    public int hashCode() {
        return 701 * first.hashCode() + 31 * second.hashCode() + toString().hashCode();
    }

    public String toString(String operation) {
        return "(" + first.toString() + operation + second.toString() + ")";
    }

    public String wrap(PriorityExpression expression, boolean brackets) {
        if (brackets) {
            return "(" + expression.toMiniString() + ")";
        }
        return expression.toMiniString();
    }

    private boolean checkPriority(PriorityExpression expression) {
        return priority > expression.getPriority();
    }

    private boolean checkImportant(PriorityExpression expression) {
        return priority == expression.getPriority() && (this.isImportant() || expression.isImportant());
    }

    public String toMiniString(String operation) {
        return wrap(first, checkPriority(first))
                + operation
                + wrap(second, checkPriority(second) || checkImportant(second));
    }
}
