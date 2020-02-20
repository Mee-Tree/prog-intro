package mnk;

public class SecurePosition implements Position {
    private final Position position;

    public SecurePosition(final Position position) {
        this.position = position;
    }

    public boolean isValid(final Move move) {
        return position.isValid(move);
    }

    public Cell getCell(final int r, final int c) {
        return position.getCell(r, c);
    }

    public int getN() {
        return position.getN();
    }

    public int getM() {
        return position.getM();
    }

    public String toString() {
        return position.toString();
    }
}
