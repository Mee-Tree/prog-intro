import java.util.*;

public class TaskJ {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[][] paths = new int[n][n];
        for (int i = 0; i < n; ++i) {
            String str = in.next();
            for (int j = 0; j < n; ++j) {
                paths[i][j] = str.charAt(j) - '0';
            }
        }

        int[][] ans = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (paths[i][j] != 0) {
                    ans[i][j] = 1;
                    for (int k = j + 1; k < n; ++k) {
                        paths[i][k] -= paths[j][k];
                        paths[i][k] %= 10;
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }
    }
}
