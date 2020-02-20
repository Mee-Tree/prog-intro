package mnk;

import javafx.scene.layout.ColumnConstraints;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            Move move = readMove(cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Invalid move. Please enter two integer numbers");
            out.println("Row should be from 0 to " + position.getN());
            out.println("Column should be from 0 to " + position.getM());
        }
    }

    private Move readMove(Cell cell) {
        final Move invalidMove = new Move(-1, -1, cell);
        int row = -1, column = -1;
        Scanner line = new Scanner(in.nextLine());
        try {
            row = line.nextInt();
            column = line.nextInt();
        } catch (NoSuchElementException | NumberFormatException e) {
            return invalidMove;
        }
        if (line.hasNext()) {
            return invalidMove;
        }
        return new Move(row, column, cell);
    }
}
