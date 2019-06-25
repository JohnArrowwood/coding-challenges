#!/usr/bin/env python3

is_thundercloud = lambda cloud: cloud == 1

def v1_backwards(clouds):

    cloud = len(clouds)-1 # the cloud we are working back from
    start = 0
    jumps = 0

    while cloud > start:

        intended = cloud - 2            

        if intended < start or is_thundercloud( clouds[intended] ):
            intended = cloud - 1

        cloud = intended

        jumps += 1

    return jumps


jumpingOnClouds = v1_backwards

if __name__ == '__main__':

    n = int(input())
    c = list(map(int, input().rstrip().split()))

    result = jumpingOnClouds(c)

    print(result)

