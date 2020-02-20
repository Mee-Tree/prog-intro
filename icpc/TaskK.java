import java.io.*;
import java.util.*;
 
public class TaskK {
	static List<Pair> children;
	static char[][] matrix;

	static void fill(Pair beg, Pair end) {
		for (int i = 0; i < children.size(); ++i) {
			Pair child = children.get(i);
			if (contains(child, beg, end)) {
				char letter = Character.toLowerCase(matrix[child.x][child.y]);
				int minY = child.y - 1;
				int maxY = child.y + 1;
				while (minY >= beg.y && matrix[child.x][minY] == '.') {
					matrix[child.x][minY] = letter;
					minY--;
				}
				while (maxY < end.y && matrix[child.x][maxY] == '.') {
					matrix[child.x][maxY] = letter;
					maxY++;
				}
			}
		}
		for (int i = 0; i < children.size(); ++i) {
			Pair child = children.get(i);
			if (contains(child, beg, end)) {
				char letter = Character.toLowerCase(matrix[child.x][child.y]);
				int minY = child.y;
				int maxY = child.y + 1;
				while (minY >= beg.y && Character.toLowerCase(matrix[child.x][minY]) == letter) {
					minY--;
				}
				while (maxY < end.y && matrix[child.x][maxY] == letter) {
					maxY++;
				}
				for (int y = minY + 1; y < maxY; ++y) {
					int minX = child.x - 1;
					int maxX = child.x + 1;
					while (minX >= beg.x && matrix[minX][y] == '.') {
						matrix[minX][y] = letter;
						minX--;
					}
					while (maxX < end.x && matrix[maxX][y] == '.') {
						matrix[maxX][y] = letter;
						maxX++;
					}
				}
			}
		}
	}

	static boolean contains(Pair a, Pair beg, Pair end) {
		return (a.x < end.x && a.y < end.y &&
				a.x >= beg.x && a.y >= beg.y);
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
		int n = in.nextInt();
        int m = in.nextInt();

        matrix = new char[n][m];
        for (int i = 0; i < n; ++i) {
        	String line = in.next();
        	for (int j = 0; j < m; ++j) {
        		matrix[i][j] = line.charAt(j);
        	}
        }
        Pair A = new Pair(0, 0);
        children = new ArrayList<>();
        for (int i = 0 ; i < n; ++i) {
        	for (int j = 0; j < m; ++j) {
        		if (matrix[i][j] == 'A') {
        			A = new Pair(i, j);
        		} else if (matrix[i][j] != '.') {
        			children.add(new Pair(i, j));
        		}
        	}
        }
        children.add(new Pair(-1, -1));
        children.add(new Pair(n, m));
        int maxArea = 0;
        int maxX = A.x, maxY = A.y, minX = A.x, minY = A.y;
        for (int left = 0; left < children.size(); ++left) {
        	for (int right = 0; right < children.size(); ++right) {
				for (int upper = 0; upper < children.size(); ++upper) {
        			for (int lower = 0; lower < children.size(); ++lower) {

        				Pair max = new Pair(children.get(right).x, children.get(upper).y);
        				Pair min = new Pair(children.get(left).x + 1, children.get(lower).y + 1);
    					boolean empty = true;
    					for (int i = 0; i < children.size(); ++i) {
    						if (contains(children.get(i), min, max)) {
    							empty = false;
    							break;
    						}
        				}
        				if (contains(A, min, max) && empty) {
        					int curArea = (max.x - min.x) * (max.y - min.y);
        					if (curArea > maxArea) {
        						maxArea = curArea;
        						maxX = max.x;
        						maxY = max.y;
        						minX = min.x;
        						minY = min.y;
        					} 
        				}
        			}
        		}        	
        	}
        }

        children.add(A);
        fill(new Pair(0, 0), new Pair(n, minY));
        fill(new Pair(0, minY), new Pair(minX, m));
        fill(new Pair(maxX, minY), new Pair(n, m));
        fill(new Pair(minX, maxY), new Pair(maxX, m));
        fill(new Pair(minX, minY), new Pair(maxX, maxY));

        for (int i = 0; i < n; ++i) {
        	for (int j = 0; j < m; ++j) {
        		System.out.print(matrix[i][j]);
        	}
        	System.out.println();
        }
    }
    static class Pair {
	    public final int x;
	    public final int y;

	    public Pair(final int x, final int y) {
	        this.x = x;
	        this.y = y;
	    }
	}
}