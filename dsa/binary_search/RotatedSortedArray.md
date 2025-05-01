## [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/description/)

### Description
---
There is an integer array `nums` sorted in ascending order (with distinct values).

Prior to being passed to your function, `nums` is possibly rotated at an unknown pivot index `k` (`1 <= k < nums.length`) such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]` (0-indexed). For example, `[0,1,2,4,5,6,7]` might be rotated at pivot index `3` and become `[4,5,6,7,0,1,2]`.

Given the array `nums` after the possible rotation and an integer target, return the index of target if it is in `nums`, or -1 if it is not in `nums`.

You must write an algorithm with `O(log n)` runtime complexity.
  
### Solution

```java
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

            int prev = (mid+len-1)%len;
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
```

### Solution with comments

```java
class RotatedSortedArray {
    /**
     * 🔍 Searches for a target value in a rotated sorted array.
     *
     * This method efficiently finds the index of the target value within a
     * sorted array that might have been rotated. It first identifies the pivot
     * index (the point of rotation) and then performs binary search in the
     * appropriate half of the array.
     *
     * @param nums   The rotated sorted array to search in. 🔢
     * @param target The value to search for. 🎯
     * @return The index of the target value if found, otherwise -1. 📍 or -1️⃣
     */
    public int search(int[] nums, int target) {
        // 🛡️ Handle the base case where the array has only one element.
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        // 🧭 Find the pivot index, which is the point where the array is rotated.
        int pi = getPivotIndex(nums);

        // 🔎 Perform binary search in the left part of the rotated array (from index 0 to pivot - 1).
        int leftPart = binarySearch(nums, target, 0, pi - 1);

        // ✅ If the target is not found in the left part, search in the right part (from pivot to the end).
        if (leftPart == -1) {
            return binarySearch(nums, target, pi, nums.length - 1);
        }

        // 🎉 If found in the left part, return its index.
        return leftPart;
    }

    /**
     * ⚙️ Finds the pivot index in a rotated sorted array.
     *
     * The pivot index is the index of the smallest element in the rotated array.
     * This method uses a modified binary search approach to locate it.
     *
     * @param nums The rotated sorted array. 🔢
     * @return The index of the pivot element. 📍
     */
    private int getPivotIndex(int[] nums) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;

        // 🧐 Binary search to find the pivot.
        while (l <= r) {
            // 💡 Calculate the middle index, preventing potential integer overflow.
            int mid = l + (r - l) / 2;

            // ➡️ If the array is not rotated, the first element is the "pivot" (smallest).
            if (nums[l] < nums[mid] && nums[mid] < nums[r]) return l; // Array is not rotated 🔄

            /**
             * ⏪ `(mid + len - 1)` is used instead of `mid - 1` to prevent negative index values. 🛡️
             * For example, if `mid` is zero (0️⃣), then `mid - 1` would be negative one (-1️⃣), which is an invalid index.
             * Adding the length of the array (`len`) and then taking the modulo (`% len`) will result in the last index of the array. 尾➡️
             *
             * 🤔 You might be thinking, why do we need the modulo operator (`%`) here when we just have `(mid + len - 1)`?
             * For example, if `mid` is any non-zero value, like `mid = 3` when `len = 5`, then `(mid + len - 1)` would be 7️⃣, which is out of bounds. 💥
             * The modulo operator (`%`) helps to reset 7️⃣ to 2️⃣: `(3 + 5 - 1) % 5 = 7 % 5 = 2`. 🔄
             */
            int prev = (mid + len - 1) % len;

            /**
             * ➡️ The modulo operator (`%`) with `len` (the length of the array) is crucial here. 🔑
             * It handles the "wrap-around" effect of the rotation. 🔄
             * If `mid` is the last index of the array (尾➡️), then `mid + 1` would be out of bounds. 💥
             * The modulo operator (`%`) ensures that you wrap back to the beginning of the array (首➡️).
             * For example, if `mid` is the last index (e.g., 4 when `len = 5`), then `mid + 1` will be equal to the length of the array (5️⃣).
             * When you take the modulo by the length of the array (`% 5`), you will get zero (0️⃣), which is the first index. 首➡️
             */
            int next = (mid + 1) % len;

            // 🔑 If the current element is smaller than both its neighbors, it's the pivot.
            if (nums[mid] < nums[prev] && nums[mid] < nums[next]) {
                return mid; // Found the pivot! 🎉
            }
            // 📉 If the middle element is smaller than the last element, the pivot is in the left half.
            else if (nums[mid] < nums[len - 1]) {
                r = mid - 1; // Search the left side. ⬅️
            }
            // ⬆️ Otherwise, the pivot is in the right half.
            else {
                l = mid + 1; // Search the right side. ➡️
            }
        }

        // 🤔 If the loop finishes without finding a distinct pivot (e.g., a sorted array),
        // the first element's index is considered the pivot.
        return l;
    }

    /**
     * 🎯 Performs standard binary search within a sorted range of the array.
     *
     * @param nums   The array to search in. 🔢
     * @param target The value to search for. 🎯
     * @param l      The starting index of the search range (inclusive). ➡️
     * @param r      The ending index of the search range (inclusive). ⬅️
     * @return The index of the target value if found, otherwise -1. 📍 or -1️⃣
     */
    private int binarySearch(int[] nums, int target, int l, int r) {
        // 🧐 Continue searching as long as the left index is less than or equal to the right index.
        while (l <= r) {
            // 💡 Calculate the middle index.
            int mid = (l + r) / 2;

            // ✅ If the middle element is the target, we've found it!
            if (nums[mid] == target) {
                return mid; // Target found! 🎉
            }
            // ⬆️ If the middle element is smaller than the target, search in the right half.
            else if (nums[mid] < target) {
                l = mid + 1; // Move the left boundary. ➡️
            }
            // 📉 If the middle element is larger than the target, search in the left half.
            else {
                r = mid - 1; // Move the right boundary. ⬅️
            }
        }

        // 😞 If the loop finishes without finding the target, it's not in the array.
        return -1; // Target not found. 🙅‍♀️
    }
}
```