public class Solution2419 {
    public int longestSubarray(int[] nums) {
        int max = 0;
        // 找到序列中最大值
        for (int num : nums) max = Math.max(max, num);

        // 然後找最大值的最大連續長度
        int longest = 0, count = 0;
        for (int num : nums) {
            if (num == max) {
                count++;
                longest = Math.max(longest, count);
            } else {
                count = 0;
            }
        }

        return longest;
    }
}

// Score:
// Runtime: 3 ms
// Memory: 92.50 MB
