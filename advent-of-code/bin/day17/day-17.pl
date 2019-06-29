#!/usr/bin/perl -w

use strict;
use warnings;

my @sizes = qw( 33 14 18 20 45 35 16 35 1 13 18 13 50 44 48 6 24 41 30 42 );
my $length = @sizes;
my $max = 2 ** @sizes;

my $solution_hist = {};
my $num_permutations = 0;
my $target = 150;
for ( my $i = 1 ; $i < $max ; $i++ ) {
  my $capacity = 0;
  my $containers = 0;
  my @bits = split( "", sprintf( "%0${length}b", $i ) );
  for ( my $j = 0 ; $j < @bits ; $j++ ) {
    if ( $bits[$j] == 1 ) {
      $capacity += $sizes[$j] if $bits[$j] == 1;
      $containers++;
    }
  }
  if ( $capacity == $target ) {
    $num_permutations++;
    $solution_hist->{$containers}++;
  }
}

print $num_permutations, "\n";
my @order = sort { $a <=> $b } keys %$solution_hist;
print $solution_hist->{$order[0]}, "\n";

