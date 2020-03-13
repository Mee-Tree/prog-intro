package parserExpression;

public interface CommonExpression extends Expression, TripleExpression {
    default int getPriority() {
        return 3;
    }
    default boolean isImportant() {
        return false;
    }
}
