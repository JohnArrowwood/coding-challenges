#!/usr/bin/env python

class Houses:
	all = {}

	def deliver( self, x, y ):
		key = str(x) + '::' + str(y)
		self.all[key] = 1 + ( self.all[key] if (key in self.all) else 0 )

	def numHouses( self ):
		total = 0;
		for key in self.all:
			total += 1
		return total

	def __init__( self ):
		self.all = {}

class Santa:
	x = 0
	y = 0
	houses = None

	def deliver( self ):
		self.houses.deliver( self.x, self.y )

	def move( self, dir ):
		if   ( dir == '<' ): self.x -= 1
		elif ( dir == '>' ): self.x += 1
		elif ( dir == '^' ): self.y -= 1
		elif ( dir == 'v' ): self.y += 1

	def report( self ):
		return "pos(" + str(self.x) + "," + str(self.y) + ")"

	def __init__( self, houses ):
		self.x = 0
		self.y = 0
		self.houses = houses

def thisYear( instructions ):
	houses = Houses()
	print "Houses visited:", houses.numHouses()

	santa = Santa( houses )
	santa.deliver()
	for dir in instructions:
		santa.move( dir )
		santa.deliver()
	return houses.numHouses()

def nextYear( instructions ):
	houses = Houses()
	print "Houses visited:", houses.numHouses()

	santa = Santa( houses )
	santa.deliver()

	roboSanta = Santa( houses )
	roboSanta.deliver()

	current = santa
	for dir in instructions:
		print "\n"
		print "Instruction: ", dir
		current.move( dir )
		current.deliver()
		current = roboSanta if ( current == santa ) else santa
		print "Santa is at", santa.report()
		print "RoboSanta is at", roboSanta.report()
		print "Houses visited:", houses.numHouses()
	
	return houses.numHouses()

with open( 'day-03.txt', 'r' ) as input_file:
	data = input_file.read()

print "In the first year, Santa delivers to ", thisYear( data )
print "In the second year, Santa and Robo-Santa delivers to ", nextYear( data ) 
	
	
			

		
