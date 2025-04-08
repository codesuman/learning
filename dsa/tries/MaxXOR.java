public class MaxXOR {
    private final BinaryTrie binaryTrie = new BinaryTrie();

    public int calculateMaxXOR(int[] nums) {
        for(int num : nums) {
            this.binaryTrie.add(num);
        }

        int result = Integer.MIN_VALUE;

        for(int num : nums) {
            result = Math.max(result, this.binaryTrie.calculateMaxXOR(num));
        }

        return result;
    }
}
