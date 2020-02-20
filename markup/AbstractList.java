package markup;

import java.util.List;

public abstract class AbstractList implements InList {
	protected List<ListItem> elements;

	protected AbstractList(List<ListItem> elements) {
		this.elements = elements;	
	}

	protected void toTex(StringBuilder sb, String leftTag, String rightTag) {
		sb.append(leftTag);
		for (ListItem cur : elements) {
			cur.toTex(sb);
		}
		sb.append(rightTag);
	}
}