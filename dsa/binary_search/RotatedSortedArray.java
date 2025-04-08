class RotatedSortedArray {
    public int search(int[] nums, int target) {
        if(nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int pi = getPivotIndex(nums);

        int leftPart = binarySearch(nums, target, 0, pi-1);

        if(leftPart == -1) {
            return binarySearch(nums, target, pi, nums.length-1);
        }

        return leftPart;
    }

    private int getPivotIndex(int[] nums) {
        int len = nums.length;
        int l = 0;
        int r = len-1;

        while(l<=r) {
            int mid = l+ (r-l) / 2; // Avoids potential integer overflow issues

            if(nums[l] < nums[mid] && nums[mid] < nums[r]) return l; // Array is not rotated

            /**
             * (mid + len -1) is used instead of mid -1 to prevent negative index values. 
             * For example, if mid is zero, then mid -1 would be negative one. 
             * Adding the length of the array, and then modding it, will result in the last index of the array.
             *
             * Again, the modulo operator (%) with len handles the wrap-around. 
             * If mid is the first index of the array, mid - 1 would be out of bounds. 
             * The modulo ensures that you wrap around to the end of the array.
             */
            int prev = (mid+len-1)%len;

            /**
             * The modulo operator (%) with len (the length of the array) is crucial here. 
             * It handles the "wrap-around" effect of the rotation. 
             * If mid is the last index of the array, mid + 1 would be out of bounds. 
             * The modulo operator ensures that you wrap back to the beginning of the array.
             * For example, if mid is the last index, then mid+1 will be equal to the length of the array. 
             * When you mod it by the length of the array, you will get zero, which is the first index.
             */
            int next = (mid+1)%len;

            if(nums[mid] < nums[prev] && nums[mid] < nums[next]) {
                return mid;
            } else if(nums[mid] < nums[len-1]) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }

        return l;
    }

    private int binarySearch(int[] nums, int target, int l, int r) {
        while(l<=r) {
            int mid = (l+r) / 2;

            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }

        return -1;
    }
}