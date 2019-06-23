#!/usr/bin/env python3

def flippingBits(n):
    return n ^ 0xFFFFFFFF

n = int(input())
for i in range(n):
    n = int(input())
    print( flippingBits(n) )

