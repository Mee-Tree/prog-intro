package expression;

public interface PriorityExpression extends Expression, DoubleExpression, TripleExpression {
    default int getPriority() {
        return 3;
    }
    default boolean isImportant() {
        return false;
    }
}
