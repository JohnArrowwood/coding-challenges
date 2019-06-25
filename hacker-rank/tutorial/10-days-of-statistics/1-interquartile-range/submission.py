#!/usr/bin/env python3

from collections import Counter

class SortedVirtualArray:

    """Generator that turns a dictionary of {number:count} into an iterable / subscriptable 
       
       Allows you to treat it like an ordinary array, without using up the memory"""

    def __init__(self, values, counts ):

        # turn the arrays into a hash
        hash = Counter()
        for key,count in zip( values, counts ):
            hash[key] += count
        keys = sorted( hash.keys() )

        # for each value, calculate the virtual index that represents the next value
        # NOTE: this is one more than the last index that IS this value, to make it 
        # easier to just iterate while searching for the right value for an index
        offsets = []
        i = 0
        for key in keys:
            i += hash[key] 
            offsets.append( i )

        N = i

        self._dictionary = hash
        self._order = keys
        self._offsets = offsets
        self._virtual_size = N

        self._virtual_index = 0
        self._current_value_index = 0

    def __iter__(self):
        return self

    def __len__(self):
        return self._virtual_size

    def __next__(self):
        if self._virtual_index >= self._virtual_size: 
            raise StopIteration

        value = self._order[ self._current_value_index ]
        self.jumpTo( self._virtual_index + 1 )

        return value

    def __getitem__(self,where):
        # handle 
        if isinstance(where, int):
            if where >= 0:
                return self.jumpTo( where ).next()
            else:
                return self.jumpTo( self._virtual_size - where ).next()
        elif isinstance(where, slice):
            return [ self.jumpTo(i).next() for i in range( where.start, where.stop, where.step ) ]
        else:
            return [ self.jumpTo(i).next() for i in where ]

    def next(self):
        return self.__next__()

    def jumpTo(self,n):
        # if we try to jump past the end, jump just to the end
        if n > self._virtual_size: 
            n = self._virtual_size

        # if we try to jump backwards, reset value pointer back to the beginning
        if n < self._virtual_index: 
            self._current_value_index = 0  

        # set the internal position pointer
        self._virtual_index = n

        # advance the value pointer until it is pointing to the right value
        while self._current_value_index < len(self._order) and self._offsets[ self._current_value_index ] <= n:
            self._current_value_index += 1

        return self


    def take(self,n):
        self.jumpTo( self._virtual_index + n )

n = int(input())
values = list(map(int, input().rstrip().split()))
counts = list(map(int, input().rstrip().split()))

def quartileRange(v,c):

    a = SortedVirtualArray( values, counts )
    N = len(a)

    # handle boundary cases
    if N == 0 or N == 1: 
        return 0

    # calculate the values needed to do the calculation
    half = N // 2
    c = half // 2

    # calculate based on if there are an odd or even number of elements
    if half % 2 == 0:
        a,b,c,d = a[ c - 1, c, N - c - 1, N - c ]
        q1 = (a + b) / 2
        q3 = (c + d) / 2
    else: 
        q1,q3 = a[ c, N - c - 1 ]

    return q3 - q1

result = quartileRange( values, counts )

print( '{:.1f}'.format( result ) )