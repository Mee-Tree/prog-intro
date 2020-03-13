package wordStat;

import java.io.*;
import java.util.*;

public class IntList {
    private static final int SIZE = 4;

    private int[] arr = new int[SIZE];
    private int arrPtr = 0;

    public void put(int el) {
        if (arrPtr == arr.length) {
            arr = Arrays.copyOf(arr, 2 * arr.length);
        }
        arr[arrPtr++] = el;
    }

    public void set(int i, int el) {
        arr[i] = el;
    }

    public int get(int i) {
        return arr[i];
    }

    public int size() {
        return arrPtr;
    }
}