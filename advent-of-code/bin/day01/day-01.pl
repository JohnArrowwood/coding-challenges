#!/usr/bin/perl -w

use strict;
use warnings;

my $input = '';
open IN, '<', 'day-01.txt';
while ( <IN> ) { $input .= $_; }
close IN;

my $floor = 0;
my @instructions = split( "", $input );
my $step = 0;
my $entered_basement = 0;

while ( @instructions ) {
  my $do = shift @instructions;
  $step++;
  if ( $do eq '(' ) {
    $floor++;
  } elsif ( $do eq ')' ) {
    $floor--;
    if ( ! $entered_basement && $floor < 0 ) {
      $entered_basement = 1;
      print "Santa entered the basement at step $step\n";
    }
  }
}
print "Santa ended up on floor $floor\n";
