# Minimum Swaps 2

[Problem Statement](https://www.hackerrank.com/challenges/minimum-swaps-2)

## Solution

At first blush, it looks almost like all you have to do is iterate the array
and count how many numbers are not in place.  And that would give you the
upper limit to how many swaps are necessary.  However, take for example
the following:

    [ 2 1 ]

In this case, there are two items out of place.  But it will only take one
swap to get them back into place.

The basic algorithm is like this:

* Loop through each position, starting at either the front or the back, it doesn't matter
* If it is already the right one, move on
* Otherwise, find the position of the right one, and swap it into position
* Return how many swaps you made

### Optimizing the Search

Finding the position of the next item is a sequential search.  But if you store a 
dictionary that holds the values as the keys, and the position as the value, then
you can do the lookup much quicker.  Then, you just need to make sure that your
swap routine also updates the index dictionary.

### Non-Sequential Input

If the input is missing any numbers, that will break the algorithm.  To handle
that condition, you need to differentiate between the index you are at, and the
number you are looking for.  This is good, anyway, because of the difference between
0-indexed arrays, and the input starting at 1.

Then, if you search for value 5 and there isn't one, just move on to 6.  Repeat until
you find what you are looking for.

If there are no missing values in the sequence, then the two indexes will always be
one apart.  But the algorithm will handle the unexpected.

### Duplicate values

The problem statement specifically says there are no duplicates.  But it only makes
sense to enforce this.  While building the index dictionary, assert that the key
does not exist before you put it in there.


