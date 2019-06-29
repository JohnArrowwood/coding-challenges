package day07;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.stream.Stream;

class Circuit {
	private Hashtable<String, String> wires = new Hashtable<String, String>();
	private Hashtable<String, Integer> values = new Hashtable<String, Integer>();

	public void wire(String name, String connections) {
		wires.put(name, connections);
	}

	public void reset() {
		values.clear();
	}

	public int valueOf(String wire) {
		int result = 0;
		if (values.containsKey(wire)) {
			return values.get(wire);
		}

		if (!wires.containsKey(wire)) {
			result = Integer.parseInt(wire);
			values.put(wire, result);
			return result;
		}

		String definition = wires.get(wire);
		String[] parts = definition.split(" ");
		switch (parts.length) {
		case 1:
			result = valueOf(parts[0]);
			break;
		case 2:
			if (parts[0].equals("NOT")) {
				result = ~valueOf(parts[1]);
			}
			break;
		case 3:
			int a = valueOf(parts[0]);
			int b = valueOf(parts[2]);
			switch (parts[1]) {
			case "AND":
				result = a & b;
				break;
			case "OR":
				result = a | b;
				break;
			case "XOR":
				result = a ^ b;
				break;
			case "RSHIFT":
				result = a >> b;
				break;
			case "LSHIFT":
				result = a << b;
				break;
			}
			break;
		}

		values.put(wire, result);
		return result;
	}
}

public class Day07 {

	public static void main(String[] args) throws IOException {
		Circuit circuit = new Circuit();

		try (Stream<String> lines = Files.lines(Paths.get("input", "day-07.txt"), StandardCharsets.UTF_8)) {
			lines.forEach(instructions -> {
				String[] part = instructions.split(" -> ");
				circuit.wire(part[1], part[0]);
			});
		}

		int a = circuit.valueOf("a");

		circuit.reset();
		circuit.wire("b", Integer.toString(a));

		int a_2 = circuit.valueOf("a");

		System.out.println("value of a = " + a);
		System.out.println("part2,   a = " + a_2);

	}

}
