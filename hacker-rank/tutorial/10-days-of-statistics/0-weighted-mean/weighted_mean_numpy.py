#!/usr/bin/env python3

import numpy as np

n = int(input())
x = np.array(list(map(int, input().rstrip().split())))
w = np.array(list(map(int, input().rstrip().split())))

weighted_mean = np.average(x,weights=w)

print( '{:.1f}'.format(weighted_mean) )

