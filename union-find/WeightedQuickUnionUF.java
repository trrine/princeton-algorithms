public class WeightedQuickUnionUF {
    private int[] id;       // parent[i] = parent of i
    private int[] size;     // size[i] = number of elements in subtree rooted at i
    private int count;      // number of components

    public WeightedQuickUnionUF(int n) {
        id = new int[n];
        size = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }
    public int count() {
        return count;
    }

    public int root(int i) { // find parent
        validate(i);
    
        while (i != id[i]) i = id[i];
        return i;
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return root(p) == root(q);
    }

    private void validate(int i) {
        int n = id.length;

        if (i < 0 || i >= n) {
            throw new IllegalArgumentException("index " + i + " is not between 0 and " + (n-1));
        }
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);

        int rootP = root(p);
        int rootQ = root(q);

        if (rootP == rootQ) return;

        if (size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }

        else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }

        count--;
    }
}
