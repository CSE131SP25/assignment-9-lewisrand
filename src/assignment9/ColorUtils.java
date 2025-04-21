package assignment9;

import java.awt.Color;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

public class ColorUtils {

	public static Color solidColor() {
		Random r = new Random();
		return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	}

	private static Color transparent(Color c) {
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		return new Color(r, g, b, 64);
	}

	public static Color transparentColor() {
		return transparent(solidColor());
	}

	// testing some stuff out
	public static Color foodColor() {
		return new Color(255, 0, 0);
	}

	public static Color snakeColor() {
		return new Color(0, 0, 100);
	}

	public static Color snakeHeadColor() {
		return new Color(0, 0, 255);
	}

	public static Color gameOver() {
		return new Color(0, 0, 0);
	}

	public static Color backgroundColor() {
		return new Color(144, 238, 144);
	}
}
