package md2html;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2HtmlReader {
	private BufferedReader reader;
	private boolean EOF = false;

	public Md2HtmlReader(String fileName) {
		try {
            reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.err.println("Input error: " + e.getMessage());
        }
	}

	public boolean hasNextParagraph() {
		return !EOF;
	}

	public String nextParagraph() throws IOException {
		String line = reader.readLine();

		while (line != null && line.isEmpty()) {
			line = reader.readLine();
		}

		StringBuilder paragraph = new StringBuilder();
		while (line != null && !line.isEmpty()) {
			paragraph.append(line);
			line = reader.readLine();
			paragraph.append('\n');
		}		
		paragraph.deleteCharAt(paragraph.length() - 1);
		if (line == null) {
			EOF = true;
		}

		return paragraph.toString();
	}

	public void close() {
		try {
			reader.close();
		} catch(IOException e) {
			System.err.println("Reader cannot be closed: " + e.getMessage());
		}
	}
}
