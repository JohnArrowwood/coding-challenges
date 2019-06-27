#!/usr/bin/env python3

rolls = [ (a,b) for a in range(1,7) for b in range(1,7) ]
total = len(rolls)
valid = sum([1 for (a,b) in rolls if a+b<=9 ])
print( "sum < 9 =", valid)
print("total    =", total)