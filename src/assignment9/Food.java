package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Food {

	public static final double FOOD_SIZE = 0.0225;
	private double x, y;

	/**
	 * Creates a new Food at a random location
	 */
	public Food() {
		x = FOOD_SIZE + (1.0 - 2 * FOOD_SIZE) * Math.random(); // random x-axis
		y = FOOD_SIZE + (1.0 - 2 * FOOD_SIZE) * Math.random(); // random y-axis
	}

	/**
	 * Draws the Food
	 */
	public void draw() {
		StdDraw.setPenColor(ColorUtils.foodColor());
		StdDraw.filledCircle(x, y, FOOD_SIZE); // draw food at the x and y coordinates

	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
