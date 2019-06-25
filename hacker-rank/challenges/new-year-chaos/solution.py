#!/usr/bin/env python3

def minimumBribes(q):
    
    bribes = 0

    # position swapping utility function
    # that keeps track of how many times it is called    
    def swap(a,b): 
        nonlocal bribes
        q[a], q[b] = q[b], q[a]
        bribes += 1

    # start at the end of the queue
    # work your way towards the front
    for pos in range(len(q),0,-1):

        # account for the difference between 1-indexed and 0-indexed arrays
        i = pos - 1

        # if the person that belongs here isn't here, 
        # put him/her back here
        if q[i] != pos:
        
            # if it will take more than 2 swaps, the queue is in an illegal state
            if ( ( pos > 2 and q[i-2] != pos ) and q[i-1] != pos ):
                raise Exception("Somebody broke the rules")

            # if the person has bribed twice, move them back once
            if ( q[i-2] == pos ):
                swap( i-2, i-1 )

            # if the person has bribed at least once, move them back where they belong
            if ( q[i-1] == pos ):
                swap( i-1, i )

    # at this point, everything should be swapped back to where it belongs
    # and the number of swaps represents the minimum number of bribes
    # that it took to get the list into the state it was when we started
    return bribes

count = int(input())
for n in range(count):
    size = int(input())
    queue = list(map(int, input().rstrip().split()))
    try:
        print( minimumBribes(queue) )
    except:
        print( "Too chaotic" )


