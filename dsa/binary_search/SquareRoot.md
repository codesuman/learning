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