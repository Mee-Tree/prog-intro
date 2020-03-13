package parserExpression.parser;

import parserExpression.*;
import java.util.Map;

public class ExpressionParser extends AbstractParser {
    private final int MIN_PRIORITY = 0;
    private final int MAX_PRIORITY = 3;
    private Token current;
    private int number;
    private String var;

    private static final Map<Token, Integer> PRIORITIES = Map.of(
            Token.LEFT,    0,
            Token.RIGHT,   0,
            Token.ADD,     1,
            Token.SUB,     1,
            Token.MUL,     2,
            Token.DIV,     2
    );

    public CommonExpression parse(final String expression) {
        super.source = new ExpressionSource(expression);
        nextChar();
        current = Token.BEGIN;
        return parseExpression(MIN_PRIORITY);
    }

    private void getNextToken() {
        skipWhitespace();
        if (test('\0')) {
            current = Token.END;
        } else if (test('*')) {
            current = Token.MUL;
        } else if (test('/')) {
            current = Token.DIV;
        } else if (test('+')) {
            current = Token.ADD;
        } else if (test('(')) {
            current = Token.OPEN;
        } else if (test(')')) {
            current = Token.CLOSE;
        } else if (test('<')) {
            expect('<');
            current = Token.LEFT;
        } else if (test('>')) {
            expect('>');
            current = Token.RIGHT;
        } else if (test('a')) {
            expect("bs");
            current = Token.ABS;
        } else if (test('s')) {
            expect("quare");
            current = Token.SQUARE;
        } else if (test('r')) {
            expect("everse");
            current = Token.REVERSE;
        } else if (test('d')) {
            expect("igits");
            current = Token.DIGITS;
        }  else if (test('-')) {
            if (current == Token.CLOSE || current == Token.CONST || current == Token.VARIABLE) {
                current = Token.SUB;
            } else {
                current = Token.MINUS;
            }
        } else if (between('x', 'z')) {
            current = Token.VARIABLE;
            var = String.valueOf(ch);
            nextChar();
        } else {
            current = Token.CONST;
            parseNumber();
        }
    }

    private void parseNumber() {
        if (!between('0', '9')) {
            throw error("Incorrect format");
        }
        number = 0;
        while (between('0', '9')) {
            number = 10 * number + (ch - '0');
            nextChar();
        }
    }

    private boolean checkPriority(int priority) {
        return PRIORITIES.getOrDefault(current, MAX_PRIORITY) == priority;
    }

    private CommonExpression parseExpression(int priority) {
        if (priority == MAX_PRIORITY) {
            return getMaxPriorityOperation();
        }
        CommonExpression result = parseExpression(priority + 1);
        while (checkPriority(priority)) {
            result = getOperation(current, result, parseExpression(priority + 1));
        }
        return result;
    }

    private CommonExpression getMaxPriorityOperation() {
        CommonExpression result;
        getNextToken();

        if (current == Token.OPEN) {
            result = parseExpression(MIN_PRIORITY);
            getNextToken();
        } else if (current == Token.CONST) {
            result = new Const(number);
            getNextToken();
        } else if (current == Token.VARIABLE) {
            result = new Variable(var);
            getNextToken();
        } else if (current == Token.MINUS) {
            result = new Minus(getMaxPriorityOperation());
        } else if (current == Token.ABS) {
            result = new Abs(getMaxPriorityOperation());
        } else if (current == Token.SQUARE) {
            result = new Square(getMaxPriorityOperation());
        } else if (current == Token.REVERSE) {
            result = new Reverse(getMaxPriorityOperation());
        } else if (current == Token.DIGITS) {
            result = new Digits(getMaxPriorityOperation());
        } else {
            result = new Const(0);
        }
        return result;
    }

    private CommonExpression getOperation(Token current, CommonExpression result, CommonExpression expression) {
        if (current == Token.LEFT) {
            result = new LeftShift(result, expression);
        } else if (current == Token.RIGHT) {
            result = new RightShift(result, expression);
        } else if (current == Token.ADD) {
            result = new Add(result, expression);
        } else if (current == Token.SUB) {
            result = new Subtract(result, expression);
        } else if (current == Token.MUL) {
            result = new Multiply(result, expression);
        } else if (current == Token.DIV) {
            result = new Divide(result, expression);
        }
        return result;
    }

    private void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }
}
