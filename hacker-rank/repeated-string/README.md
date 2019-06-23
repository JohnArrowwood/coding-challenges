# Repeated String

[Problem Statement](https://www.hackerrank.com/challenges/repeated-string/problem)

# Solution 1: Iteration

The way the problem is described, the solution is to round-robin rotate
through the letters N times, and each time you see the letter 'a', increase
your counter.

# Solution 2: Pre-Calculation

The reality is, we can easily see how many letter a's there are in the
string.  And we know how long the string is.  So if you divide N by the
number of characters in the string, that's how many times we will go
through the string completely.  Multiply that by the number of a's in 
the string, and you are almost done.  Now, iterate through what is left
like solution 1, but without the modulo division on every step.

# Which is Faster

When N is large, solution 1 is grossly inefficient: O(N)

Solution 2 execution time only varies based on the length of the string,
making it, more or less: O(1)

