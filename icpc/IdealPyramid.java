import java.io.*;
import java.util.*;
 
public class IdealPyramid {
    public static void main(String[] args) {
        final int MAX = 100_000_000 + 1;

		Scanner in = new Scanner(System.in);
        
		int n = in.nextInt();
        int minX = MAX, maxX = -MAX;
        int minY = MAX, maxY = -MAX;
        for (int i = 0; i < n; ++i) {
            int x = in.nextInt();
            int y = in.nextInt();
            int h = in.nextInt();
            minX = Math.min(minX, x - h);
            maxX = Math.max(maxX, x + h);
            minY = Math.min(minY, y - h);
            maxY = Math.max(maxY, y + h);
        }
        int x = (minX + maxX) / 2;
        int y = (minY + maxY) / 2;
        int h = (Math.max(maxX - minX, maxY- minY) + 1) / 2;
        
        System.out.println(x + " " + y + " " + h);
    }
}