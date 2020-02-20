package mnk;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.V, '|',
            Cell.H, '-',
            Cell.E, '.'
    );

    private static final Cell[] playerSymbol = {
            Cell.X, Cell.O, Cell.V, Cell.H
    };

    private final Cell[][] cells;

    private final int numberOfPlayers;
    private final int k;
    private int emptyCells;
    private int ind;
    private Cell turn;

    public MNKBoard(int n, int m, int k, int numberOfPlayers, int gameNo) {
        this.numberOfPlayers = numberOfPlayers;
        this.cells = new Cell[n][m];
        this.k = k;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        emptyCells = m * n;
        ind = gameNo % numberOfPlayers;
        turn = playerSymbol[ind];
    }

    @Override
    public Position getPosition() {
        return new SecurePosition(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        int r = move.getRow();
        int c = move.getColumn();

        cells[r][c] = move.getValue();
        emptyCells--;

        int horizontal = count(r, c, 0, 1) + count(r, c, 0, -1) - 1;
        if (horizontal >= k) {
            return Result.WIN;
        }

        for (int deltaC = -1; deltaC <= 1; ++deltaC) {
            if (count(r, c, 1, deltaC) + count(r, c, -1, -deltaC) - 1 >= k) {
                return Result.WIN;
            }
        }

        if (emptyCells == 0) {
            return Result.DRAW;
        }

        ind = (ind + 1) % numberOfPlayers;
        turn = playerSymbol[ind];
        return Result.UNKNOWN;
    }

    private int count(int r, int c, int deltaR, int deltaC) {
        if (0 <= r && r < getN() && 0 <= c && c < getM() && cells[r][c] == turn) {
            return 1 + count(r + deltaR, c + deltaC, deltaR, deltaC);
        }
        return 0;
    }

    @Override
    public int getN() {
        return cells.length;
    }

    @Override
    public int getM() {
        return cells[0].length;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < getN()
                && 0 <= move.getColumn() && move.getColumn() < getM()
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < getM(); ++i) {
            sb.append(i);
        }
        for (int r = 0; r < getN(); r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < getM(); c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
