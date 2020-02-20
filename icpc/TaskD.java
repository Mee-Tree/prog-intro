import java.util.*;
import java.io.*;

public class TaskD {
    static final int MOD = 998_244_353; 

    static long binPow(long base, int exp) {
        long res = 1;
        while (exp > 0) {
            if (exp % 2 > 0) {
                res *= base;
                res %= MOD;
            }
            base *= base;
            base %= MOD;
            exp /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        long k = in.nextInt();

        long[] r = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                r[i] = (i / 2) * (k + 1) * binPow(k, i / 2) % MOD;
            } else {
                r[i] =  i * binPow(k, (i + 1) / 2) % MOD;
            }
        }

        long[] d = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            d[i] = r[i];
            for (int j = 1; j * j <= i; ++j) {
                if (i % j == 0 && i != 1) {
                    long tmp = i / j * d[j] % MOD;
                    d[i] += MOD - tmp;
                    d[i] %= MOD;
                    if (j != 1 && j * j != i) {
                        tmp = j * d[i / j] % MOD;
                        d[i] += MOD - tmp;
                        d[i] %= MOD;
                    }
                }
            }
        }

        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j * j <= i; ++j) {
                if (i % j == 0) {
                    ans += d[j];
                    ans %= MOD;
                    if (j * j != i) {
                        ans += d[i / j];
                        ans %= MOD;
                    }
                }
            }
        }
        out.println(ans);
        out.close();
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
