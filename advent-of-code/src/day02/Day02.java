package day02;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Gift {
	private int x;
	private int y;
	private int z;

	public Gift(String s) {
		String[] part = s.split("x");
		int[] d = new int[3];
		for (int i = 0; i < 3; i++) {
			d[i] = Integer.parseInt(part[i]);
		}
		Arrays.sort(d); // put them in ascending order so we can reason
						// about them
		x = d[0];
		y = d[1];
		z = d[2];
	}

	public int wrappingPaper() {
		return (2 * (x * y + x * z + y * z) + x * y);
	}

	public int ribbon() {
		return (2 * (x + y) + x * y * z);
	}
}

public class Day02 {

	public static List<Gift> load() throws IOException {
		List<Gift> gifts = new ArrayList<Gift>();

		try (Stream<String> lines = Files.lines(Paths.get("input", "day-02.txt"), StandardCharsets.UTF_8)) {
			lines.forEach(size -> gifts.add(new Gift(size)));
		}

		return gifts;
	}

	public static void main(String[] args) throws IOException {
		List<Gift> gifts = load();

		long total_wrapping_paper = 0;
		long total_ribbon = 0;

		for (Gift g : gifts) {
			total_wrapping_paper += g.wrappingPaper();
			total_ribbon += g.ribbon();
		}

		System.out.println("The elves need " + total_wrapping_paper + " square feet of wrapping paper");
		System.out.println("And " + total_ribbon + " linear feet of ribbon");
	}
}
