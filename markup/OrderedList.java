package markup;

import java.util.List;

public class OrderedList extends AbstractList {
	public OrderedList(List<ListItem> elements) {
		super(elements);
	}   

	public void toTex(StringBuilder sb) {
		super.toTex(sb, "\\begin{enumerate}", "\\end{enumerate}");
	} 
}