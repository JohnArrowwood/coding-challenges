package day03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

class Neighborhood {
	private Hashtable<String, Integer> homes;

	public Neighborhood() {
		homes = new Hashtable<String, Integer>();
	}

	public void deliver(int x, int y) {
		String key = x + "::" + y;
		Integer current = homes.getOrDefault(key, 0);
		homes.put(key, current + 1);
	}

	public int homesVisited() {
		return homes.size();
	}
}

class JollyOldElf {
	private int x;
	private int y;
	private Neighborhood homes;

	public JollyOldElf(Neighborhood toVisit) {
		x = 0;
		y = 0;
		homes = toVisit;
	}

	public void deliver() {
		homes.deliver(x, y);
	}

	public void move(char dir) {
		if (dir == '^')
			y--;
		else if (dir == 'v')
			y++;
		else if (dir == '<')
			x--;
		else if (dir == '>')
			x++;
	}

	public String report() {
		return "pos(" + x + "," + y + ")";
	}

}

public class Day03 {

	public static int firstYear(String instructions) {
		Neighborhood hood = new Neighborhood();

		JollyOldElf santa = new JollyOldElf(hood);
		santa.deliver();

		for (char dir : instructions.toCharArray()) {
			santa.move(dir);
			santa.deliver();
		}

		return hood.homesVisited();
	}

	public static int secondYear(String instructions) {
		Neighborhood hood = new Neighborhood();

		JollyOldElf santa = new JollyOldElf(hood);
		santa.deliver();

		JollyOldElf roboSanta = new JollyOldElf(hood);
		roboSanta.deliver();

		JollyOldElf active = santa;
		for (char dir : instructions.toCharArray()) {
			active.move(dir);
			active.deliver();
			active = (active == santa ? roboSanta : santa);
		}

		return hood.homesVisited();
	}

	public static void main(String[] args) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get("input", "day-03.txt"));
		String instructions = new String(encoded, StandardCharsets.UTF_8);

		System.out.println("In year one, Santa delivers to " + firstYear(instructions) + " homes");
		System.out.println("In year two, Santa and RoboSanta deliver to " + secondYear(instructions) + " homes");
	}
}
