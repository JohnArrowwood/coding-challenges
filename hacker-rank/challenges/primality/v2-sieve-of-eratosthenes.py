#!/usr/bin/env python3

from math import sqrt


def isPrime(n):
    if n < 2: return False
    if n == 2: return True

    # keep track of every value known to be prime or non-prime
    sieve = {}

    # test only prime values between 2 and limit
    factor = 2
    limit = int(sqrt(n))

    while factor <= limit:

        # we know that factor is prime
        if n % factor == 0: return False

        sieve[factor] = True

        # we know that every multiple of factor 
        # between here and limit is not-prime
        multiple = factor + factor
        while multiple <= limit:
            sieve[multiple] = False
            multiple += factor

        # jump to the next prime number
        factor += 1
        while factor in sieve and sieve[factor] == False: 
            factor += 1

        # at this point, factor contains the next prime number
        # or it is over the limit and we don't know nor care

    # if we got here, this is a prime number
    return True

count = int(input())
for i in range(count):
    n = int(input())
    print( "Prime" if isPrime(n) else "Not prime" )
