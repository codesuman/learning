import java.util.*;

public class FindMostFrequent {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,3,3,3,1,2,1,1,1,1,1,1};
        int maxFrequencyVal = findMostFrequent(nums);

        System.out.println(maxFrequencyVal);
    }

    private static int findMostFrequent(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int maxFrequency = 1;
        int maxFrequencyVal = nums[0];

        // Itr through nums and populate frequency map
        // If we find an element with more frequency while populating frequency map, then update maxFrequency & maxFrequencyVal

        for(int i=0; i<nums.length; i++) {
            int currNum = nums[i];

            if(frequencyMap.containsKey(currNum)) {
                int frequency = frequencyMap.get(currNum);
                frequencyMap.put(currNum, ++frequency);

                // Check & update
                if(frequencyMap.get(currNum) > maxFrequency) {
                    maxFrequency = frequencyMap.get(currNum);
                    maxFrequencyVal = currNum;
                }
            } else {
                frequencyMap.put(currNum, 1);
            }
        }

        return maxFrequencyVal;
    }
}