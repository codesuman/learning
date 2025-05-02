## [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/description/)

### Description
---
Given an integer array `nums` and an integer `k`, return the `kth` largest element in the array.

Note that it is the `kth` largest element in the sorted order, not the `kth` distinct element.

Can you solve it without sorting?

#### Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

#### Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4

### Solution

```java
    public int findKthLargest(int[] nums, int k) {
        if(Objects.isNull(nums) || nums.length == 0) return 0;

        // In Java, PriorityQueue's are minHeaps by default
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int num: nums) {
            pq.add(num);

            if(pq.size() > k) {
                pq.remove();
            }
        }

        return pq.remove();
    }
```

**The Core Idea: Maintaining the *k* Largest Elements**

The key is that the priority queue (implemented as a min-heap in this case) *always* holds the *k* largest elements encountered so far. By using a min-heap and limiting its size to *k*, we ensure the smallest of the *k* largest elements is always at the top (root) of the heap.

**Step-by-Step Explanation**

:one: **Initialization:** An empty min-heap `pq` is created.

:two: **Iteration:** The code iterates through the input array `nums`.

:three: **Adding to Heap:** For each `num`, it's added to the min-heap `pq`.

:four: **Maintaining Size *k*:** This is the crucial step. `if (pq.size() > k)` checks if the heap now has more than *k* elements. If it does, `pq.remove()` (which removes the *minimum* element from the min-heap) is called.

**Why removing the minimum works:**

*   Since it's a min-heap, the smallest element among the current elements in the heap is at the root.
*   If we have more than *k* elements, it means we have more than the *k* largest elements encountered so far. The smallest of these is definitely *not* one of the *k* largest overall elements. So, removing it doesn't affect our search for the *k*th largest.
*   By removing the smallest element when the size exceeds *k*, we guarantee that the heap *always* contains the *k* largest elements seen up to that point in the iteration.

:five: **Final Result:** After processing all elements in `nums`, the min-heap `pq` contains the *k* largest elements. The smallest of these (which is at the root of the min-heap) is the *k*th largest element overall. Therefore, `pq.remove()` at the end returns the *k*th largest element.

**Example Walkthrough (nums = {3, 2, 1, 5, 6, 4}, k = 2)**

1.  **3:** `pq = {3}`
2.  **2:** `pq = {2, 3}` (min-heap structure)
3.  **1:** `pq = {1, 3, 2}`. `pq.size()` is 3 > 2. `pq.remove()` removes 1. `pq = {2, 3}`
4.  **5:** `pq = {2, 3, 5}`. `pq.size()` is 3 > 2. `pq.remove()` removes 2. `pq = {3, 5}`
5.  **6:** `pq = {3, 5, 6}`. `pq.size()` is 3 > 2. `pq.remove()` removes 3. `pq = {5, 6}`
6.  **4:** `pq = {4, 6, 5}`. `pq.size()` is 3 > 2. `pq.remove()` removes 4. `pq = {5, 6}`

Finally, `pq.remove()` returns 5, which is the 2nd largest element.

**In summary:** The min-heap of size *k* acts as a "window" that always holds the *k* largest values encountered so far. By removing the minimum when the size exceeds *k*, we efficiently maintain this window and ensure that the root of the heap is always the *k*th largest element at each step, and therefore after processing all elements.
