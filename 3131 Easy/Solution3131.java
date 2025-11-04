import java.util.Arrays;

class Solution3131 {
    public int addedInteger(int[] nums1, int[] nums2) {
        // 題目已給 nums2 一定由 nums1 來
        // 故找到 nums1, nums2 的最小值之差即為答案
        int min1 = Arrays.stream(nums1).min().getAsInt();
        int min2 = Arrays.stream(nums2).min().getAsInt();
        return min2 - min1;
    }
}

// Score:
// Runtime: 5 ms
// Memory: 45.3 MB
