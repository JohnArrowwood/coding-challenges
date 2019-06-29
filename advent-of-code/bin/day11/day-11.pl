#!/usr/bin/perl -w

use strict;
use warnings;

sub increment {
  my $password = shift;
  my @password = split "", $password;
  my $i = $#password;
  my $done = 0;
  while ( ! $done ) {
    if ( $password[$i] =~ m/[a-y]/ ) {
      $password[$i]++;
      $done = 1;
    } elsif ( $password[$i] eq 'z' ) {
      $password[$i] = 'a';
      $i--;
      if ( $i < 0 ) {
        $done = 1;
      }
    } else {
      die "Invalid password: $password";
    }
  }
  return join( '', @password );
}


sub isValid {
  my $password = shift;
  return $password =~ m/
    ^
    (?!.*?[iol])
    (?=.*?(?:abc|bcd|cde|def|efg|fgh|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz))
    (?=.*?([a-z])\1.*?([a-z])\2)
  /x;
}

sub newPassword {
  my $password = shift;
  do {
    $password = increment( $password );
  } until isValid( $password );
  return $password;
}

my $p1 = 'vzbxkghb';
my $p2 = newPassword( $p1 );
my $p3 = newPassword( $p2 );

print "p1 = ", $p1, "\n";
print "p2 = ", $p2, "\n";
print "p3 = ", $p3, "\n";
