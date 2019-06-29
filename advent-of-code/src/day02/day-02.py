#!/usr/bin/env python

def paperRequired( x, y, z ):
  x,y,z = sorted( [ x, y, z ] )
  return ( 2*x*y + 2*x*z + 2*y*z + x*y )

def ribbonRequired( x, y, z ):
  x,y,z = sorted( [ x, y, z ] )
  return ( 2*x + 2*y + x*y*z )

data = []
with open( "day-02.txt", "r" ) as input_file:
  for line in input_file:
    data.append( line.rstrip().split("x") )

total_paper  = sum( map( lambda n: paperRequired( int(n[0]), int(n[1]), int(n[2]) ), data ) )
total_ribbon = sum( map( lambda n: ribbonRequired( int(n[0]), int(n[1]), int(n[2]) ), data ) )
  
print total_paper, "Square feet of wrapping paper";
print total_ribbon, "Linear feet of ribbon";

