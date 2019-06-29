#!/usr/bin/env ruby

class Santa
  def initialize()
    @floor = 0
    @step = 0
    @entered_basement = 0
  end
  def followInstruction(i)
    @step += 1
    if ( i == '(' )
      @floor += 1
    elsif ( i == ')' )
      @floor -= 1
      if ( @entered_basement == 0 and @floor < 0 )
        @entered_basement = @step
      end
    end
  end
  def instructions( s )
    s.split("").each do |c|
      self.followInstruction(c)
    end
  end
  def onFloor
    @floor
  end
  def enteredBasement
    @entered_basement
  end
end

data = File.open( "day-01.txt", "r" ).read

santa = Santa.new
santa.instructions( data )
print "Santa ended up on floor #{santa.onFloor}\n"
print "Santa entered the basement on step #{santa.enteredBasement}\n"

