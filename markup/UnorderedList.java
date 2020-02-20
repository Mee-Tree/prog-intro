package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
	public UnorderedList(List<ListItem> elements) {
		super(elements);
	}   

	public void toTex(StringBuilder sb) {
		super.toTex(sb, "\\begin{itemize}", "\\end{itemize}");
	} 
}