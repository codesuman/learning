public class LongestSubArrayOfOnes{
    private static int calcSubArrayLength(int[] nums) {
        int result = 0;
        boolean flipped = false;
        int l = 0;

        // Loop through the array using sliding window technique
        // once you encounter 0, set flipped = true
        // Keep updating the result, result = Math.max(result, right-left+1)
        // If you encounter 0 even when flipped = true, then time to shrink window
        // Return result-1

        for(int r=0; r<nums.length; r++) {
            while(flipped && nums[r]==0) {
                if(nums[l]==0) flipped = false;
                l++;
            }

            if(nums[r]==0) flipped = true;

            result = Math.max(result, r-l+1);
        }

        return result-1;
    }

    public static void main(String[] args) {
        int res1 = calcSubArrayLength(new int[]{1,1,0,1});
        System.out.println(res1);

        int res2 = calcSubArrayLength(new int[]{0,1,1,1,0,1,1,0,1});
        System.out.println(res2);
    }
}