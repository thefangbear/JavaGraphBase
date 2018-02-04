/**
 * Created by Rui-Jie Fang on 2/3/18.
 * Code for getting all permutations of an array. Based on Heap's Algorithm.
 */

public class Permutations<T> {

    private T[] arr;
    private int len;
    private Callback callback = null;

    public Permutations(T[] arr, Callback callback) {
        this.arr = arr;
        this.len = arr.length;
        this.callback = callback;
    }

    public Permutations(T[] arr) {
        this.arr = arr;
        this.len = arr.length;
        this.callback = this::printArray;
    }

    private void printArray() {
        for (int i = 0; i < this.len; ++i) {
            System.out.print(" " + arr[i]);
        }
        System.out.println("");
    }

    private void exch(int idx1, int idx2) {
        T c = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = c;
    }

    /**
     * Generates all permutations. Assuming T implements a toString() method.
     */
    public void permute(int size) {
        if (size == 1) {
            this.callback.callback();
            return;
        }
        for (int i = 0; i < size - 1; ++i) {
            permute(size - 1);
            if ((i >> 1) == 1) {
                exch(0, size - 1);
            } else {
                exch(i, size - 1);
            }
        }
        permute(size - 1);
    }

    public int getLen() {
        return this.len;
    }

    public T[] getArr() {
        return this.arr;
    }

    public static interface Callback {
        public void callback();
    }
}
