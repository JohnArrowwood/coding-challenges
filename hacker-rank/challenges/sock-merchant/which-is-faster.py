#!/usr/bin/env python3

from v1_physical_analog_optimized import v1_physical_analog_optimized as v1
from v2_delegate_to_builtin       import v2_delegate_to_builtin       as v2
import time

n = int(input())
ar = list(map(int, input().rstrip().split()))

def evaluate(f):
    count = 1000000
    start = time.perf_counter_ns()
    for n in range(count):
        f(n,ar)
    stop = time.perf_counter_ns()
    elapsed = stop - start
    average = elapsed / count
    print( "start",start,"stop",stop,"elapsed",elapsed,"average",average )
    return average * 1e-6
    
print( "v1:", evaluate( v1 ) )
print( "v2:", evaluate( v2 ) )
