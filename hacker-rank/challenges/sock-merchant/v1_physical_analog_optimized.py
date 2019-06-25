#!/usr/bin/env python3

# Complete the sockMerchant function below.
def v1_physical_analog_optimized(n, pile):
    count    = 0     # total pairs seen
    unpaired = set() # total unpaired socks we have in front of us

    for sock in pile:
        if sock in unpaired:
            count += 1
            unpaired.remove(sock)
        else:
            unpaired.add(sock)

    return count

sockMerchant = v1_physical_analog_optimized

if __name__ == '__main__':

    n = int(input())
    ar = list(map(int, input().rstrip().split()))

    result = sockMerchant(n, ar)

    print(str(result))

