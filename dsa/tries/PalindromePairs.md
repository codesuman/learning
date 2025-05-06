## 🔹 Understanding the Approach
The solution uses a Trie (prefix tree) where words are inserted in reverse order. This allows us to efficiently find palindrome pairs by searching for prefixes and suffixes in a structured manner.

### Key Observations:
* Insert words in reverse order:

This makes it easy to check if a suffix of a word is a palindrome (by searching for prefixes in the Trie).

* Search for matching words efficiently:

If a word’s remaining suffix is a palindrome and its matching prefix is already in the Trie, we have a palindrome pair.

## 🔹 Breaking Down the Code

### 1️⃣ TrieNode Structure
```java
class TrieNode {
    Map<Character, TrieNode> children; // Children nodes
    int wordIndex; // Stores index of the word if it's a complete word
    List<Integer> palindromePrefixes; // Indices of words that form palindromes with this word

    public TrieNode() {
        children = new HashMap<>();
        wordIndex = -1; // Default: no word ends here
        palindromePrefixes = new ArrayList<>();
    }
}
```

* `children`: Stores characters to navigate through the Trie.

* `wordIndex`: If a word ends at this node, this holds the index of that word.

* `palindromePrefixes`: Stores indices of words that form palindromes with this word.

### 2️⃣ Inserting Words in Reverse
```java
public void insert(String word, int index) {
    TrieNode node = root;
    for (int i = word.length() - 1; i >= 0; i--) { // Reverse insertion
        char ch = word.charAt(i);

        // If remaining word[0...i] is palindrome, store index
        if (isPalindrome(word, 0, i)) {
            node.palindromePrefixes.add(index);
        }

        node.children.putIfAbsent(ch, new TrieNode());
        node = node.children.get(ch);
    }

    // Mark end of word
    node.wordIndex = index;
}
```

#### Why Reverse Insertion?
* Suffix matching becomes prefix searching, which is efficient in a Trie.

* If a suffix of a word is a palindrome, store its index in palindromePrefixes.

* This helps detect cases where one word is a suffix of another, and the remaining part is a palindrome.

### 3️⃣ Searching for Pairs
```java
public void search(String word, int index, List<List<Integer>> result) {
    TrieNode node = root;
    for (int i = 0; i < word.length(); i++) { // Traverse normally
        char ch = word.charAt(i);

        // Case 1: If node contains a full word and remaining part is a palindrome
        if (node.wordIndex != -1 && node.wordIndex != index && isPalindrome(word, i, word.length() - 1)) {
            result.add(Arrays.asList(index, node.wordIndex));
        }

        if (!node.children.containsKey(ch)) {
            return;
        }
        node = node.children.get(ch);
    }

    // Case 2: If we reach a valid word in Trie that’s different from current word
    if (node.wordIndex != -1 && node.wordIndex != index) {
        result.add(Arrays.asList(index, node.wordIndex));
    }

    // Case 3: If we reach a prefix in Trie, check stored palindrome indices
    for (int palindromeIndex : node.palindromePrefixes) {
        if (palindromeIndex != index) {
            result.add(Arrays.asList(index, palindromeIndex));
        }
    }
}
```

#### Why These Conditions?
1.  Direct Match (`wordIndex != -1`)

* If a word fully exists in the Trie, we check if the remaining part of the current word is a palindrome.

2. Prefix Matches (`palindromePrefixes`)

* This helps when one word is a prefix of another and the remaining part forms a palindrome.

## 🔹 Example Walkthrough
#### Input: `["abcd", "dcba", "lls", "s", "sssll"]`

### 1️⃣ Insertion into Trie (in reverse order):

* `"abcd"` → Insert `"dcba"`
* `"dcba"` → Insert `"abcd"`
* `"lls"` → Insert `"sll"`
* `"s"` → Insert `"s"`
* `"sssll"` → Insert `"llsss"`

<br>
The Trie now looks like this:
<br>

```arduino

(root)
 ├── 'd'
 │   ├── 'c'
 │   │   ├── 'b'  
 │   │   │   ├── 'a'  (wordIndex = 0)
 ├── 'a'
 │   ├── 'b'
 │   │   ├── 'c'  
 │   │   │   ├── 'd'  (wordIndex = 1)
 ├── 's'  (wordIndex = 3)
 │   ├── 'l'
 │   │   ├── 'l'  (wordIndex = 2)
 ├── 'l'
 │   ├── 'l'
 │   │   ├── 's'
 │   │   │   ├── 's'
 │   │   │   │   ├── 's'  (wordIndex = 4)
```

```arduino

(root)  <palindromePrefixes = [3]>
 ├── 'd'
 │   ├── 'c'
 │   │   ├── 'b'  
 │   │   │   ├── 'a'
 ├── 'a'
 │   ├── 'b'
 │   │   ├── 'c'  
 │   │   │   ├── 'd'
 ├── 's'  <palindromePrefixes = [2]>
 │   ├── 'l'
 │   │   ├── 'l'
 ├── 'l'
 │   ├── 'l'  <palindromePrefixes = [4]>
 │   │   ├── 's'  <palindromePrefixes = [4]>
 │   │   │   ├── 's'
 │   │   │   │   ├── 's'
```

Final Output:

```java
[[0, 1], [1, 0], [3, 2], [2, 4]]
```