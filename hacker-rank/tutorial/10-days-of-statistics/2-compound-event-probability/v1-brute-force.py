#!/usr/bin/env python3

from fractions import Fraction

def balls( red, black ):
    return [ 'red' for _ in range(red) ] + [ 'black' for _ in range(black) ]

X = balls(4,3)
Y = balls(5,4)
Z = balls(4,4)

total = 0
valid = 0
for x in X:
    for y in Y:
        for z in Z:
            total += 1
            if ( ( x == 'black' and y == 'red'   and z == 'red' ) or
                 ( x == 'red'   and y == 'black' and z == 'red' ) or 
                 ( x == 'red'   and y == 'red'   and z == 'black' ) ):
                valid += 1

print( valid, "/", total, "=", valid / total )
print( "10 / 63 =", 10/63 )
print( "17 / 42 =", 17/42 )
print( "2 / 7 = ", 2/7 )
print( "31 / 126 =", 31/126 )

print( Fraction(valid/total).limit_denominator() )

