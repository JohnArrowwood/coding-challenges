#!/usr/bin/env python3

from fractions import Fraction

class Urn:
    RED = 'red'
    BLACK = 'black'
    def __init__(self,red,black):
        self.red  = red
        self.black = black

    def probability(self,which):
        total = self.red + self.black
        target = self.red if which == self.RED else self.black
        return target / total

X = Urn( red=4, black=3 )
Y = Urn( red=5, black=4 )
Z = Urn( red=4, black=4 )

xr = X.probability(Urn.RED)
yr = Y.probability(Urn.RED)
zr = Z.probability(Urn.RED)

x_is_b = (1-xr) * yr * zr
y_is_b = xr * (1-yr) * zr
z_is_b = xr * yr * (1-zr)

combined = x_is_b + y_is_b + z_is_b
as_fraction = Fraction(combined).limit_denominator()

print( as_fraction )