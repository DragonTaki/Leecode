import java.util.ArrayList;
import java.util.List;

public class Solution1023 {
    // 思路：從 pattern 拿字母出來找
    // 如果比對到就往後找下一個
    // 如果發現多餘的小寫無視，多餘的大寫則 false
    // 直到比對完畢 true，比對不完 false
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        
        for (String query : queries) {
            result.add(matches(query, pattern));
        }
        
        return result;
    }
    
    private boolean matches(String query, String pattern) {
        int i = 0; // pointer for pattern
        
        for (char c : query.toCharArray()) {
            if (i < pattern.length() && c == pattern.charAt(i)) {
                i++; // matched next pattern char
            } else if (Character.isUpperCase(c)) {
                return false; // extra uppercase not allowed
            }
        }
        
        return i == pattern.length(); // pattern must be fully matched
    }
}

// Score:
// Runtime: 0 ms
// Memory: 43.10 MB
