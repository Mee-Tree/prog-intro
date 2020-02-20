import java.io.*;
import java.util.*;

public class WordStatLastIndex {
    private static class LastIndex {
        private IntList list = new IntList();
        private int qty = 0;
        private int lastLine = 0;

        public int getQty(){
            return qty;
        }
        public void incQty(){
            qty++;
        }

        public void put(int index, int lineNum) {
            if (lineNum == lastLine) {
                int size = list.size();
                list.set(size - 1, index);
            } else {
                list.put(index);
                lastLine = lineNum;
            }
        }

        public int get(int i) {
            return list.get(i);
        }
        public int size(){
            return list.size();
        }
    }

    public static void main(String[] args) {
        Map<String, LastIndex> cnt = new LinkedHashMap<String, LastIndex>();
        try (Scanner in = new Scanner(new File(args[0]))) {
            String word;
            int lineNum = 1;
            while (in.hasNextLine()) {
                int index = 1;
                try (Scanner line = new Scanner(in.nextLine())) {
                    while (line.hasNextWord()) {
                        word = line.nextWord().toLowerCase();
                        LastIndex tmp = cnt.get(word);
                        if (tmp == null) {
                            tmp = new LastIndex();
                        }
                        tmp.put(index++, lineNum);
                        tmp.incQty();
                        cnt.put(word, tmp);
                    }
                } catch (IOException e) {
                    System.err.println("Input error: " + e.getMessage());
                }
                lineNum++;
            }
        } catch (IOException e) {
            System.err.println("Input error: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8")
            );
            try {
                for (String key : cnt.keySet()) {
                    LastIndex tmp = cnt.get(key);
                    writer.write(key + " " + tmp.getQty());
                    for (int i = 0; i < tmp.size(); ++i) {
                        writer.write(" " + tmp.get(i));
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