import java.util.ArrayList;

/**
 * Created by derros on 2/4/18.
 * Union-Find-Disjoint sets.
 */
public class UnionFindDisjointSet {

    private ArrayList<Integer> set;
    private int size;
    public UnionFindDisjointSet(int size) {
        this.set = new ArrayList<>();
        for(int i = 0; i < size; ++i)
            set.add(i);
        this.size = size;
    }

    public int find(int i) {
        if (i==set.get(i)) {
            return i;
        } else {
            --size;
            i = find(set.get(i));
            return i;
        }
    }

    public void union(int i, int j ) {
        set.set(find(i), find(j));
        --size;
    }

    public void grow(int additionalSize) {
        if (additionalSize > set.size())
            return;
        for(int i = set.size(); i < additionalSize; ++i) {
            set.add(i);
        }
    }

    public boolean equals(int i, int j) {
        return find(i) == find(j);
    }

    public int getSize() {
        return this.size;
    }
}
