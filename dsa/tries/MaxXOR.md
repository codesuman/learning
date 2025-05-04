```java
class BinaryTrieNode {
    private final BinaryTrieNode[] list = new BinaryTrieNode[2]; // 🌳 Each node has pointers to two children: 0 and 1 (representing bits).

    /**
     * 🔍 Checks if the current node has a child for the given bit index.
     *
     * @param idx The bit index (0 or 1). 🔢
     * @return `true` if a child exists for the index, `false` otherwise. ✅/❌
     */
    public boolean has(int idx) {
        return this.list[idx] != null; // Check if the pointer to the child node is not null.
    }

    /**
     * ➡️ Retrieves the child node for the given bit index.
     *
     * @param idx The bit index (0 or 1). 🔢
     * @return The child `BinaryTrieNode` if it exists, otherwise `null`. 🌳 or ∅
     */
    public BinaryTrieNode get(int idx) {
        return this.list[idx]; // Return the child node.
    }

    /**
     * 🛠️ Sets (or gets if already present) the child node for the given bit index.
     * If a child doesn't exist, a new `BinaryTrieNode` is created.
     *
     * @param i The bit index (0 or 1). 🔢
     * @return The child `BinaryTrieNode`. 🌳
     */
    public BinaryTrieNode set(int i) {
        if (!this.has(i)) { // If a child for this bit doesn't exist...
            this.list[i] = new BinaryTrieNode(); // ...create a new node. 🌱
        }
        return this.get(i); // Return the (potentially newly created) child node.
    }
}

class BinaryTrie {
    private final BinaryTrieNode root = new BinaryTrieNode(); // 🌳 The root of our binary trie.

    /**
     * ➕ Adds a number to the binary trie. Each bit of the number (from MSB to LSB)
     * determines the path taken in the trie.
     *
     * @param val The integer value to add. 🔢
     */
    public void add(int val) {
        BinaryTrieNode currNode = this.root; // Start from the root. 🌳

        // Iterate through the bits of the number from the most significant bit (31st) to the least significant bit (0th).
        for (int i = 31; i >= 0; i--) {
            int bit = this.getBit(val, i); // Get the i-th bit of the value. ➡️
            currNode = currNode.set(bit); // Traverse down the trie, creating a node if it doesn't exist for the current bit. 🚶‍♀️
        }
        // After iterating through all bits, the number is represented as a path from the root to the last node reached. 📍
    }

    /**
     * 🧮 Calculates the maximum XOR value of the given number with any number currently in the trie.
     * The goal is to find a number in the trie whose bits are as different as possible from the input number's bits.
     *
     * @param num The integer value to find the maximum XOR with. 🔢
     * @return The maximum XOR value achievable. ✨
     */
    public int calculateMaxXOR(int num) {
        BinaryTrieNode currNode = this.root; // Start from the root. 🌳
        int result = 0; // Initialize the result. 🏆

        // Iterate through the bits of the number from MSB to LSB.
        for (int i = 31; i >= 0; i--) {
            int bit = this.getBit(num, i); // Get the i-th bit of the current number. ➡️
            int flippedBit = bit ^ 1; // Get the opposite bit (0 becomes 1, 1 becomes 0). 🔄

            // Check if the current node has a child with the flipped bit.
            boolean hasFlipBit = currNode.has(flippedBit);

            // If we find a child with the flipped bit, it maximizes the XOR at this bit position.
            // We add 2^i to the result because this bit contributes to the XOR.
            result += hasFlipBit ? (1 << i) : 0; // Add 2^i if the flipped bit exists. ➕

            // Move down the trie. If we found a flipped bit, go down that path (to maximize further XOR).
            // Otherwise, go down the path of the original bit.
            currNode = hasFlipBit ? currNode.get(flippedBit) : currNode.get(bit); // Navigate the trie. 🧭
        }

        return result; // Return the maximum XOR value found. 🎉
    }

    /**
     * ➡️ Extracts the i-th bit of a given value.
     *
     * @param val The integer value. 🔢
     * @param i   The bit position (0 for LSB, 31 for MSB). 🔢
     * @return The value of the i-th bit (0 or 1). 0️⃣ or 1️⃣
     */
    private int getBit(int val, int i) {
        return (val >> i) & 1; // Right shift the value by i positions and perform bitwise AND with 1 to get the i-th bit. ⚙️
    }
}

public class MaxXOR {
    private final BinaryTrie binaryTrie = new BinaryTrie(); // 🌳 Create an instance of our Binary Trie.

    /**
     * 🧮 Calculates the maximum XOR value between any two numbers in the given array.
     *
     * @param nums The array of integers. 🔢
     * @return The maximum XOR value found between any pair of numbers. ✨
     */
    public int calculateMaxXOR(int[] nums) {
        // First, add all the numbers from the input array into the binary trie.
        for (int num : nums) {
            this.binaryTrie.add(num); // Add each number to the trie. ➕
        }

        int result = Integer.MIN_VALUE; // Initialize the result with the smallest possible integer value. 📉

        // Iterate through each number in the array again.
        for (int num : nums) {
            // For each number, find the maximum XOR it can have with any number already in the trie.
            result = Math.max(result, this.binaryTrie.calculateMaxXOR(num)); // Update the maximum XOR result if a larger value is found. ⬆️
        }

        return result; // Return the overall maximum XOR value. 🎉
    }
}
```