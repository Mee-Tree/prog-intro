package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup {
	public Emphasis(List<InParagraph> elements) {
		super(elements);
	}

	public void toTex(StringBuilder sb) {
		super.toTex(sb, "\\emph{", "}");
	} 
	public void toMarkdown(StringBuilder sb) {
		super.toMarkdown(sb, "*");
	} 
}