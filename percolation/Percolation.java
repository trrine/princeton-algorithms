import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;                          // number of rows and columns
    private boolean[] open;                 // to keep track of open and blocked sites
    private int openCount;                  // number of open sites
    private WeightedQuickUnionUF ufOpen;    // to keep track of connected open sites
    private WeightedQuickUnionUF ufFull;    // to keep track of full sites
    private int virtualTop;                 // location of virtual top
    private int virtualBottom;              // location of virtual bottom

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();

        this.n = n;
        open = new boolean[n * n];
        openCount = 0;

        // add 2 virtual sites (top and bottom)
        ufOpen = new WeightedQuickUnionUF(n * n + 2);

        // add top virtual site
        // bottom virtual site not needed
        ufFull = new WeightedQuickUnionUF(n * n + 1);

        virtualTop = n * n;
        virtualBottom = n * n + 1;

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row);
        validate(col);

        if (!isOpen(row, col)) {
            // adjust to zero-based indexing
            row--;
            col--;

            // open and connect
            open[row * n + col] = true;
            openCount++;
            connectAdjacentOpen(row, col);
        }
    }

    // connects the open site with adjacent open sites
    private void connectAdjacentOpen(int row, int col) {
        int site = row * n + col;

        // if site is in the top row, connect to virtual top
        if (row == 0) {
            ufOpen.union(virtualTop, site);
            ufFull.union(virtualTop, site);
        }

        // if site is in the bottom row, connect to virtual bottom
        if (row == n - 1) {
            ufOpen.union(site, virtualBottom);
        }

        // connect to open site above
        if (row > 0) {
            int above = (row - 1) * n + col;

            if (open[above]) {
                ufOpen.union(site, above);
                ufFull.union(site, above);
            }
        }

        // connect to open site below
        if (row < n - 1) {
            int below = (row + 1) * n + col;

            if (open[below]) {
                ufOpen.union(site, below);
                ufFull.union(site, below);
            }
        }

        // connect to open site to the left
        if (col > 0) {
            int left = row * n + col - 1;

            if (open[left]) {
                ufOpen.union(site, left);
                ufFull.union(site, left);
            }
        }

        // connect to open site to the right
        if (col < n - 1) {
            int right = row * n + col + 1;

            if (open[right]) {
                ufOpen.union(site, right);
                ufFull.union(site, right);
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);

        // adjust to zero-based indexing
        row--;
        col--;
        return open[row * n + col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);

        if (!isOpen(row, col)) { // site must be open to be full
            return false;
        }

        // adjust to zero-based indexing
        row--;
        col--;

        // site is full if it connected to virtual top
        int site = row * n + col;
        return ufFull.find(site) == ufFull.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return ufOpen.find(virtualTop) == ufOpen.find(virtualBottom);
    }

    private void validate(int i) {
        if (i < 1 || i > n) {
            throw new IllegalArgumentException("Index must be between 1 and " + n);
        }
    }

    // test client (optional)

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        System.out.println("Open sites:" + percolation.numberOfOpenSites());
        percolation.open(1, 1);
        percolation.open(5, 2);
        System.out.println("1,1 open:" + percolation.isOpen(1, 1));
        System.out.println("1,1 full: " + percolation.isFull(1, 1));
        System.out.println("6,0 full: " + percolation.isFull(6, 0));
        System.out.println("Percolates: " + percolation.percolates());
        percolation.open(2, 2);
        percolation.open(3, 2);
        percolation.open(4, 2);
        System.out.println("Percolates: " + percolation.percolates());
        percolation.open(2, 1);
        System.out.println("Percolates: " + percolation.percolates());
        System.out.println("Open sites:" + percolation.numberOfOpenSites());
        System.out.println("2,1 full: " + percolation.isFull(2, 1));
        // percolation.open(0, 1);
    }
}
