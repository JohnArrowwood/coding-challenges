#!/usr/bin/env python3

n = int(input())
x = list(map(int, input().rstrip().split()))

def quartiles(x):

    def median(y):
        """This utility version of median() assumes the list is already sorted"""
        count = len(y)
        center = (count // 2)
        if count % 2 == 1:
            return y[center]
        else:
            return ( y[center-1] + y[center] ) / 2

    xs = sorted(x)
    half = len(x) // 2
    return ( median( xs[:half] ), median(xs), median( xs[-half:] ) )

for q in quartiles(x):
    print( '{:.0f}'.format(q) )

