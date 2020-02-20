package markup;

import java.util.List;

public class Paragraph implements InList {
	private List<InParagraph> elements;

	public Paragraph(List<InParagraph> elements) {
		this.elements = elements;	
	}
	
	public void toTex(StringBuilder sb) {
		for (InParagraph cur : elements) {
			cur.toTex(sb);
		}
	} 
	
	public void toMarkdown(StringBuilder sb) {
		for (InParagraph cur : elements) {
			cur.toMarkdown(sb);
		}
	} 
}