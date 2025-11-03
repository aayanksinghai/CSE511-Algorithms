import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int r = mat.length;
        int c = mat[0].length;
        int[][] dist = new int[r][c];
        boolean[][] v = new boolean[r][c];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[] { i, j, 0 });
                    v[i][j] = true;
                    dist[i][j] = 0;
                }
            }
        }

        int[] dr = { 0, 0, 1, -1 };
        int[] dc = { 1, -1, 0, 0 };

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];
            int d = curr[2];

            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];

                if (nr >= 0 && nc >= 0 && nr < r && nc < c && !v[nr][nc]) {
                    v[nr][nc] = true;
                    dist[nr][nc] = d + 1;
                    q.add(new int[] { nr, nc, d + 1 });
                }
            }
        }
        return dist;
    }
}