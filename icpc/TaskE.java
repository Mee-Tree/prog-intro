import java.util.*;
import java.io.*;

public class TaskE {
    static IntList[] g;
    static int[] depths;
    static int[] prev;

    static void dfs(int v, int depth) {
        depths[v] = depth;
        for (int i = 0; i < g[v].size(); ++i) {
            int to = g[v].get(i);
            if (prev[v] != to) {
                prev[to] = v;
                dfs(to, depth + 1);
            }
        }
    }

    static void reset(int n) {
        prev = new int[n];
        for (int i = 0; i < n; ++i) {
            prev[i] = -1;
        }
        depths = new int[n];
    }
    public static void main(String[] args) {
        Scanner in = new Scanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        g = new IntList[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new IntList();
        }

        for (int i = 0; i < n - 1; ++i) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            g[v].put(u);
            g[u].put(v);
        }

        int[] c = new int[m];
        for (int i = 0; i < m; ++i) {
            c[i] = in.nextInt() - 1;
        }

        reset(n);
        dfs(c[0], 0);

        int maxDepth = 0;
        int ver = c[0];
        for (int i = 0; i < m; ++i) {
            if (depths[c[i]] > maxDepth) {
                maxDepth = depths[c[i]];
                ver = c[i];
            }
        }
        for (int i = 0; 2 * i < maxDepth; ++i) {
            ver = prev[ver];
        }

        reset(n);
        dfs(ver, 0);

        boolean isFound = true;
        for (int i = 0; i < m; ++i) {
            if (2 * depths[c[i]] != maxDepth) {
                isFound = false;
            }
        }
        if (isFound) {
            out.println("YES");
            out.println(ver + 1);
        } else {
            out.println("NO");
        }
        out.close();
    }
    static class IntList {
        private static final int SIZE = 4;

        private int[] arr = new int[SIZE];
        private int arrPtr = 0;

        public void put(int el) {
            if (arrPtr == arr.length) {
                arr = Arrays.copyOf(arr, 2 * arr.length);
            }
            arr[arrPtr++] = el;
        }
        public int get(int i) {
            return arr[i];
        }
        public int size() {
            return arrPtr;
        }
    }
    static class Scanner implements AutoCloseable { 
        final private int BUFFER_SIZE = 1024; 

        final private DataInputStream in;
        private byte[] buffer;
        private int bufferPtr = 0, bytesRead = 0; 
        private String token = "";
      
        public Scanner() { 
            in = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE]; 
        } 

        private boolean isInteger(String s) {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }

        private boolean hasNextChar() {
            if (bufferPtr == bytesRead) {
                read();
            }
            return (bytesRead != -1);
        }

        public boolean hasNextInt() {
            if (!hasNext()) { 
                return false;
            }
            return isInteger(token);
        }

        private boolean hasNext() {
            if (token.length() == 0) {
                readToken();
            } 
            return (token.length() > 0);
        }

        private void readToken() {
            StringBuilder str = new StringBuilder();
            int c;
            while ((c = nextChar()) != -1) {
                if (Character.isWhitespace(c)) {
                    if (str.length() > 0) { 
                        break;
                    }
                    continue;
                }
                str.append((char) c);
            }
            token = str.toString();
        }

        private int nextChar() {
            if (!hasNextChar()) {
                return -1;
            }
            return buffer[bufferPtr++];
        }

        public int nextInt() {
            if (hasNextInt()) {
                String tmp = token;
                token = "";
                return Integer.parseInt(tmp);
            }
            throw new NoSuchElementException();
        }

        private void read() { 
            try {
                bytesRead = in.read(buffer, bufferPtr = 0, BUFFER_SIZE);
            } catch(IOException e) {
                System.err.println(e.getMessage());
            }  
        }

        public void close() { 
            try{
                in.close();
            } catch (IOException e) {
                System.err.println("Scanner cannot be closed: " + e.getMessage());
            } 
        }
    }
}
