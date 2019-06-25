#!/usr/bin/env python3

from collections import Counter

n = int(input())
x = list(map(int, input().rstrip().split()))

total = sum(x)
count = len(x)

# Calculate the arithmetic mean, or average value
mean = total / count

# Calculate the median value (50th percentile mark)
xs = sorted(x)
center = (count // 2)
if count % 2 == 1:
    median = xs[center]
else:
    median = ( xs[center-1] + xs[center] ) / 2

# Calculate the mode - most common value
# This bears some explanation:
# * Counter builds a histogram
# * sorted() sorts the items by negative value, then by the key
# * finally, it returns the one at the beginning
# the sorting by negative value is so that the sort can be in ascending order,
# yet output the values in descending order, 
# while still using the keys in ascending order
# clever trick! :)
distribution = Counter(x)
most_common_first = sorted( distribution.items(), key=lambda x: (-x[1],x[0]) )
mode = most_common_first[0][0]

print( '{:.1f}'.format(mean) )
print( '{:.1f}'.format(median) )
print( '{:d}'.format(mode) )

