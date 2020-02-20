package mnk;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Position {
    boolean isValid(Move move);
    Cell getCell(int r, int c);
    String toString();
    int getN();
    int getM();
}
