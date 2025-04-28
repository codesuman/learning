### Example Walkthrough:

**Input:** `nums = [10, 5, 2, 6]`, `k = 100`

* Start with `l = 0`, `r = 0`, and `p = 1`.

* Sub-Array count formula : `count = (r-l+1)`, `totalCount += count`

* Move `r` and calculate the product of elements in the window:

  * `r = 0`: `p = 10`. It's valid, so the subarray is `[10]`. `count=1`, `totalCount=1`.

  * `r = 1`: `p = 50`. It's valid, so the subarrays are `[10]`, `[5]`, `[10, 5]`. `count=2`, `totalCount=3`.

  * `r = 2`: `p = 100`. Now, `p >= 100`, so we need to move `l` to shrink the window:

    * Move `l = 1`, `p = 10`. It's valid, so the subarrays are `[10]`, `[5]`, `[10, 5]`, `[2]`, `[5, 2]`. `count=2`, `totalCount=5`.

  * `r = 3`: `p = 60`. It's valid, so the subarrays are `[10]`, `[5]`, `[10, 5]`, `[2]`, `[5, 2]`, `[6]`, `[2, 6]`, `[5, 2, 6]`. `count=3`, `totalCount=8`.

In the end, the valid subarrays are counted efficiently.