#!/usr/bin/env python3

n = int(input())
x = list(map(int, input().rstrip().split()))
w = list(map(int, input().rstrip().split()))

total = sum([ x[i] * w[i] for i in range(n) ]) 
sum_of_weights = sum(w)

weighted_mean = total / sum_of_weights

print( '{:.1f}'.format(weighted_mean) )

