import java.util.HashMap;
import java.util.Map;

class Solution3164 {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        // Hash map，紀錄 nums2 * k 的出現頻率
        Map<Integer, Integer> freq = new HashMap<>();
        // 紀錄 nums2 * k 中的最小值
        int minVal = Integer.MAX_VALUE;

        // Step 1：遍歷 nums2
        // (A) 記錄 nums2 * k 至 HashMap 中
        // (B) 紀錄 nums2 * k 中的最小值
        for (int j : nums2) {
            int val = j * k;
            freq.put(val, freq.getOrDefault(val, 0) + 1);
            minVal = Math.min(minVal, val);
        }

        long count = 0;

        // Step 2：遍歷 nums1，對 nums1 每個值找其因數
        for (int i : nums1) {
            // 如果未滿足最低需求 (>= minVal)，則跳過
            if (i % k != 0 || i < minVal) continue;

            for (int d = 1; d <= i / d; d++) {
                if (i % d == 0) {
                    // d 是因數
                    if (freq.containsKey(d)) {
                        count += freq.get(d);
                    }
                    // i/d 是另外一個因數
                    // 排除重複（完全平方數）
                    int d2 = i / d;
                    if (d2 != d && freq.containsKey(d2)) {
                        count += freq.get(d2);
                    }
                }
            }
        }
        return count;
    }
}

// Score:
// Runtime: 685 ms 差
// Memory: 170.3 MB
