package exceptionsExpression.exceptions;
import exceptionsExpression.parser.Token;

public class MissingArgumentException extends ParsingException {
    public MissingArgumentException(String message, boolean after) {
        super("An argument is missing " + (after ? "after" : "before") + ":\t" + message);
    }
}
