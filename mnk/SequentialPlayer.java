package mnk;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
//        Board board = (Board) position;
//        board.makeMove(new Move(1, 1, Cell.X));
        for (int r = 0; r < position.getN(); r++) {
            for (int c = 0; c < position.getM(); c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
