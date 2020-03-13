package parser;

public class ExpressionSource implements Source {
    private final String data;
    private int pos;

    public ExpressionSource(final String data) {
        this.data = data;
    }

    public boolean hasNext() {
        return pos < data.length();
    }

    public char next() {
        return data.charAt(pos++);
    }

    public ParsingException error(final String message) {
        return new ParsingException(pos + ": " + message);
    }
}
