# Weighted Mean

Day 0, 10 Days of Statistics

[Problem Statement](https://www.hackerrank.com/challenges/s10-weighted-mean/problem)

## Solution

Multiply each value by the corresponding weight, then divide by the sum of the weights.

    ( x1 * w1 + x2 * w2 + x3 * w3 + . . . + xN * wN ) / ( w1 + w2 + w3 + . . . + wN )

Notice: if you put a 1 for all of the weights, you end up with the same formula as
the one typically used for arithmetic mean.

Notice that with large positive weights, there is a chance of numerical overflow.  
In that case, you can take the sum of the weights, and adjust each weight to be the 
portion of the total contributed by that weight:

	# using vector math
	t = sum(w)  
	w = w / t
	mean = sum( x * w )

