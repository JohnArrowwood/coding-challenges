# Interquartile Range

[Problem Statement](https://www.hackerrank.com/challenges/s10-interquartile-range)

## WARNING

It is not specified in the problem statement, but you can not assume that the list of values is distinct.
Some test cases include repeat values, and you have to handle them correctly

## Solution

At first glance, it looks like it is as simple as doing a quartiles() and subtracting the first from the last response.

But the input is not a full list, it is a compressed list, or histogram of values.  You COULD just expand it into a list,
but then they might throw at you something too big to keep in memory.  This solution will avoid that pitfall.

* We need to build the histogram from the inputs.
* Sort the histogram keys
* Count the total number of elements (sum of histogram values)
* Based on that, calculate which offsets we need to know to calculate everything
* Iterate the histogram in sorted order to find the values of the full uncompressed data set that we need

The calculation for the offsets is made complicated by whether or not the list is odd or even, and
whether the list count is also evenly divisible by 4.  Here are the possible list lengths and
how they effect the calculation: (capital letters denote the ones that are inspected)

* 1: [ A ] - response is 0
* 2: [ A ][ B ] - response is b - a
* 3: [ A ] b [ C ] - response is c - a
* 4: [ A B ][ C D ] - response is average(c-d) - average(a,b)
* 5: [ A B ] c [ D E ] - response is average(d-e) - average(a,b)
* 6: [ a B c ][ d E f ] - response is  e - b
* 7: [ a B c ] d [ e F g ] - response is f - b
* 8: [ a B C d ][ e F G h ] - response is average(f,g) - average(b,c)

All lists with a count greater than 8 are functionally equivalent to either 7 or 8, just with
more items in the list halves.  Breaking it down:

Calculating the offsets needed works like this

* first half = N // 2
* if half is odd, push half//2, N - half//2
* else, c = half//2, push c-1,c, N-c-1,N-c

Iterating the virtual array

* Keep a mental model of a virtual array
* And an actual array of the sorted keys
* Iterate the mental model without actually populating it by updating your index positions
* Loop until you have all the values you need

Calculating the final solution

* If the final array is 2 elements, the result is the second one minus the first
* If it is 4 elements, it is the average of the last two elements, minus the average of the first two

### Imperative Version

* Keeps a bunch of state variables and then simulates walking through the list

### Iterator Version

* Turn the iteration over the virtual list into a generator
* get the values by [ x for i,x in enumerate(generator) if i in offsets_needed ] 

Most of the logic in the Imparative version is hidden away inside the iterator,
and it allows you to use the list comprehension mechanism to interact with it.

Sadly, you loose the optimization of being able to avoid unnecessary calls.

### Optimized Iterator

* Add a .take(n) to skip past the next n elements in the generator in one step
* Add a .jumpTo(offset) to calculate how far to jump based on our current position, and .take() that many
* build the values like this: [ it.jumpTo(i).next() for i in offsets ]

### Subscriptable

* Once the .jumpTo() is defined, it can be used in .__getitem__ to make the object subscriptable

NOTE: I am ONLY going to build this version!
