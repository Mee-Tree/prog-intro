package scanners;

import java.util.NoSuchElementException;
import java.io.*;

public class FastScanner implements AutoCloseable { 
    private static final int BUFFER_SIZE = 1024; 

    private final DataInputStream in;
    private byte[] buffer;
    private int bufferPtr = 0;
    private int bytesRead = 0; 
    private String token = "";
  
    public FastScanner() { 
        in = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE]; 
    } 
    public FastScanner(File file) throws IOException { 
        in = new DataInputStream(new FileInputStream(file)); 
        buffer = new byte[BUFFER_SIZE]; 
    } 
    public FastScanner(String str) throws IOException { 
        in = new DataInputStream(new ByteArrayInputStream(str.getBytes())); 
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

    private boolean hasNextChar() throws IOException {
        if (bufferPtr == bytesRead) {
            read();
        }
        return (bytesRead != -1);
    }

    public boolean hasNextInt() throws IOException {
        if (!hasNext()) { 
            return false;
        }
        return isInteger(token);
    }

    public boolean hasNextLine() throws IOException {
        return hasNextChar();
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
        while ((c = nextChar()) != -1) {
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

    private int nextChar() throws IOException {
        if (!hasNextChar()) {
            return -1;
        }
        return buffer[bufferPtr++];
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
        while ((c = nextChar()) != -1) { 
            if (c == '\n') {
                break; 
            }
            str.append((char) c); 
        } 
        return str.toString();
    }

    private void read() throws IOException { 
        bytesRead = in.read(buffer, bufferPtr = 0, BUFFER_SIZE);  
    }

    public void close() { 
        try{
            in.close();
        } catch (IOException e) {
            System.err.println("Scanner cannot be closed: " + e.getMessage());
        } 
    }
} 