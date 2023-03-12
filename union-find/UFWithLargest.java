public class UFWithLargest {
    private int[] id;           // parent[i] = parent of i
    private int[] size;         // size[i] = number of elements in subtree rooted at i
    private int[] largestValue; // largestValue[i] = largest value in i's component
    private int count;          // number of components

    public UFWithLargest(int n) {
        id = new int[n];
        size = new int[n];
        largestValue = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
            largestValue[i] = i;
        }
    }
    public int count() {
        return count;
    }

    private int root(int i) { 
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
            largestValue[rootQ] = Math.max(largestValue[rootQ], largestValue[rootP]);
        }

        else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
            largestValue[rootP] = Math.max(largestValue[rootQ], largestValue[rootP]);
        }

        count--;
    }

    public int find(int i) {
        return largestValue[root(i)];
    }

    // test
    public static void main(String[] args) {
        int[] membersA = {0, 0, 1, 3};
        int[] membersB = {1, 5, 5, 4};
        int n = 6;
        UFWithLargest uf = new UFWithLargest(n);

        for (int i = 0; i < membersA.length; i++) {
            uf.union(membersA[i], membersB[i]);
        }

        System.out.println("Largest value in component of 0: " + uf.find(0));
        System.out.println("Largest value in component of 3: " + uf.find(3));

    }
}
