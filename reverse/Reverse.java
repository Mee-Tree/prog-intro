package prog-intro.reverse;

import prog-intro.scanners.Scanner;
import java.io.*;
import java.util.Arrays;

public class Reverse {
	public static void main(String[] args) {
		final int SIZE = 8; 

		int[][] arr = new int[SIZE][];
		int[] buffer = new int[SIZE];
		int curSize = 0;
		try (Scanner in = new Scanner()){
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
				if (curSize == arr.length) {
                	arr = Arrays.copyOf(arr, 2 * arr.length);
            	}
				arr[curSize++] = Arrays.copyOf(buffer, size);
			}
		} catch (IOException e){
			System.err.println("Input error: " + e.getMessage());
		}
		for (int i = curSize - 1; i >= 0; --i) {
			for (int j = arr[i].length - 1; j >= 0; --j) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
