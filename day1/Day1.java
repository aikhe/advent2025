import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

record RotationValue(char direction, int steps) {
}

public class Day1 {
	public static void main(String[] args) {
		System.out.println("The dial starts by pointing at 50.");

		try {
			Rotation rotation = new Rotation("input.txt", 50, 100);

			rotation.run();
		} catch (FileNotFoundException e) {
			System.out.println("[Error] File not found: " + e.getMessage());
		}
	}
}

class Rotation {
	private final String fileName;
	private int pointer;
	private final int cap;
	private int zero_count;
	private int points;

	public Rotation(String fileName, int pointer, int cap) {
		this.fileName = fileName;
		this.pointer = pointer;
		this.cap = cap;
	}

	public void run() throws FileNotFoundException {
		List<String> lines = parseFile();
		for (String line : lines) {
			RotationValue value = parseLine(line);

			if (value.direction() == 'R') {
				pointer = calcRight(value.steps());
			} else if (value.direction() == 'L') {
				pointer = calcLeft(value.steps());
			}

			if (pointer == 0) {
				zero_count++;
			}

			System.out.println("The dial is rotated " + value.direction() + value.steps() + " to point at " + pointer);
		}

		System.out.println("Total zero: " + zero_count + ", Points: " + points);
		System.out.println("All: " + (zero_count + points));
	}

	public List<String> parseFile() throws FileNotFoundException {
		List<String> lines = new ArrayList<>();
		File file = new File(fileName);

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
		}

		return lines;
	}

	public RotationValue parseLine(String input) {
		char direction = input.charAt(0);
		int steps = Integer.parseInt(input.substring(1));

		return new RotationValue(direction, steps);
	}

	public int calcRight(int steps) {
		int point = (pointer + steps) / cap;
		points = points + point;

		return (pointer + steps) % cap;
	}

	public int calcLeft(int steps) {
		int val = (pointer - steps) % cap;
		while (val < 0) {
			val += cap;
			points++;
		}

		return val;
	}
}
