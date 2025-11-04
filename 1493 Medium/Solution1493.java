public class Solution1493 {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        // 紀錄左右
        // 滑動 right
        // 當 zeroCount > 1 => 滑動 left 直到 zeroCount <= 1
        // 紀錄 left, right 最大的差值
        int left = 0, zeroCount = 0, maxLen = 0;
        
        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) zeroCount++;
            
            while (zeroCount > 1) {
                if (nums[left] == 0) zeroCount--;
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left);
        }
        
        if (maxLen == n)
            maxLen--;

        return maxLen;
    }
}

// Score:
// Runtime: 3 ms
// Memory: 65.50 MB
