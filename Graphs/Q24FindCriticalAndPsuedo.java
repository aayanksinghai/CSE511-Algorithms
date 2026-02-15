import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UnionFind {
    int[] parent;
    int[] rank;
    int maxSize;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        maxSize = 1;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int a) {
        while (parent[a] != a) {
            parent[a] = parent[parent[a]];
            a = parent[a];
        }
        return a;
    }

    public boolean makeUnion(int a, int b) {
        int parA = find(a), parB = find(b);
        if (parA != parB) {
            if (rank[parA] < rank[parB]) {
                parent[parA] = parB;
                rank[parB] += rank[parA];
                maxSize = Math.max(maxSize, rank[parB]);
            } else {
                parent[parB] = parA;
                rank[parA] += rank[parB];
                maxSize = Math.max(maxSize, rank[parA]);
            }
            return true;
        }
        return false;
    }
}

class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }

        Arrays.sort(newEdges, (a, b) -> a[2] - b[2]);

        int weight = 0;
        UnionFind ufMST = new UnionFind(n);
        for (int[] edge : newEdges) {
            if (ufMST.makeUnion(edge[0], edge[1])) {
                weight += edge[2];
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            UnionFind ufCritical = new UnionFind(n);
            int ignoreWeight = 0;
            for (int j = 0; j < m; j++) {
                if (i != j && ufCritical.makeUnion(newEdges[j][0], newEdges[j][1])) {
                    ignoreWeight += newEdges[j][2];
                }
            }

            if (ufCritical.maxSize < n || ignoreWeight > weight) {
                // add it's original index
                result.get(0).add(newEdges[i][3]);
            } else {
                UnionFind ufForce = new UnionFind(n);
                ufForce.makeUnion(newEdges[i][0], newEdges[i][1]);
                int forceWeight = newEdges[i][2];
                for (int j = 0; j < m; j++) {
                    if (i != j && ufForce.makeUnion(newEdges[j][0], newEdges[j][1])) {
                        forceWeight += newEdges[j][2];
                    }
                }
                if (forceWeight == weight) {
                    result.get(1).add(newEdges[i][3]);
                }
            }
        }
        return result;
    }
}