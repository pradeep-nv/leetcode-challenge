
class Solution {
    int m, n;
    int[][] grid;

    public int countIslands(int[][] grid, int k) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = dfs(i, j);

                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n ||
            grid[r][c] == 0) {
            return 0;
        }

        long sum = grid[r][c];
        grid[r][c] = 0;

        sum += dfs(r - 1, c);
        sum += dfs(r + 1, c);
        sum += dfs(r, c - 1);
        sum += dfs(r, c + 1);

        return sum;
    }
}
