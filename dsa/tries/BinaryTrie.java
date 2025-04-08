public class BinaryTrie {
    private final BinaryTrieNode root = new BinaryTrieNode();

    public void add(int val) {
        BinaryTrieNode currNode = this.root;

        for(int i=31; i>=0; i--) {
            currNode = currNode.set(this.getBit(val, i));
        }
    }

    public int calculateMaxXOR(int num) {
        BinaryTrieNode currNode = this.root;

        int result = 0;

        for(int i=31; i>=0; i--) {
            int bit = this.getBit(num, i);
            boolean hasFlipBit = currNode.has(~bit);

            result += hasFlipBit ? 1<<i : 0;

            currNode = hasFlipBit ? currNode.get(~bit) : currNode.get(bit);
        }

        return result;
    }

    private int getBit(int val, int i) {
        return (val>>i)&1;
    }
}

class BinaryTrieNode {
    private final BinaryTrieNode[] list = new BinaryTrieNode[2];

    public boolean has(int idx) {
        return this.list[idx] != null;
    }

    public BinaryTrieNode get(int idx) {
        return this.list[idx];
    }

    public BinaryTrieNode set(int i) {
        if(!this.has(i)) {
            this.list[i] = new BinaryTrieNode();
        }

        return this.get(i);
    }
}
