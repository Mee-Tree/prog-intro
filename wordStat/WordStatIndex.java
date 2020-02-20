import java.io.*;
import java.util.*;

public class WordStatIndex {
    static class intList {
        final private int SIZE = 8;
        private int[] arr = new int[SIZE];
        private int arrPtr = 0;

        public void put(int num) {
            if (arrPtr == arr.length) {
                arr = Arrays.copyOf(arr, 2 * arr.length);
            }
            arr[arrPtr++] = num;
        }

        public int get(int pos) {
            return arr[pos];
        }

        public int size() {
            return arrPtr;
        }
    }
    public static void main(String[] args) {
        Map<String, intList> cnt = new LinkedHashMap<String, intList>();
        try (Scanner in = new Scanner(new File(args[0]))) {
            String word;
            int index = 1;
            while (in.hasNextWord()) {
                word = in.nextWord().toLowerCase();
                cnt.putIfAbsent(word, new intList());
                cnt.get(word).put(index++);
                cnt.put(word, cnt.get(word));
            }
        } catch (IOException e) {
            System.err.println("Input error: " + e.getMessage());
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8")
            );
            try {
                for (String key : cnt.keySet()) {
                    intList arr = cnt.get(key);
                    writer.write(key + " " + arr.size());
                    for (int i = 0; i < arr.size(); ++i) {
                        writer.write(" " + arr.get(i));
                    }
                    writer.write("\n");
                }
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error with opening or creating file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Output error: " + e.getMessage());
        }
    }
}