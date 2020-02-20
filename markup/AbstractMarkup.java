package markup;

import java.util.List;

public abstract class AbstractMarkup implements InParagraph {
	protected List<InParagraph> elements;

	protected AbstractMarkup(List<InParagraph> elements) {
		this.elements = elements;	
	}
	
	protected void toTex(StringBuilder sb, String leftTag, String rightTag) {
		sb.append(leftTag);
		for (InParagraph cur : elements) {
			cur.toTex(sb);
		}
		sb.append(rightTag);
	}
	protected void toMarkdown(StringBuilder sb, String tag) {
		sb.append(tag);
		for (InParagraph cur : elements) {
			cur.toMarkdown(sb);
		}
		sb.append(tag);
	}
}