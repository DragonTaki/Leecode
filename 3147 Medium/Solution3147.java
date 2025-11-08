public class Solution3147 {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int maxEnergy = Integer.MIN_VALUE;

        // 思路：總共有 k 種起點，累加取最大值
        // 陷阱：開頭有可能是負的 => 從後往前累加，取最大值
        for (int i = n - 1; i >= 0; i--) {
            if (i + k < n) {
                energy[i] += energy[i + k];
            }
            maxEnergy = Math.max(maxEnergy, energy[i]);
        }

        return maxEnergy;
    }
}

// Score:
// Runtime: 2 ms
// Memory: 128.27 MB
