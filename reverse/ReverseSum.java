import java.util.Scanner;
import java.util.Arrays;

public class ReverseSum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[][] arr = new int[1][];
		int[] buffer = new int[1];
		int curSize = 0, maxLength = 0;
		while (in.hasNextLine()) {
			Scanner line = new Scanner(in.nextLine());
			int size = 0;
			while (line.hasNextInt()) {
				if (size == buffer.length) {
                	buffer = Arrays.copyOf(buffer, 2 * buffer.length);
            	}
				buffer[size++] = line.nextInt();
			}
			line.close();
			maxLength = Math.max(maxLength, size);
			if (curSize == arr.length) {
                arr = Arrays.copyOf(arr, 2 * arr.length);
            }
			arr[curSize++] = Arrays.copyOf(buffer, size);
		}
		in.close();
		int[] lineSum = new int[curSize];
		int[] colSum = new int[maxLength];
		for (int i = 0; i < curSize; ++i) {
			for (int j = 0; j < arr[i].length; ++j) {
				lineSum[i] += arr[i][j];
				colSum[j] += arr[i][j];
			}
		}
		for (int i = 0; i < curSize; ++i) {
			for (int j = 0; j < arr[i].length; ++j) {
				System.out.print((lineSum[i] + colSum[j] - arr[i][j]) + " ");
			}
			System.out.println();
		}
	}
}
