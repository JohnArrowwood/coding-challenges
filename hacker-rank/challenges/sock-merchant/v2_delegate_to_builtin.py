#!/usr/bin/env python3

from collections import Counter

# Complete the sockMerchant function below.
def v2_delegate_to_builtin(n, pile):
    count = 0               # total pairs possible
    total = Counter( pile )

    for sock_type in total:
        count += total[sock_type] // 2

    return count

sockMerchant = v2_delegate_to_builtin

if __name__ == '__main__':

    n = int(input())
    ar = list(map(int, input().rstrip().split()))

    result = sockMerchant(n, ar)

    print(str(result))

