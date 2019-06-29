#!/usr/bin/env python

with open('day-01.txt','r') as input_file:
  data = input_file.read()

class Building:
  floor = 0
  _step = 0
  entered_basement = 0

  def step( self ):
    self._step += 1

  def goUp( self ):
    self.step()
    self.floor += 1

  def goDown( self ):
    self.step()
    self.floor -= 1
    if ( ( not self.entered_basement ) and ( self.floor < 0 ) ):
      self.entered_basement = self._step

santa = Building()
for instruction in data:
  if ( instruction == '(' ):
    santa.goUp()
  elif ( instruction == ')' ):
    santa.goDown()

print "Santa ended up on floor", santa.floor
print "Santa entered the basement on step ", santa.entered_basement

