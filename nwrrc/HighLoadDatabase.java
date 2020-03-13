import java.util.*;
import java.io.*;

public class HighLoadDatabase {
    public static void main(String[] args) {
        Scanner in = new Scanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int maxA = 0;
        int[] sum = new int[n + 1];

        for (int i = 0; i < n; ++i) {
            int a = in.nextInt();
            sum[i + 1] = sum[i] + a;
            maxA = Math.max(maxA, a);
        }

        int q = in.nextInt();
        int[] ans = new int[sum[n] + 1];

        for (int i = 0; i < q; ++i) {
            int t = in.nextInt();

            if (t < maxA) {
                out.println("Impossible");
                continue;
            } else if (ans[t] > 0) { 
                out.println(ans[t]);
            } else {
                int cnt = 0;
                int prev = 0;

                while (prev < n) {
                    int left = prev, right = n;

                    while (left + 1 < right) {
                        int mid = (left + right) / 2;
                        
                        if (sum[mid + 1] - sum[prev] <= t) {
                            left = mid;
                        } else {
                            right = mid;
                        }
                    }
                    prev = right;
                    cnt++;
                }
                ans[t] = cnt;
                out.println(ans[t]);
            }
        }
        out.close();
    }
    static class Scanner implements AutoCloseable { 
        private static final int BUFFER_SIZE = 1024;

        final private DataInputStream in; 
        private byte[] buffer; 
        private int bufferPtr = 0;
        private int curSize = 0; 
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
            if (bufferPtr == curSize) {
                read();
            }
            return (curSize != -1);
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

        private void readToken(){
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
            } else {
                throw new NoSuchElementException();
            }
        }

        private void read() { 
            try {
                curSize = in.read(buffer, bufferPtr = 0, BUFFER_SIZE);    
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
