#!/usr/bin/env python3

def minimumSwaps(arr):
    # count how many swaps we do while sorting the list
    swaps = 0

    # index the array so we can find values O(1)
    # could add a duplicate check - but I'm not going to
    index_of = { x[1]: x[0] for x in enumerate(arr) }

    # n is the value we are trying to put into position i
    n = 1

    # position i is the offset in the array we are trying to fill
    for i in range(len(arr)):

        # support gaps in the sequence - NOT REQUIRED, but good defensive programming
        while n not in index_of: 
            n += 1

        # where is it?
        pos = index_of[n]

        # if not already in position, swap it
        if ( pos != i ):
            a,b = i,pos
            arr[a],arr[b] = arr[b],arr[a]
            index_of[arr[b]] = b
            index_of[arr[a]] = a 
            swaps += 1

        # move on to searching for the next number
        n += 1

    return swaps


n = int(input())
arr = list(map(int, input().rstrip().split()))

result = minimumSwaps( arr )

print( result )

