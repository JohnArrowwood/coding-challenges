package day09;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class Distance {
	private Hashtable<String, Integer> between = new Hashtable<String, Integer>();
	private Set<String> cities = new HashSet<String>();

	private String key(String a, String b) {
		return (a.compareTo(b) <= 0 ? (a + "<->" + b) : (b + "<->" + a));
	}

	public void set(String a, String b, int distance) {
		cities.add(a);
		cities.add(b);
		String k = key(a, b);
		between.put(k, distance);
	}

	public int get(String a, String b) {
		return between.getOrDefault(key(a, b), 0);
	}

	public String[] cities() {
		return cities.toArray(new String[cities.size()]);
	}
}

class Route {
	private String[] stops;
	private int distance;

	public Route(String[] order, int traveled) {
		stops = order;
		distance = traveled;
	}

	public String[] getOrder() {
		return stops;
	}

	public int getDistance() {
		return distance;
	}

	public String toString() {
		return Arrays.toString(stops) + " = " + distance;
	}
}

public class Day09 {

	private static Distance distance = new Distance();
	private static String[] cities = loadCities();

	private static String[] addTo(String[] list, String s) {
		String[] result = new String[list.length + 1];
		for (int i = 0; i < list.length; i++) {
			result[i] = list[i];
		}
		result[result.length - 1] = s;
		return result;
	}

	private static String[] without(String s, String[] list) {
		// WARNING: if s does not appear in list, this will throw an array index
		// out of bounds exception
		// But as used here, it will never do that
		String[] result = new String[list.length - 1];
		int j = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i] != s) {
				result[j] = list[i];
				j++;
			}
		}
		return result;
	}

	private static Route shortestRoute(String[] visited, int distance_so_far, int distance_to_beat,
			String[] remaining) {

		// Are we done?
		if (remaining.length == 0) {
			if (distance_to_beat == 0 || distance_so_far < distance_to_beat) {
				return new Route(visited, distance_so_far);
			} else {
				return null;
			}
		}
		// Is it hopeless?
		else if (distance_to_beat > 0 && distance_so_far > distance_to_beat) {
			return null;
		}
		// the city we are arriving from, if any
		String from = null;

		// if this is not the first time in, sort all remaining nodes relative
		// to the last visited so we are more likely to find the shortest
		// distance in the fewest tries
		if (visited.length > 0) {
			String last = from = visited[visited.length - 1];
			Arrays.sort(remaining, (a, b) -> distance.get(last, a) - distance.get(last, b));
		}

		Route result = null;

		// process every remaining node, one at a time
		for (String city : remaining) {
			int new_distance = distance_so_far + (from == null ? 0 : distance.get(from, city));
			String[] new_visited = addTo(visited, city);
			String[] new_remaining = without(city, remaining);
			Route new_shortest = shortestRoute(new_visited, new_distance, distance_to_beat, new_remaining);
			if (new_shortest != null) {
				result = new_shortest;
				distance_to_beat = result.getDistance();
			}
		}
		return result;
	}

	private static Route longestRoute(String[] visited, int distance_so_far, int distance_to_beat, String[] remaining) {
		// Are we done?
		if (remaining.length == 0) {
			if (distance_to_beat == 0 || distance_so_far > distance_to_beat) {
				return new Route(visited, distance_so_far);
			} else {
				return null;
			}
		}

		// the city we are arriving from, if any
		String from = (visited.length == 0 ? null : visited[visited.length - 1]);
		Route result = null;

		// process every remaining node, one at a time
		for (String city : remaining) {
			int new_distance = distance_so_far + (from == null ? 0 : distance.get(from, city));
			String[] new_visited = addTo(visited, city);
			String[] new_remaining = without(city, remaining);
			Route new_longest = longestRoute(new_visited, new_distance, distance_to_beat, new_remaining);
			if (new_longest != null) {
				result = new_longest;
				distance_to_beat = result.getDistance();
			}
		}
		return result;

	}

	private static String[] loadCities() {
		Pattern parser = Pattern.compile("^(.*?) to (.*?) = (\\d+)\\s*$");
		try {
			try (Stream<String> lines = Files.lines(Paths.get("input", "day-09.txt"), StandardCharsets.UTF_8)) {
				lines.forEach(s -> {
					Matcher m = parser.matcher(s);
					if (m.find()) {
						distance.set(m.group(1), m.group(2), Integer.parseInt(m.group(3)));
					}
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return distance.cities();
	}

	public static void main(String[] args) {
		System.out.println(cities);
		Route shortest = shortestRoute(new String[0], 0, 0, cities);
		System.out.println(shortest);
		Route longest = longestRoute(new String[0], 0, 0, cities);
		System.out.println(longest);

	}

}
