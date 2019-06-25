#!/usr/bin/env python3

nd = input().split()
n = int(nd[0])
d = int(nd[1])
a = list(map(int, input().rstrip().split()))

def rotateLeft(x,d):
    while d > 0:
        x = x[1:] + x[0:1]
        d -= 1 
    return x

result = rotateLeft( a, d )

print( ' '.join(map(str,result)) )
