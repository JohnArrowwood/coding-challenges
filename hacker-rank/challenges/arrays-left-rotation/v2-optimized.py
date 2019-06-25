#!/usr/bin/env python3

n,d = map( int,input().split() )
a = list(map(int, input().rstrip().split()))

def rotateLeft(x,d):
    l = len(x)
    r = d % l
    return x[r:] + a[:r]

result = rotateLeft( a, d )

print( ' '.join(map(str,result)) )
