#!/usr/bin/env python3

def hourglassSum(a):
    sums = []
    for x in range(4):
        for y in range(4):
            sums.append(
                arr[y+0][x+0] + arr[y+0][x+1] + arr[y+0][x+2] +
                                arr[y+1][x+1] +
                arr[y+2][x+0] + arr[y+2][x+1] + arr[y+2][x+2]
            )
    return max(sums)

arr = []
for _ in range(6):
    arr.append(list(map(int, input().rstrip().split())))

print( hourglassSum(arr) )