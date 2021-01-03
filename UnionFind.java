public class UnionFind {
    private int size;
    private int[] sz;
    private int[] id;
    private int numComponents;

    public UnionFind(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("Size <= is not allowed");

        this.size = numComponents = size;
        this.sz = new int[size];
        this.id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i;
            sz[i] = i;
        }
    }

    public int find(int p) {
        int root = p;
        while (root != id[root])
            root = id[root];

        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int componentSize(int p) {
        return sz[find(p)];
    }

    public int size(){
        return size;
    }

    public int components() {
        return numComponents;
    }

    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        if(root1 == root2) return;

        if(sz[root1] < sz[root2]) {
            sz[root2] += sz[root1];
            id[root1] = root2;
        } else {
            sz[root1] += sz[root2];
            id[root2] = root1;
        }

        numComponents--;
    }
}
