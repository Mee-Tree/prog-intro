package exceptionsExpression.parser;

import exceptionsExpression.TripleExpression;
import exceptionsExpression.exceptions.ParsingException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser {
    TripleExpression parse(String expression) throws ParsingException;
}