package markup;

import java.util.List;

public class Strong extends AbstractMarkup {
	public Strong(List<InParagraph> elements) {
		super(elements);
	}   

	public void toTex(StringBuilder sb) {
		super.toTex(sb, "\\textbf{", "}");
	} 
	public void toMarkdown(StringBuilder sb) {
		super.toMarkdown(sb, "__");
	} 
}