package markup;

import java.util.List;

public class Strikeout extends AbstractMarkup {
	public Strikeout(List<InParagraph> elements) {
		super(elements);
	}  

	public void toTex(StringBuilder sb) {
		super.toTex(sb, "\\textst{", "}");
	} 
	public void toMarkdown(StringBuilder sb) {
		super.toMarkdown(sb, "~");
	} 
}