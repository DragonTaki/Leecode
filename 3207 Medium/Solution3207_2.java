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

public class Solution3207_2 {
    public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
        long sum = 0;
        int minE = Integer.MAX_VALUE;

        // 先找到最便宜的
        // 同時計算總合
        for (int e : enemyEnergies) {
            sum += e;
            minE = Math.min(minE, e);
        }

        // 若起始分數打不了最便宜的 => 0 分
        if (currentEnergy < minE) return 0;

        // 保留最便宜的作為刷分來源，吸取其餘所有敵人
        long totalEnergyAfterAbsorb = currentEnergy + (sum - minE);
        return totalEnergyAfterAbsorb / minE;
    }
}

// Score:
// Runtime: 1 ms
// Memory: 89.77 MB
