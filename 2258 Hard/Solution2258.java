import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution2258 {
    // 火跟步數上限需分開設定 + 區隔大
    // 否則接近上限時 if (nt < fireTime[nx][ny]) 會誤判
    static final int INF_FIRE = (int)1e9 * 2;
    static final int INF_WALK = (int)1e9;
    static final int[] DIR = {0, 1, 0, -1, 0};

    public int maximumMinutes(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] fireTime = new int[m][n];
        for (int[] row : fireTime) Arrays.fill(row, INF_FIRE);

        // Step 1：多源火種 BFS
        // 先標記火種位置
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                    fireTime[i][j] = 0;
                }
            }
        }

        // 從 Queue 中拿火 => 計算四散 => 標記新火並放到 Queue => 直到全部散完
        while (!q.isEmpty()) {
            var cur = q.poll();
            int x = cur[0], y = cur[1], t = fireTime[x][y];
            for (int d = 0; d < 4; d++) {
                int nx = x + DIR[d], ny = y + DIR[d + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (grid[nx][ny] == 2) continue;
                if (fireTime[nx][ny] > t + 1) {
                    fireTime[nx][ny] = t + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        // Step 2：假設等待時間，夾擠方式計算
        // 無法先計算路徑再算時間差 <= 路可能被燒掉導致路徑改變
        int left = 0, right = INF_WALK;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canReach(mid, grid, fireTime)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (ans == INF_WALK) return INF_WALK;
        return ans;
    }

    boolean canReach(int wait, int[][] grid, int[][] fireTime) {
        int m = grid.length, n = grid[0].length;

        if (wait > fireTime[0][0]) return false;

        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, -1);

        // 一樣使用 Queue 但起點只有 (0, 0)
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        dist[0][0] = wait;

        while (!q.isEmpty()) {
            var cur = q.poll();
            int x = cur[0], y = cur[1], t = dist[x][y];

            if (x == m - 1 && y == n - 1) {  // 終點
                return true;
            }

            // 也是 BPS 找路
            for (int d = 0; d < 4; d++) {
                int nx = x + DIR[d], ny = y + DIR[d + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (grid[nx][ny] == 2) continue;
                if (dist[nx][ny] != -1) continue;
                int nt = t + 1;
                if (nx == m - 1 && ny == n - 1) {  // 跑到終點
                    if (nt <= fireTime[nx][ny]) {
                        dist[nx][ny] = nt;
                        q.offer(new int[]{nx, ny});
                    }
                } else {                           // 跑到一般格子
                    if (nt < fireTime[nx][ny]) {
                        dist[nx][ny] = nt;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        return false;
    }
}

// Score:
// Runtime: 27 ms
// Memory: 48.48 MB
