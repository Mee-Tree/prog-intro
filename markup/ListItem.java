package markup;

import java.util.List;

public class ListItem {
	private List<InList> elements;

	public ListItem(List<InList> elements) {
		this.elements = elements;
	}

	public void toTex(StringBuilder sb) {
		sb.append("\\item ");
		for (InList cur : elements) {
			cur.toTex(sb);
		}
	} 
}