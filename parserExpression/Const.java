package parserExpression;

public class Const implements CommonExpression {
    private final Number number;

    public Const(Number number) {
        this.number = number;
    }

    public int evaluate(int var) {
        return number.intValue();
    }

    public int evaluate(int x, int y, int z) {
        return number.intValue();
    }

    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            Const that = (Const) obj;
            return this.number.equals(that.number);
        }
        return false;
    }

    public int hashCode() {
        return number.hashCode();
    }

    public String toString() {
        return number.toString();
    }
}
