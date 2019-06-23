#!/usr/bin/env python3

def v2_transitions_below_starting_altitude(n, steps):

    alt     = 0 # how high we are
    valleys = 0 # how many valleys we have traversed

    for step in steps:
        if step == 'U':
            alt += 1
        elif step == 'D':
            if alt == 0:
                valleys += 1
            alt -= 1

    return valleys

countingValleys = v2_transitions_below_starting_altitude

if __name__ == '__main__':

    n = int(input())
    s = input()

    result = countingValleys(n, s)

    print(result)

