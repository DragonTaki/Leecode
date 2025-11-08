// 這題題目複雜
// 行為 1：
// (A) 條件：需要 currentEnergy >= enemyEnergies[i]
// (B) 獲得 1 分
// (C) 失去 enemyEnergies[i] 點能量
// (D) 可重複使用於每個敵人
// 行為 2：
// (A) 條件：至少 1 分（就是不能開場就用）
// (B) 獲得 enemyEnergies[i] 點能量
// (C) 不扣任何東西
// (D) 敵人會被標記，每個敵人只能使用 1 次
// 回傳最大獲得點數

import java.util.Arrays;

public class Solution3207 {
    public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
        int n = enemyEnergies.length;
        Arrays.sort(enemyEnergies);  // 排序便宜到貴

        int left = 0;                // 最便宜
        int right = n - 1;           // 最貴
        long points = 0;
        long maxPoints = 0;  // WA：溢位了，要使用 long

        while (left <= right) {
            // 能量夠：打最便宜的敵人拿分
            if (currentEnergy >= enemyEnergies[left]) {
                currentEnergy -= enemyEnergies[left];
                points++;
                maxPoints = Math.max(maxPoints, points);
            }
            // 能量不夠：打最貴的敵人補能量
            else if (points > 0) {
                currentEnergy += enemyEnergies[right];
                points--;
                right--;
            }
            // 分數不夠操作
            else {
                break;
            }
        }

        return maxPoints;
    }
}

// Score:
// Runtime: TLE
// Memory:
