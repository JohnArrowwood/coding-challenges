# New Year Chaos

[Problem Statement](https://www.hackerrank.com/challenges/new-year-chaos)

## Solution

* Start at the end and reverse the bribe chain
* Each swap back, increment the counter
* If it takes more than 2 steps to get the person back to position N, throw an error
* Otherwise, return the swap count

* As long as no error is thrown, output the swap count
* Otherwise, output "Too chaotic"