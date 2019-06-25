#!/usr/bin/env python3

from SortedVirtualArray import SortedVirtualArray

n = int(input())
values = list(map(int, input().rstrip().split()))
counts = list(map(int, input().rstrip().split()))

def quartileRange(v,c):

    a = SortedVirtualArray( values, counts )
    N = len(a)

    # handle boundary cases
    if N == 0 or N == 1: 
        return 0

    # calculate the values needed to do the calculation
    half = N // 2
    c = half // 2

    # calculate based on if there are an odd or even number of elements
    if half % 2 == 0:
        a,b,c,d = a[ c - 1, c, N - c - 1, N - c ]
        q1 = (a + b) / 2
        q3 = (c + d) / 2
    else: 
        q1,q3 = a[ c, N - c - 1 ]

    return q3 - q1

result = quartileRange( values, counts )

print( '{:.1f}'.format( result ) )