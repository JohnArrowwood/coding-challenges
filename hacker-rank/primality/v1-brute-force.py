#!/usr/bin/env python3

from math import sqrt

def isPrime(n):
    if n < 2: return False
    if n == 2: return True
    if n % 2 == 0: return False
    limit = int(sqrt(n))
    for factor in range(3,limit,2):
        if n % 2 == 0: return False
    return True

count = int(input())
for i in range(count):
    n = int(input())
    print( "Prime" if isPrime(n) else "Not prime" )
