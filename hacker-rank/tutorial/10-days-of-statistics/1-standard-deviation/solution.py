#!/usr/bin/env python3

from math import sqrt

n = int(input())
x = list(map(int, input().rstrip().split()))

def standard_deviation(x):

    N = len(x)
    total = sum(x)
    mean = total / N

    error = [ xi - mean for xi in x ]
    squared = [ e**2 for e in error ]

    sum_of_squared = sum( squared )
    average = sum_of_squared / N

    return sqrt( average )

result = standard_deviation( x )

print( '{:.1f}'.format( result ) )