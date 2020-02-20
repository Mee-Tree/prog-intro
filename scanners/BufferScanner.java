import java.util.*;
import java.io.*;

public class BufferScanner implements AutoCloseable { 
    private static final int BUFFER_SIZE = 1024; 

    private final Reader in; 
    private String token = "";
  
    public BufferScanner() { 
        in = new BufferedReader(new InputStreamReader(System.in));
    } 
    public BufferScanner(File file) throws IOException { 
        in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
    } 
    public BufferScanner(String str) throws IOException { 
        in = new BufferedReader(new InputStreamReader(new StringBufferInputStream(str)));
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean hasNextInt() throws IOException {
        if (!hasNext()) { 
            return false;
        }
        return isInteger(token);
    }

    //              to CHANGE
    public boolean hasNextLine() throws IOException { 
        return in.ready();
    }

    private boolean hasNext() throws IOException {
        if (token.length() == 0) {
            readToken();
        } 
        return (token.length() > 0);
    }

    private void readToken() throws IOException {
        StringBuilder str = new StringBuilder();
        int c;
        while ((c = in.read()) != -1) {
            if (Character.isWhitespace(c) || c == '\n') {
                if (str.length() > 0) { 
                    break;
                }
                continue;
            }
            str.append((char) c);
        }
        token = str.toString();
    }

    public int nextInt() throws IOException {
        if (hasNextInt()) {
            String tmp = token;
            token = "";
            return Integer.parseInt(tmp);
        } else {
            throw new NoSuchElementException();
        }
    }
    
    public String nextLine() throws IOException { 
        StringBuilder str = new StringBuilder(); 
        int c; 
        while ((c = in.read()) != -1) { 
            if (c == '\n') {
                break; 
            }
            str.append((char) c); 
        } 
        return str.toString();
    }

    public void close() { 
        try{
            in.close();
        } catch (IOException e) {
            System.err.println("Scanner cannot be closed: " + e.getMessage());
        } 
    }
} 