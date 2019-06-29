#!/usr/bin/perl -w

use strict;
use warnings;

my $input = <<'EOF';
AlphaCentauri to Snowdin = 66
AlphaCentauri to Tambi = 28
AlphaCentauri to Faerun = 60
AlphaCentauri to Norrath = 34
AlphaCentauri to Straylight = 34
AlphaCentauri to Tristram = 3
AlphaCentauri to Arbre = 108
Snowdin to Tambi = 22
Snowdin to Faerun = 12
Snowdin to Norrath = 91
Snowdin to Straylight = 121
Snowdin to Tristram = 111
Snowdin to Arbre = 71
Tambi to Faerun = 39
Tambi to Norrath = 113
Tambi to Straylight = 130
Tambi to Tristram = 35
Tambi to Arbre = 40
Faerun to Norrath = 63
Faerun to Straylight = 21
Faerun to Tristram = 57
Faerun to Arbre = 83
Norrath to Straylight = 9
Norrath to Tristram = 50
Norrath to Arbre = 60
Straylight to Tristram = 27
Straylight to Arbre = 81
Tristram to Arbre = 90
EOF

my $distance_map = {};

sub parse {
	my $s = shift;
	$s =~ m/^(.*?) to (.*?) = (\d+)$/;
	$distance_map->{$1}{$2} = $3;
	$distance_map->{$2}{$1} = $3;
}

foreach my $s ( split( "\n", $input ) ) {
	parse( $s );
}

my $memoized_sort = {};
sub sort_nodes {
	my @nodes = sort @_;
	my $key = join '->', @nodes;
	if ( exists( $memoized_sort->{$key} ) ) {
		return @{$memoized_sort->{$key}};
	}
	my %weight;
	foreach my $x ( @nodes ) {
		foreach my $y ( @nodes ) {
			if ( $x ne $y ) {
				if ( ! defined $weight{$x} || $weight{$x} > $distance_map->{$x}{$y} ) {
					$weight{$x} = $distance_map->{$x}{$y}
				}
			}
		}
	}
	my @order = sort { $weight{$a} <=> $weight{$b} } @nodes;
	$memoized_sort->{$key} = \@order;
	return @order;
}

sub heuristic_path {
	if ( @_ > 1 ) {
		my @order = sort_nodes( @_ );
		my $first = shift @order;
		return ( $first, heuristic_path( @order ) );
	} else {
		return ( @_ );
	}
}

sub path_length {
	my @nodes = @_;
	if ( @nodes < 2 ) {
		return 0;
	} else {
		my $a = shift @nodes;
		my $b = $nodes[0];
		return $distance_map->{$a}{$b} + path_length( @nodes );
	}
}	

sub without {
	my $element = shift;
	my @nodes = @_;
	my @remaining;
	foreach my $x ( @nodes ) {
		if ( $x ne $element ) {
			push @remaining, $x;
		}
	}
	return @remaining;
}
sub shorter_than { 
	my $distance_so_far = shift;
	my $distance_to_beat = shift;
	my $at_node = shift;
	my @remaining_nodes = @_;

	if ( $distance_so_far >= $distance_to_beat ) {
		return ( undef, undef );
	}

	if ( @remaining_nodes == 0 && $distance_so_far < $distance_to_beat ) {
		return ( $distance_so_far, $at_node );
	}

	my @best_order = ( defined $at_node ? ( $at_node, @remaining_nodes ) : @remaining_nodes );

	my @order = sort_nodes( @remaining_nodes );
	my $improved = 0;
	foreach my $node ( @order ) {
		my @remaining = without( $node, @order );
		my $distance_to_add = ( defined $at_node ? $distance_map->{$at_node}{$node} : 0 );
		my ( $new_dist, @new_order ) = shorter_than( $distance_so_far + $distance_to_add, $distance_to_beat, $node, @remaining );
		if ( defined $new_dist ) {
			$improved = 1;
			$distance_to_beat = $new_dist;
			@best_order = ( defined $at_node ? ( $at_node, @new_order ) : @new_order );
		}
	}	
	if ( $improved ) {
		return ( $distance_to_beat, @best_order );
	} else {
		return ( undef, undef );
	}
}

sub shortest_path {
	my @places = @_;
	my @best_order = heuristic_path( @places );
	my $best_dist = path_length( @best_order );
	print join( " -> ", @best_order ), " = ", $best_dist, "\n";

	my ( $new_dist, @new_order ) = shorter_than( 0, $best_dist, undef, @best_order );
	if ( defined $new_dist ) {
		return ( $new_dist, @new_order );
	} else {
		return ( $best_dist, @best_order );
	}
}	

sub longest_path {
	my $distance_so_far = shift;
	my $distance_to_beat = shift;
	my $at_node = shift;
	my @remaining_nodes = @_;

	if ( @remaining_nodes == 0 ) {
		if ( $distance_so_far < $distance_to_beat ) {
			return ( undef, undef );
		} else {
			return ( $distance_so_far, $at_node );
		}
	}

	my @best_order = @_;

	my $improved = 0;
	foreach my $node ( @remaining_nodes ) {
		my @remaining = without( $node, @remaining_nodes );
		my $distance_to_add = ( defined $at_node ? $distance_map->{$at_node}{$node} : 0 );
		my ( $new_dist, @new_order ) = longest_path( $distance_so_far + $distance_to_add, $distance_to_beat, $node, @remaining );
		if ( defined $new_dist ) {
			$improved = 1;
			$distance_to_beat = $new_dist;
			@best_order = ( defined $at_node ? ( $at_node, @new_order ) : @new_order );
		}
	}
	if ( $improved ) {
		return ( $distance_to_beat, @best_order );
	} else {
		return ( undef, undef );
	}
}

my ( $dist, @order ) = shortest_path( keys %$distance_map );
printf "Shortest [%d] %s\n", $dist, join( ' -> ', @order );

( $dist, @order ) = longest_path( 0, 0, undef, keys %$distance_map );
printf "Longest  [%d] %s\n", $dist, join( ' -> ', @order );
