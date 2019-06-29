#!/usr/bin/perl -w

use strict;
use warnings;

use Data::Dumper qw( Dumper );

my $pantry;

sub input {
  my $text = <<'EOF';
Frosting: capacity 4, durability -2, flavor 0, texture 0, calories 5
Candy: capacity 0, durability 5, flavor -1, texture 0, calories 8
Butterscotch: capacity -1, durability 0, flavor 5, texture 0, calories 6
Sugar: capacity 0, durability 0, flavor -2, texture 2, calories 1
EOF
  $pantry = {};
  foreach ( split "\n", $text ) {
    die unless m/^(.*?): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)$/;
    $pantry->{$1} = {
      capacity => $2,
      durability => $3,
      flavor => $4,
      texture => $5,
      calories => $6
    };
  }
}

sub bestRecipe {

  my $best = { v => 0, f => 0, c => 0, b => 0, s => 0 };

  my $limit = 100;
  my $value = 0;
  my ( $f, $c, $b, $s ); # frosting, candy, butterscotch, sugar
  foreach $f ( 0 .. $limit ) {
    my $limit_c = $limit - $f;
    foreach $c ( 0 .. $limit_c ) {
      my $limit_b = $limit - $f - $c;
      foreach $b ( 0 .. $limit_b ) {
        $s = $limit - $f - $c - $b;
        my @property_values;
        foreach my $property ( qw( capacity durability flavor texture ) ) {
          my $x = (
            ( $f * $pantry->{Frosting}->{$property} ) + 
            ( $c * $pantry->{Candy}->{$property} ) + 
            ( $b * $pantry->{Butterscotch}->{$property} ) + 
            ( $s * $pantry->{Sugar}->{$property} )
          );
          $x = 0 if ( $x < 0 );
          push @property_values, $x;
        }
        my $value = 1;
        map { $value *= $_ } @property_values;
        if ( $value > $best->{v} ) {
          my $calories = (
            ( $f * $pantry->{Frosting}->{calories} ) + 
            ( $c * $pantry->{Candy}->{calories} ) + 
            ( $b * $pantry->{Butterscotch}->{calories} ) + 
            ( $s * $pantry->{Sugar}->{calories} )
          );
          if ( $calories == 500 ) {
            print "f=$f,c=$c,b=$b,s=$s,v=$value\n";
            $best = { v => $value, f => $f, c => $c, b => $c, s => $s };
          }
        }
      }
    }
  }
  return $best;
}

input();
my $recipe = bestRecipe();

print "The best cookie requires the following ingredients:\n";
printf "  %3d tsp Frosting\n", $recipe->{f};
printf "  %3d tsp Candy\n", $recipe->{c};
printf "  %3d tsp Butterscotch\n", $recipe->{b};
printf "  %3d tsp Sugar\n", $recipe->{s};
printf "And has a total score of: %s\n", $recipe->{v};
