#!/usr/bin/perl -w

use strict;
use warnings;

sub input {
  my $text = <<'EOF';
Alice would lose 2 happiness units by sitting next to Bob.
Alice would lose 62 happiness units by sitting next to Carol.
Alice would gain 65 happiness units by sitting next to David.
Alice would gain 21 happiness units by sitting next to Eric.
Alice would lose 81 happiness units by sitting next to Frank.
Alice would lose 4 happiness units by sitting next to George.
Alice would lose 80 happiness units by sitting next to Mallory.
Bob would gain 93 happiness units by sitting next to Alice.
Bob would gain 19 happiness units by sitting next to Carol.
Bob would gain 5 happiness units by sitting next to David.
Bob would gain 49 happiness units by sitting next to Eric.
Bob would gain 68 happiness units by sitting next to Frank.
Bob would gain 23 happiness units by sitting next to George.
Bob would gain 29 happiness units by sitting next to Mallory.
Carol would lose 54 happiness units by sitting next to Alice.
Carol would lose 70 happiness units by sitting next to Bob.
Carol would lose 37 happiness units by sitting next to David.
Carol would lose 46 happiness units by sitting next to Eric.
Carol would gain 33 happiness units by sitting next to Frank.
Carol would lose 35 happiness units by sitting next to George.
Carol would gain 10 happiness units by sitting next to Mallory.
David would gain 43 happiness units by sitting next to Alice.
David would lose 96 happiness units by sitting next to Bob.
David would lose 53 happiness units by sitting next to Carol.
David would lose 30 happiness units by sitting next to Eric.
David would lose 12 happiness units by sitting next to Frank.
David would gain 75 happiness units by sitting next to George.
David would lose 20 happiness units by sitting next to Mallory.
Eric would gain 8 happiness units by sitting next to Alice.
Eric would lose 89 happiness units by sitting next to Bob.
Eric would lose 69 happiness units by sitting next to Carol.
Eric would lose 34 happiness units by sitting next to David.
Eric would gain 95 happiness units by sitting next to Frank.
Eric would gain 34 happiness units by sitting next to George.
Eric would lose 99 happiness units by sitting next to Mallory.
Frank would lose 97 happiness units by sitting next to Alice.
Frank would gain 6 happiness units by sitting next to Bob.
Frank would lose 9 happiness units by sitting next to Carol.
Frank would gain 56 happiness units by sitting next to David.
Frank would lose 17 happiness units by sitting next to Eric.
Frank would gain 18 happiness units by sitting next to George.
Frank would lose 56 happiness units by sitting next to Mallory.
George would gain 45 happiness units by sitting next to Alice.
George would gain 76 happiness units by sitting next to Bob.
George would gain 63 happiness units by sitting next to Carol.
George would gain 54 happiness units by sitting next to David.
George would gain 54 happiness units by sitting next to Eric.
George would gain 30 happiness units by sitting next to Frank.
George would gain 7 happiness units by sitting next to Mallory.
Mallory would gain 31 happiness units by sitting next to Alice.
Mallory would lose 32 happiness units by sitting next to Bob.
Mallory would gain 95 happiness units by sitting next to Carol.
Mallory would gain 91 happiness units by sitting next to David.
Mallory would lose 66 happiness units by sitting next to Eric.
Mallory would lose 75 happiness units by sitting next to Frank.
Mallory would lose 99 happiness units by sitting next to George.
EOF
  my $map = {};
  foreach ( split( "\n", $text ) ) {
    die unless m/^(.*?) would (gain|lose) (\d+) happiness units by sitting next to (.*?)\.$/;
    $map->{$1}{$4} = $3 * ( $2 eq 'gain' ? +1 : -1 );
  }
  return $map;
}

sub without {
  my $which = shift;
  my @result;
  foreach ( @_ ) {
    next if $_ eq $which;
    push @result, $_;
  }
  return \@result;
}

sub bestPermutation {
  my $value_func = shift;
  my $comparison_func = shift;
  my @values = @_;

  my $f;
  $f = sub {
    my $left = shift;
    my $right = shift;

    if ( @$right == 1 ) {
      return ( &{$value_func}( @$left, @$right ), @$right );
    }

    my @values;
    foreach my $item ( @$right ) {
      my ( $v, @o ) = &$f( [ @$left, $item ], without( $item, @$right ) );
      push @values, { value => $v, order => [ $item, @o ] };
    }
    @values = sort { &$comparison_func( $a->{value}, $b->{value} ) } @values;
    return ( $values[0]->{value}, @{$values[0]->{order}} );
  };

  return &{$f}( [], [ @values ] );
}

sub valueOf {
  my $map = shift;

  my $f = sub {
    my @seating = @_;
    my $last = $#seating;
    my @values;
    for ( my $i = 0 ; $i < @seating ; $i++ ) {
      my $me = $seating[$i];
      my $left  = $seating[( $i > 0 ? $i - 1 : $last )];
      my $right = $seating[( $i < $last ? $i + 1 : 0 )];
      push @values, ( $map->{$me}{$left}  || 0 );
      push @values, ( $map->{$me}{$right} || 0 );
    }
    my $total = 0;
    map { $total += $_ } @values;
    return $total;
  };
  return $f;
}

sub biggerIsBetter {
  my $left = shift;
  my $right = shift;
  return $right <=> $left;
}

my $map = input();
my @people = keys %$map;
my ( $score, @seating ) = bestPermutation( valueOf( $map ), \&biggerIsBetter, @people );

printf "Total Happiness goes up by %d if the seating arrangement is:\n%s\n",
  $score, join( ', ', @seating );

push @people, 'ME';
( $score, @seating ) = bestPermutation( valueOf( $map ), \&biggerIsBetter, @people );
printf "Total Happiness goes up by %d if the seating arrangement is:\n%s\n",
  $score, join( ', ', @seating );


