import expression.parser.ExpressionParser;
import expression.*;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        int x = 959929107;
        int y = -2140241999;
        int z = 1865460343;
        CommonExpression res = parser.parse("(((digits (y + z)) + (digits ((x + 168226166) << ((-861462423) / 447857512)))) << ((-1136513835) << (y * (-783152590))))");
        System.out.println(res.evaluate(x, y, z));
    }
}
