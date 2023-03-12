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

    private int root(int i) { // find
        while (i != id[i]) i = id[i];
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
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
