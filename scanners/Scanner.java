import java.util.*;
import java.io.*;

public class Scanner implements AutoCloseable { 
    private static final int BUFFER_SIZE = 1024;

    private final Reader in; 
    private char[] buffer; 
    private int bufferPtr = 0;
    private int curSize = 0; 
    private String token = "";
    private boolean isWord = false;

    public Scanner() { 
        in = new InputStreamReader(System.in);
        buffer = new char[BUFFER_SIZE]; 
    } 
    public Scanner(File file) throws IOException { 
        in = new InputStreamReader(new FileInputStream(file), "UTF-8"); 
        buffer = new char[BUFFER_SIZE]; 
    } 
    public Scanner(String str) throws IOException { 
        in = new StringReader(str);
        buffer = new char[BUFFER_SIZE]; 
    }

    private boolean isWordSeparator(int c) {
    	return !(Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
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
        if (bufferPtr == curSize) {
            read();
        }
        return (curSize != -1);
    }

    public boolean hasNextInt() throws IOException {
        if (!hasNext()) { 
            return false;
        }
        return isInteger(token);
    }

    public boolean hasNextWord() throws IOException {
    	isWord = true;
    	if (!hasNext()) { 
            return false;
        }
        return true;
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
            if (isWord && isWordSeparator(c) || !isWord && Character.isWhitespace(c)) {
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

    public String nextWord() throws IOException {
    	if (hasNextWord()) {	
    		String tmp = token;
            token = "";
            isWord = false;
            return tmp;
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
        curSize = in.read(buffer, bufferPtr = 0, BUFFER_SIZE);  
    }

    public void close() { 
        try{
            in.close();
        } catch (IOException e) {
            System.err.println("Scanner cannot be closed: " + e.getMessage());
        } 
    }
} 