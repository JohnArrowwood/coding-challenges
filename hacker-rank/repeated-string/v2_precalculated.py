#!/usr/bin/env python3

def v1_iteration( s, n ):
    size_of_s = len(s)
    count_per_s = sum( 1 if letter == 'a' else 0 for letter in s )
    whole = n // size_of_s;
    remainder = n % size_of_s;
    partial = sum( 1 if letter == 'a' else 0 for letter in s[:remainder] )
    return ( whole * count_per_s ) + partial

repeatedString = v1_iteration

if __name__ == '__main__':

    s = input()
    n = int(input())

    result = repeatedString(s, n)

    print(result)

