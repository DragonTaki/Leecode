public class Solution3021 {
    public long flowerGame(int n, int m) {
        // 思路：x + y 為奇數的組合數量
        // WA：使用 int 會溢位，注意約束範圍
        long oddX = (n + 1) / 2;
        long evenX = n / 2;
        long oddY = (m + 1) / 2;
        long evenY = m / 2;

        return oddX * evenY + evenX * oddY;
    }
}

// Score:
// Runtime: 0 ms
// Memory: 42.60 MB
