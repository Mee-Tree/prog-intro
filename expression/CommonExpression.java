package expression;

public interface CommonExpression extends Expression, DoubleExpression, TripleExpression {
    default int getPriority() {
        return 3;
    }
    default boolean isImportant() {
        return false;
    }
}
