import java.util.Arrays;
import java.io.*;

public class ReverseMin {
	public static void main(String[] args) {
 		final int INF = Integer.MAX_VALUE;
 		final int SIZE = 8;

		int[][] arr = new int[SIZE][];
		int[] buffer = new int[SIZE];
		int curSize = 0, maxLength = 0;
		try (Scanner in = new Scanner()) {
			while (in.hasNextLine()) {
				int size = 0;
				try (Scanner line = new Scanner(in.nextLine())){
					while (line.hasNextInt()) {
						if (size == buffer.length) {
                			buffer = Arrays.copyOf(buffer, 2 * buffer.length);
            			}
						buffer[size++] = line.nextInt();
					}
				} catch (IOException e){
					System.err.println("Input error: " + e.getMessage());
				}
				maxLength = Math.max(maxLength, size);
				if (curSize == arr.length) {
                	arr = Arrays.copyOf(arr, 2 * arr.length);
            	}
				arr[curSize++] = Arrays.copyOf(buffer, size);
			}
		} catch (IOException e) {
			System.err.println("Input error: " + e.getMessage());
		}
		int[] lineSum = new int[curSize];
		int[] colSum = new int[maxLength];
		for (int i = 0; i < curSize; ++i) {
			lineSum[i] = INF;
		}
		for (int i = 0; i < maxLength; ++i) {
			colSum[i] = INF;
		}
		for (int i = 0; i < curSize; ++i) {
			for (int j = 0; j < arr[i].length; ++j) {
				lineSum[i] = Math.min(arr[i][j], lineSum[i]);
				colSum[j] = Math.min(arr[i][j], colSum[j]);
			}
		}
		for (int i = 0; i < curSize; ++i) {
			for (int j = 0; j < arr[i].length; ++j) {
				System.out.print(Math.min(lineSum[i], colSum[j]) + " ");
			}
			System.out.println();
		}
	}
}
