import java.io.*;
import java.util.*;
  
public class CrossStitch {
    static List<Pair>[][][] stitches;
    static List<Pair> ans = new ArrayList<>();
 
    static void dfs(Pair v, int side) {
        List<Pair>[][] tmp = stitches[side];
        while (tmp[v.x][v.y].size() > 0) {
            int last = tmp[v.x][v.y].size() - 1; 
            Pair to = tmp[v.x][v.y].remove(last);
            dfs(to, side ^ 1);
            ans.add(v);
        }
    }

    static void connect(Pair v, Pair to, int side) {
        stitches[side][v.x][v.y].add(to);
        stitches[side][to.x][to.y].add(v);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
         
        int w = in.nextInt();
        int h = in.nextInt();
 
        stitches = new ArrayList[2][h + 1][w + 1];
        ans = new ArrayList<>();
 
        for (int i = 0; i < h + 1; ++i) {
            for (int j = 0; j < w + 1; ++j) {
                stitches[0][i][j] = new ArrayList<>();
                stitches[1][i][j] = new ArrayList<>();
            }
        }
 
        Pair start = new Pair(0, 0);
        for (int i = 0; i < h; ++i) {
            String line = in.next();
            for (int j = 0; j < w; ++j) {
                if (line.charAt(j) == 'X') {
                    start = new Pair(i, j);
                    connect(start, new Pair(i + 1, j), 0);
                    connect(new Pair(i, j + 1), new Pair(i + 1, j), 1);
                    connect(start, new Pair(i + 1, j + 1), 1);
                    connect(new Pair(i, j + 1), new Pair(i + 1, j + 1), 0);
                }
            }
        }
        dfs(start, 1);
        System.out.println(ans.size() - 1);
        for (int i = 0; i < ans.size(); ++i) {
            Pair tmp = ans.get(i);
            System.out.println(tmp.y + " " + tmp.x);
        }        
    }
    static class Pair {
        public int x;
        public int y;
 
        public Pair(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}