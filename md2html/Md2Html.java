package md2html;

import java.util.*;
import java.io.*;

public class Md2Html {
	public static void main(String[] args) {
		Md2HtmlReader source = new Md2HtmlReader(args[0]); 
		Md2HtmlParser parser = new Md2HtmlParser(args[1]);
		parser.parse(source);
		source.close();
	}
}
