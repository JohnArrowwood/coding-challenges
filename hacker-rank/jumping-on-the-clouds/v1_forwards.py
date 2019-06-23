#!/usr/bin/env python3

is_thundercloud = lambda cloud: cloud == 1

def v1_forward(clouds):

    cloud = 0 # the cloud we are standing on
    target = len(clouds)-1
    jumps = 0

    while cloud < target:

        intended = cloud + 2            

        if intended > target or is_thundercloud( clouds[intended] ):
            intended = cloud + 1

        cloud = intended

        jumps += 1

    return jumps


jumpingOnClouds = v1_forward

if __name__ == '__main__':

    n = int(input())
    c = list(map(int, input().rstrip().split()))

    result = jumpingOnClouds(c)

    print(result)

