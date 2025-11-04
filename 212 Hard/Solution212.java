import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution212 {
    // 題目要點：
    // 看 board, words 哪個數量級大
    // board >> words => 拿 words 去找位置：建立字母位置表，查上下左右
    // board << words => 拿位置去搜尋 words 樹：建立 Trie

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        // 如果節點已是樹葉末端 => 存整串 word 以利直接獲得字串
        // 如果是樹枝 => null
        String word = null;
    }

    public List<String> findWords(char[][] board, String[] words) {
        // Step 1：建立 Trie
        TrieNode root = buildTrie(words);

        List<String> result = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;

        // Step 2：DFS 遍歷每個格子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node = node.children.computeIfAbsent(c, k -> new TrieNode());
            }
            node.word = word;
        }
        return root;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        char c = board[i][j];
        if (!node.children.containsKey(c))
            return;

        TrieNode nextNode = node.children.get(c);
        if (nextNode.word != null) {
            result.add(nextNode.word);
            nextNode.word = null;
        }

        board[i][j] = '#';  // 將造訪過的 board 點換成 '#' 號
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int d = 0; d < 4; d++) {
            int ni = i + dx[d], nj = j + dy[d];
            if (ni >= 0 && ni < board.length && nj >= 0 && nj < board[0].length && board[ni][nj] != '#') {
                dfs(board, ni, nj, nextNode, result);
            }
        }

        board[i][j] = c;  // 回溯成原本的字母
    }
}

// Score:
// Runtime: 431 ms
// Memory: 49.80 MB
