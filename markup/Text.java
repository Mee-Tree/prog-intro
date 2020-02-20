package markup;

public class Text implements InParagraph{
	private String text;

	public Text(String text) {
		this.text = text;
	}   

	public void toTex(StringBuilder sb) {
		sb.append(text);	
	}
	public void toMarkdown(StringBuilder sb) {
		sb.append(text);	
	}
}