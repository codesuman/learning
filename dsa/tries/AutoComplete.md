### Code :

```java
import java.lang.*;
import java.util.*;

class TrieNode {
    private Map<Character, TrieNode> characterMap;
    private TreeMap<Integer, String> weightsMap;

    public TrieNode() {
        this.characterMap = new HashMap<>();
        this.weightsMap = new TreeMap<>(Comparator.reverseOrder());
    }

    public boolean has(Character chr) {
        return this.characterMap.containsKey(chr);
    }

    public TrieNode get(Character chr) {
        return this.characterMap.get(chr);
    }

    public TrieNode set(Character chr) {
        if(!this.has(chr)) this.characterMap.put(chr, new TrieNode());

        return this.get(chr);
    }

    public void autoComplete() {
        // Loop through the weightsMap till the count becomes MAX_COUNT & print out values
        int MAX_COUNT = 5;
        int count = 0;

        Set<Integer> keySet = this.weightsMap.keySet();

        for(Integer key : keySet) {
            System.out.print(this.weightsMap.get(key) + " ");

            count++;

            if(count == MAX_COUNT) break;
        }

        System.out.println();
    }

    public void addWeight(int weight, String word) {
        this.weightsMap.put(weight, word);
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String[] words, int[] weights) {
        for(int i=0; i < words.length; i++) {
            this.insert(words[i], weights[i]);
        }
    }

    public void insert(String word, int weight) {
        TrieNode currNode = this.root;

        for(int i=0; i < word.length(); i++) {
            Character currChar = word.charAt(i);
            currNode = currNode.set(currChar);

            currNode.addWeight(weight, word);
        }
    }

    public void search(String[] prefixes) {
        for(String prefix: prefixes) {
            TrieNode currNode = this.root;
            boolean prefixFound = true;

            for(int i=0; i < prefix.length(); i++) {
                Character currChar = prefix.charAt(i);

                if(currNode.has(currChar)) {
                    currNode = currNode.get(currChar);
                } else {
                    prefixFound = false;
                    System.out.println("-1 ");
                    break;
                }
            }

            if(prefixFound) {
                currNode.autoComplete();
            }
        }
    }
}
```

### Usage :

```java
public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert(new String[]{"abcd", "aecd", "abaa", "abef", "acdcc", "acbcc"}, new int[]{2, 1, 3, 4, 6, 5});
        trie.search(new String[]{"ab", "abc", "a"});
    }
}
```

### Usage from CLI :

```java
public class Main {
    public static void main(String[] args) {
        // YOUR CODE GOES HERE
        // Please take input and print output to standard input/output (stdin/stdout)
        // DO NOT USE ARGUMENTS FOR INPUTS
        // E.g. 'Scanner' for input & 'System.out' for output

        Scanner sc = new Scanner(System.in);
        int totalTestCases = sc.nextInt();

        while(totalTestCases-- > 0)
        {
            int N = sc.nextInt();
            int M = sc.nextInt();

            String[] words = new String[N];
            for(int i = 0; i < N; i++)
                words[i] = sc.next();

            int[] weights = new int[N];
            for(int j = 0; j < N; j++)
                weights[j] = sc.nextInt();

            String[] prefixes = new String[M];
            for(int k = 0; k < M; k++)
                prefixes[k] = sc.next();

            Trie trie = new Trie();
            trie.insert(words, weights);
            trie.search(prefixes);
        }
    }
}
```