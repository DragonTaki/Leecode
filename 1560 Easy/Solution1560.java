import java.util.ArrayList;
import java.util.List;

public class Solution1560 {
    public List<Integer> mostVisited(int n, int[] rounds) {
        // 看最後一圈頭尾
        int start = rounds[0];
        int end = rounds[rounds.length - 1];
        List<Integer> ans = new ArrayList<>();
        
        // 起點 < 終點 => 多出「起~終」這段
        // 起點 = 終點 => 頭尾相連
        // 起點 > 終點 => 多出「起~終」這段
        if (start <= end) {
            for (int i = start; i <= end; i++) {
                ans.add(i);
            }
        } else {
            for (int i = 1; i <= end; i++) ans.add(i);
            for (int i = start; i <= n; i++) ans.add(i);
        }
        
        return ans;
    }
}

// Score:
// Runtime: 0 ms
// Memory: 44.50 MB
