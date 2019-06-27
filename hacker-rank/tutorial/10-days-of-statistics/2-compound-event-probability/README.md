# Day 2: Compound Event Probabilyt

[Problem Statement](https://www.hackerrank.com/challenges/s10-mcq-3)

## Solution 1 - Brute Force

* Define 3 arrays.  
* Into each array, put the appropriate number of items
* Iterate over every possible combination and count how many result in 2 reds and a black

## Solution 2 - Mathematical

* Calculate the probability that the first one is black and the next two are red
* Repeat for the second one being black
* And again for the third one
* Sum thier probabilities

* X = 4 red, 3 black, probability of black is 3/7.  Red is 4/7
* Y = 5 red, 4 black, or 4/9, 5/9
* Z = 4 red, 4 black, or 4/8, 4/8

* black(3/7) * red(5/9) * red(4/8) = 60 / 504
* red(4/7) * black(4/9) * red(4/8) = 64 / 504
* red(4/7) * red(5/9) * black(4/8) = 80 / 504
* Total = 204 / 504 = 102 / 252 = 51 / 126

None of which are the right answer according to the available options, most likely because of repeats