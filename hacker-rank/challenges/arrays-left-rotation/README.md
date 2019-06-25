# Arrays: Left Rotation

[Problem Statement](https://www.hackerrank.com/challenges/ctci-array-left-rotation)

# Solution 1: Repeated Rotate

Implement a rotate-left.  Call it N times.  Output the result.  O(N)

# Solution 2: Calculated Rotate

* Calculate the offset of which item will be in position 1
* Split the string at that point
* Put the last half first, and the first half last
* O(1)