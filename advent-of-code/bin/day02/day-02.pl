#!/usr/bin/perl -w

use strict;
use warnings;

sub input {
  my @list;
  open IN, '<', 'day-02.txt' or die $!;
  while ( <IN> ) {
    chomp;
    push @list, [ split( "x", $_ ) ];
  }
  close IN;
  return \@list;
}

sub paperRequired {
  my ( $x, $y, $z ) = sort { $a <=> $b } @_;
  return ( 2*$x*$y + 2*$x*$z + 2*$y*$z + $x*$y );
}

sub ribbonRequired {
  my ( $x, $y, $z ) = sort { $a <=> $b } @_;
  return ( 2*$x + 2*$y + $x*$y*$z );
}

my $sizes = input();
my $total_paper = 0;
my $total_ribbon = 0;
foreach ( @$sizes ) {
  $total_paper += paperRequired( @$_ );
  $total_ribbon += ribbonRequired( @$_ );
}

print $total_paper, " Square Feet of Wrapping Paper\n";
print $total_ribbon, " Linear Feet of Ribbon\n";

