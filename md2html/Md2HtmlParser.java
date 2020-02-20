package md2html;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class Md2HtmlParser {
	private static final Map<Character, String> SPECIALS = new HashMap<>();
	private static final Map<String, String> HTMLSYMBOLS = new HashMap<>();
	private static final Set<String> MDSYMBOLS = new LinkedHashSet<>();

	private BufferedWriter writer;
	private String paragraph;

	static {
		SPECIALS.put('<', "&lt;");
		SPECIALS.put('>', "&gt;");
		SPECIALS.put('&', "&amp;");

		MDSYMBOLS.add("**");
		MDSYMBOLS.add("__");
		MDSYMBOLS.add("--");
		MDSYMBOLS.add("*");
		MDSYMBOLS.add("_");
		MDSYMBOLS.add("`");
		MDSYMBOLS.add("[");

		HTMLSYMBOLS.put("*", "em");
		HTMLSYMBOLS.put("_", "em");
		HTMLSYMBOLS.put("**", "strong");
		HTMLSYMBOLS.put("__", "strong");
		HTMLSYMBOLS.put("`", "code");
		HTMLSYMBOLS.put("--", "s");
		HTMLSYMBOLS.put("]", "a");
	}

	public Md2HtmlParser(String fileName) {
		try {
            writer = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.err.println("Error with opening or creating file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Output error: " + e.getMessage());
        }
	}

	public void parse(Md2HtmlReader source) {
		try {
			try {
				while (source.hasNextParagraph()) {
					paragraph = source.nextParagraph();
					if (paragraph.isEmpty()) {
						break;
					}
					writer.write(parseParagraph());
				}
			} finally {
				writer.close();
			}
		} catch (IOException e) {
            System.err.println("Output error: " + e.getMessage());
        }
	}

	private int countSharps() {
		int index = 0;
		while (index < paragraph.length() && paragraph.charAt(index) == '#') {
			index++;
		}
		if (!Character.isWhitespace(paragraph.charAt(index))) {
			index = 0;
		}
		return index;
	}

	private String parseParagraph() {
		StringBuilder parsedParagraph = new StringBuilder();
		int ind = countSharps();		
		if (ind > 0) {
			parsedParagraph.append("<h").append(ind).append(">");
			parsedParagraph.append(parseText(ind + 1, null).str);
			parsedParagraph.append("</h").append(ind).append(">");
		} else {
			parsedParagraph.append("<p>");
			parsedParagraph.append(parseText(ind, null).str);
			parsedParagraph.append("</p>");
		}

		return parsedParagraph.append('\n').toString();
	}

	private StringAndLastIndex parseText(int ind, String markupTag) {
		StringBuilder parsedText = new StringBuilder();

		while (ind < paragraph.length()) {
			if (ind > 0 && paragraph.charAt(ind - 1) == '\\') {
                parsedText.append(backslashSymbols(ind));
            } else if (checkTag(ind, markupTag)) {
            	StringBuilder sb = new StringBuilder();

            	sb.append("<" ).append(changeMarkup(markupTag));
            	if (markupTag.equals("]")) {
            		ind += 2;
	            	StringBuilder link = new StringBuilder();
	            	while (paragraph.charAt(ind) != ')') {
	            		link.append(paragraph.charAt(ind));
	            		ind++;
	            	}
	            	sb.append(" href=\'").append(link).append("\'");
            	}
            	sb.append(">").append(parsedText);
            	sb.append("</").append(changeMarkup(markupTag)).append(">");

            	return new StringAndLastIndex(sb.toString(), ind + markupTag.length() - 1);
            } else if (getMarkupTag(ind) != null) {
            	String tag = getMarkupTag(ind);
            	StringAndLastIndex parsed = parseText(ind + tag.length(), tag);
            	parsedText.append(parsed.str);
            	ind = parsed.ind;
            } else if (paragraph.charAt(ind) != '\\') {
				parsedText.append(toHtmlSymbols(ind));
			}
			ind++;
		}
		StringBuilder sb = new StringBuilder();
		if (markupTag != null) {
			sb.append(markupTag);
		}
		sb.append(parsedText);
		return new StringAndLastIndex(sb.toString(), ind);
	}

	private boolean checkTag(int ind, String markupElement) {
		if (markupElement == null || ind + markupElement.length() > paragraph.length()) {
			return false;
		}
		return paragraph.substring(ind, ind + markupElement.length()).equals(markupElement);
	}

	private String changeMarkup(String markupElement) {
        return HTMLSYMBOLS.get(markupElement);
    }

    private String getMarkupTag(int ind) {
        for (String tag : MDSYMBOLS) {
            if (checkTag(ind, tag)) {
            	if (tag.equals("[")) {
            		return "]";
            	}
                return tag;
            }
        }
        return null;
    }
	private String toHtmlSymbols(int ind) {
		char symbol = paragraph.charAt(ind);
		return SPECIALS.getOrDefault(symbol, Character.toString(symbol));
	}

	private String backslashSymbols(int ind) {
		String symbol = Character.toString(paragraph.charAt(ind));
		if (MDSYMBOLS.contains(symbol) || symbol.equals("\\")) {
            return symbol;
        } else {
            return "\\" + symbol;
        }
    }

    private class StringAndLastIndex {
    	private String str;
    	private int ind;

    	public StringAndLastIndex(String str, int ind) {
    		this.str = str;
    		this.ind = ind;
    	}
    }
}
