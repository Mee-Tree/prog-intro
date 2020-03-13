import java.io.*;
import java.util.*;
 
public class ManagingDifficulties {
    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        
		int t = in.nextInt();
		while (t > 0) {
        	int n = in.nextInt();
        	int[] a = new int[n];
        	for (int i = 0; i < n; ++i) {
        		a[i] = in.nextInt();
        	}
        	Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        	int res = 0;
        	for (int j = n - 1; j > 0; --j) {
        		for (int i = 0; i < j; ++i) {
                    int ak = 2 * a[j] - a[i];
                    res += cnt.getOrDefault(ak, 0);
                }
                cnt.put(a[j], cnt.getOrDefault(a[j], 0) + 1);
        	}
        	--t;
        	System.out.println(res);
		}
    }
}