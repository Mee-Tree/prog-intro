import java.io.*;
import java.util.*;
 
public class BadTreap {
    public static void main(String[] args) {
		final int PERIOD = 710;

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = -n / 2; i < n - n / 2; ++i) {
        	System.out.println(i * PERIOD);
        }
    }
}