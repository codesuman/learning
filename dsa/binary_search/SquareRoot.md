## [Sqrt(x)](https://leetcode.com/problems/sqrtx/description/)

### Description
---
Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.
  
### Solution

```java
    public int mySqrt(int x) {
        // For special cases when x is 0 or 1, return x.
        if (x == 0 || x == 1)
            return x;

        // Initialize the search range for the square root.
        int low = 1;
        int high = x;
        int ans = 0;

        while (low <= high){
            int mid = low + (high-low)/2;
            
            if (mid <= x/mid){
                ans = mid;
                low = mid +1;
            }
            else
                high = mid-1;
        }
        
        return ans;
    }
```

### Solution with comments

```java
/**
 * 🚀 Efficiently calculates the integer square root of a non-negative integer.
 *
 * Given a non-negative integer `x`, this method returns the largest integer
 * that is less than or equal to the square root of `x`. It utilizes a
 * binary search algorithm for optimal performance. 🎯
 *
 * @param x The non-negative integer for which to find the square root. 🔢
 * @return The integer part of the square root of `x`.
 */
public int mySqrt(int x) {
    // 🛡️ Handle special cases for 0 and 1 directly. Their square root is themselves.
    if (x == 0 || x == 1) {
        return x;
    }

    // 📏 Initialize the search range.
    // The square root of x (if x > 1) will always be between 1 and x/2 (inclusive).
    // However, to keep the logic consistent with binary search on the entire range,
    // we initialize low to 1 and high to x.
    int low = 1;
    int high = x;
    int ans = 0; // 🏆 This variable will store the best integer square root found so far.

    // 🔎 Perform binary search until the search range is exhausted.
    while (low <= high) {
        // 💡 Calculate the middle index. This approach prevents potential overflow
        // that could occur with (low + high) / 2 when low and high are very large.
        int mid = low + (high - low) / 2;

        // ✅ Check if the square of the middle element is less than or equal to x.
        // To avoid potential overflow when calculating mid * mid, we use the
        // equivalent condition: mid <= x / mid.
        if (mid <= x / mid) {
            // 🎉 If it is, then 'mid' could be a potential answer, or the actual
            // answer might be larger. So, we store 'mid' as a possible answer
            // and continue searching in the right half.
            ans = mid;
            low = mid + 1; // Move the lower bound to search for a larger square root.
        } else {
            // 😟 If the square of 'mid' is greater than x, then 'mid' is too large.
            // We need to search in the left half.
            high = mid - 1; // Move the upper bound to search for a smaller square root.
        }
    }

    // 🥇 After the binary search, 'ans' will hold the largest integer whose square
    // is less than or equal to x.
    return ans;
}
```

### NOTE : 

The condition `if (mid <= x / mid)` elegantly combines two scenarios:
 
:one:  `if (mid == x / mid)`: This means `mid  mid == x`, so `mid` is the exact
integer square root. While the current implementation doesn't explicitly
return `mid` here and continues searching, `ans` would eventually hold
this value.

:two:  `else if (mid < x / mid)`: This implies `mid  mid < x`. In this case, `mid`
is a potential candidate for the integer square root, so we store it in `ans`
and try to search for a larger possible square root in the right half
(`low = mid + 1`).

### WHY `while (low <= high)` CONDITION : 

#### Scenario 1: `while (low < high)`

:one: Initialization: low = 1, high = 3, ans = 0
:two: Iteration 1:
* mid = low + (high - low) / 2 = 1 + (3 - 1) / 2 = 1 + 1 = 2
* mid * mid = 4, which is greater than x = 3.
* The condition mid <= x / mid (i.e., 2 <= 3 / 2 which is 2 <= 1) is false.
* Therefore, high = mid - 1 = 2 - 1 = 1.
:three: Loop Condition Check: low (1) < high (1) is false. The loop exits.
:four: Result: The function returns the initial value of ans, which is 0. This is incorrect, as the integer square root of 3 is 1. ❌

#### Scenario 2: `while (low <= high)`

:one: Initialization: low = 1, high = 3, ans = 0
:two: Iteration 1:
* mid = low + (high - low) / 2 = 1 + (3 - 1) / 2 = 1 + 1 = 2
* mid * mid = 4, which is greater than x = 3.
* The condition mid <= x / mid (i.e., 2 <= 3 / 2 which is 2 <= 1) is false.
* Therefore, high = mid - 1 = 2 - 1 = 1.
:three: Loop Condition Check: low (1) <= high (1) is true. The loop continues.
:four: Iteration 2:
* mid = low + (high - low) / 2 = 1 + (1 - 1) / 2 = 1 + 0 = 1
* mid * mid = 1, which is less than or equal to x = 3.
* The condition mid <= x / mid (i.e., 1 <= 3 / 1 which is 1 <= 3) is true.
* Therefore, ans = mid = 1, and low = mid + 1 = 1 + 1 = 2.
:five: Loop Condition Check: low (2) <= high (1) is false. The loop exits.
:six: Result: The function returns the final value of ans, which is 1. This is the correct integer square root of 3. ✅

Why while (low <= high) is Crucial Here:

Ensuring Potential Answer is Considered: The while (low <= high) condition allows the loop to continue even when low and high converge to a single value. This final value of mid (when low == high) is a potential candidate for the integer square root and needs to be evaluated. In the x = 3 example, the value 1 (the correct answer) becomes the mid when low and high meet.

Capturing the Floor Value: The goal is to find the largest integer less than or equal to the square root. When the exact square root isn't an integer, the binary search needs to narrow down to the floor value. The while (low <= high) loop, combined with updating ans whenever mid <= x / mid, ensures that we keep track of the largest mid that satisfies the condition. Even when the loop terminates, ans holds the best valid mid found.

#### In summary:

The while (low <= high) condition in this integer square root implementation is essential for:

* ✅ Considering the final potential answer when low and high meet.
* 🏆 Correctly capturing the floor value of the square root when it's not an integer.
* 🛡️ Robustly handling edge cases like x = 0 and x = 1.