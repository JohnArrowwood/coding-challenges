package day06;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class Command {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Consumer<Light> action;
	private static Pattern commandPattern = Pattern
			.compile("^(turn on|turn off|toggle) ([0-9]+),([0-9]+) through ([0-9]+),([0-9]+)");

	public Command(String s) {
		Matcher m = commandPattern.matcher(s);
		if (m.find()) {
			switch (m.group(1)) {
			case "turn on":
				action = (Light l) -> l.turn(true);
				break;
			case "turn off":
				action = (Light l) -> l.turn(false);
				break;
			case "toggle":
				action = (Light l) -> l.toggle();
				break;
			}
			x1 = Integer.parseInt(m.group(2));
			y1 = Integer.parseInt(m.group(3));
			x2 = Integer.parseInt(m.group(4));
			y2 = Integer.parseInt(m.group(5));
		}
	}

	public void execute(Display d) {
		d.change(x1, y1, x2, y2, action);
	}
}

interface Light {

	public Light create();

	public void turn(Boolean onOff);

	public void toggle();

	public int valueOf();
}

/* This type of light is either on or off */
class OnOff implements Light {

	private Boolean state = false;

	public Light create() {
		return new OnOff();
	}

	public void turn(Boolean onOff) {
		state = onOff;
	}

	public void toggle() {
		state = !state;
	}

	public int valueOf() {
		return (state ? 1 : 0);
	}
}

/* This type of light has a brightness to it */
class BrightnessControlled implements Light {
	private int state = 0;

	public Light create() {
		return new BrightnessControlled();
	}

	public void turn(Boolean onOff) {
		state += (onOff ? 1 : -1);
		if (state < 0) {
			state = 0;
		}
	}

	public void toggle() {
		state += 2;
	}

	public int valueOf() {
		return state;
	}
}

class Display {
	Light[][] lights = new Light[1000][1000];

	public Display(Light bulb) {
		int x, y;
		for (x = 0; x < 1000; x++) {
			for (y = 0; y < 1000; y++) {
				lights[x][y] = bulb.create();
			}
		}
	}

	public void change(int x1, int y1, int x2, int y2, Consumer<Light> f) {
		int x, y;
		for (x = x1; x <= x2; x++) {
			for (y = y1; y <= y2; y++) {
				f.accept(lights[x][y]);
			}
		}
	}

	public int totalValue() {
		int x, y;
		int total = 0;
		for (x = 0; x < 1000; x++) {
			for (y = 0; y < 1000; y++) {
				total += lights[x][y].valueOf();
			}
		}
		return total;
	}
}

public class Day06 {
	private static List<Command> actions = loadInput();

	private static List<Command> loadInput() {
		List<Command> inputs = new ArrayList<Command>();
		try {
			try (Stream<String> lines = Files.lines(Paths.get("input", "day-06.txt"), StandardCharsets.UTF_8)) {
				lines.forEach(cmd -> inputs.add(new Command(cmd)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputs;
	}

	public static void main(String[] args) {
		Display part1 = new Display(new OnOff());
		Display part2 = new Display(new BrightnessControlled());
		actions.stream().forEach(c -> {
			c.execute(part1);
			c.execute(part2);
		});
		System.out.println("In part 1, the number of lights on is:" + part1.totalValue());
		System.out.println("In part 2, the total brightness is:" + part2.totalValue());
	}
}
