#!/usr/bin/env python3

def v1_iteration( s, n ):

    count = 0
    pos_in_s = 0
    length_of_s = len(s)
    i = 0

    while i < n:
        if s[pos_in_s] == 'a':
            count += 1
        i += 1
        pos_in_s = ( pos_in_s + 1 ) % length_of_s

    return count

repeatedString = v1_iteration

if __name__ == '__main__':

    s = input()
    n = int(input())

    result = repeatedString(s, n)

    print(result)

