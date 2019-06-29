#!/usr/bin/perl -w

use strict;
use warnings;

sub input {
  my $text = <<'EOF';
Dancer can fly 27 km/s for 5 seconds, but then must rest for 132 seconds.
Cupid can fly 22 km/s for 2 seconds, but then must rest for 41 seconds.
Rudolph can fly 11 km/s for 5 seconds, but then must rest for 48 seconds.
Donner can fly 28 km/s for 5 seconds, but then must rest for 134 seconds.
Dasher can fly 4 km/s for 16 seconds, but then must rest for 55 seconds.
Blitzen can fly 14 km/s for 3 seconds, but then must rest for 38 seconds.
Prancer can fly 3 km/s for 21 seconds, but then must rest for 40 seconds.
Comet can fly 18 km/s for 6 seconds, but then must rest for 103 seconds.
Vixen can fly 18 km/s for 5 seconds, but then must rest for 84 seconds.
EOF
  my $map = {};
  foreach ( split "\n", $text ) {
    die unless m/^(.*?) can fly (\d+) km\/s for (\d+) seconds, but then must rest for (\d+) seconds./;
    $map->{$1} = { speed => $2, stamina => $3, recharge => $4 };
  }
  return $map;
}

sub distance {
  my $time = shift;
  my $cfg = shift;

  my $distance = 0;
  while ( $time ) {
    if ( $time >= $cfg->{stamina} ) {
      $distance += $cfg->{speed} * $cfg->{stamina};
      $time -= $cfg->{stamina};
    } else {
      $distance += $cfg->{speed} * $time;
      $time = 0;
    }
    if ( $time >= $cfg->{recharge} ) {
      $time -= $cfg->{recharge};
    } else {
      $time = 0;
    }
  }
  return $distance;
}

my $participants = input();
my @reindeer = sort keys %$participants;
print join( "\n", @reindeer ), "\n\n";
my $d = {};
map { $d->{$_} = distance( 2503, $participants->{$_} ) } @reindeer;
my @order = sort { $d->{$b} <=> $d->{$a} } @reindeer;
print "And the winner is . . .\n";
printf "%s - %d km\n", $order[0], $d->{$order[0]};

# part 2
# give one point to each reindeer who is in the lead after every second
my $points = {};
map { $points->{$_} = 0 } @reindeer;
my @order;
my $lead;
foreach my $i ( 1 .. 2503 ) {
  my $d = {};
  map { $d->{$_} = distance( $i, $participants->{$_} ) } @reindeer;
  @order = sort { $d->{$b} <=> $d->{$a} } @reindeer;
  $lead = $d->{$order[0]};
  foreach ( @reindeer ) {
    if ( $d->{$_} == $lead ) { $points->{$_} ++ }
  }
}

@order = sort { $points->{$b} <=> $points->{$a} } @reindeer;
printf "Under the new rules, %s would win with %d points\n",
  $order[0], $points->{$order[0]};

