# Sock Merchant

[Problem Statement](https://www.hackerrank.com/challenges/sock-merchant/problem)

## Solution 1: By Physical Analog

If doing this problem by hand, I would open up some space in front of me.
I would pick up the socks one by one, look to see if I already have one of
that type in front of me.  If I do, I would pick that up, join them, and
toss it in a pile.  If not, I would lay it down, and move on to the next.
When I am done, I count the folded socks.  DONE.

To exactly mirror this, you need a set to represent the layed-out unpaired 
socks, and an array to represent the collected pairs.

However, we don't need to know the pairs, only the count, so as an 
optimization, we can trade the array for a counter.  Every time we find a
pair, discard it, and increment the counter.

## Solution 2: Delegation to Builtin

As a trick, you can also build a hash table to store the complete counts
of every kind of sock.  Then iterate over the values in the hash table,
and for each one, do integer division by 2, and add the result to the 
counter.

The advantage is that the construction of the hash table can be delegated
to the heavily optimized built-in, which may be able to do the job
faster than doing it in interpreted code

## Which is Faster?

Sometimes, delegation to Builtin is faster.  But in this case, given the 
simplicity of what is to be done, the optimized physical analog is
notably faster.  

When it comes to performance, don't assume, TEST! 
