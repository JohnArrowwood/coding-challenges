# Counting Valleys

[Problem Statement](https://www.hackerrank.com/challenges/counting-valleys/problem)

## Solution 1: Simple Transition Counter

The first solution that comes to mind is to count the transitions from
down to up.

Sadly, that doesn't even give the correct answer for the sample input.
Which is sad because real-world valleys don't have to go below sea 
level to be considered a valley.  But we can ignore that, I suppose.

## Solution 2: Count Transitions to Below Starting Point

Keep a running altitude marker.  Whenever you transition below 0, 
increment your valley counter.
