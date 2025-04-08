import java.util.HashMap;
import java.util.Map;

public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode currNode = this.root;

        for(int i=0; i<word.length(); i++) {
            Character currChar = word.charAt(i);

            currNode = currNode.setChar(currChar);
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("car");
        trie.insert("carpenter");
        trie.insert("cool");
        trie.insert("cold");
    }
}

class TrieNode {
    private final Map<Character, TrieNode> map = new HashMap<>();

    public boolean hasChar(Character chr) {
        return this.map.containsKey(chr);
    }

    public TrieNode getChar(Character chr) {
        return this.map.get(chr);
    }

    public TrieNode setChar(Character chr) {
        if(!this.hasChar(chr)) this.map.put(chr, new TrieNode());

        return this.getChar(chr);
    }
}
