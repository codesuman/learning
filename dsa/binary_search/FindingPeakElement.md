### Understanding the [Problem](https://leetcode.com/problems/find-peak-element/)

* __Peak Element Definition:__ A peak element is an element that is strictly greater than both its left and right neighbors.
* __Boundary Conditions:__ We imagine that the elements outside the array's bounds are negative infinity. This ensures that the first and last elements can also be peak elements if they are greater than their single neighbor.
* __Goal:__ Find any peak element's index in the given array.
* __Time Complexity Requirement:__ The solution must be O(log n), which strongly suggests using binary search.

### The Rational Behind the Binary Search Approach

:white_check_mark: __Exploiting the "Imaginary" Negative Infinity:__

* The key to using binary search is to leverage the fact that nums[-1] and nums[n] are considered negative infinity.
* This property guarantees that if you move in a direction where the elements are increasing, you are guaranteed to find a peak.

:fire: __Binary Search Logic:__

* We start with a standard binary search, defining left and right pointers to the start and end of the array.
* We calculate the middle index mid.
* Now, we compare ```nums[mid]``` with its neighbors (nums[mid - 1] and nums[mid + 1]).
* __Case 1: ```nums[mid]``` is a Peak:__
If ```nums[mid]``` is greater than both its neighbors, we've found a peak, and we return mid.
* __Case 2: ```nums[mid]``` is Smaller Than Its Right Neighbor:__
If ```nums[mid]``` < nums[mid + 1], it means the array is increasing as we move to the right.
Because of the "negative infinity" boundary condition, we know there must be a peak element to the right of mid. Therefore, we update left = mid + 1.
* __Case 3: ```nums[mid]``` is Smaller Than Its Left Neighbor:__
If ```nums[mid]``` < nums[mid - 1], it means the array is increasing as we move to the left.
Similarly, there must be a peak element to the left of mid. We update right = mid - 1.

:pushpin: __Why This Works (Guaranteed Peak):__

* The crucial insight is that by always moving towards the increasing side, we are guaranteed to find a peak.
* Imagine the array as a graph. If you keep moving "uphill," you will eventually reach a "peak."
* Because the problem states that nums[-1] and nums[n] = -infinity, there is always an increasing direction to follow.

:books: __O(log n) Time Complexity:__

* Binary search halves the search space in each iteration, resulting in a logarithmic time complexity.

In essence, the binary search algorithm cleverly uses the problem's boundary conditions and the "increasing direction" principle to efficiently locate a peak element without needing to examine every element in the array.