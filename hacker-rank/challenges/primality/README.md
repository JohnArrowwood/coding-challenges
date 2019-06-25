# Time Complexity: Primality

[Problem Statement](https://www.hackerrank.com/challenges/ctci-big-o/problem)

## Solution 1: Brute-Force Search

The largest possible factor of a number N is the sqrt(N).

Test every number from 2 .. sqrt(N) looking for one that divides evenly
If one is found, it's not prime, otherwise, it is

## Solution 2: Sieve of Eratosthenes

The largest number you have to test is still sqrt(N), but starting with 2,
mark every multiple up to the limit as non-prime.  Then, when you pick the 
next potential factor to test, skip over any marked as non-prime.
