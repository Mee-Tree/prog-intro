import java.io.*;
import java.util.*;

public class WordStatCount{
    public static Comparator<Word> cmp = new Comparator<Word>() {
        public int compare(Word a, Word b) {
            if (a.getQty() == b.getQty()) {
                return a.getPos() - b.getPos();
            } else {
                return a.getQty() - b.getQty();
            }   
        }
    };
    private static class Word {
        private int qty;
        private int pos;
        private String str;
        
        public Word(int qty, int pos, String str) {
            this.qty = qty;
            this.pos = pos;
            this.str = str;
        }

        public int getQty() {
            return qty;
        }
        public int getPos() {
            return pos;
        }
        public String getStr() {
            return str;
        }
    }
    public static void main(String[] args) {
        Map<String, Integer> cnt = new LinkedHashMap<String, Integer>();
        try (Scanner in = new Scanner(new File(args[0]))) {
            String word;
            while (in.hasNextWord()) {
                word = in.nextWord().toLowerCase();
                cnt.putIfAbsent(word, 0);
                cnt.put(word, cnt.get(word) + 1);
            }
        } catch (IOException e) {
            System.err.println("Input error: " + e.getMessage());
            return;
        }
        
        List<Word> arr = new ArrayList<Word>();
        int it = 0;
        for (String key : cnt.keySet()) {
            Word tmp = new Word(cnt.get(key), it++, key); 
            arr.add(tmp);
        }
        arr.sort(cmp);
        
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8")
            );
            try {
                for (int i = 0; i < arr.size(); ++i) {
                    Word word = arr.get(i);
                    writer.write(word.getStr() + " " + word.getQty() + "\n");
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