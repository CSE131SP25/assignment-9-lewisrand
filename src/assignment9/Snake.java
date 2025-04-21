package assignment9;

import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {

	private static final double SEGMENT_SIZE = 0.0225;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private int currentDirection;

	public Snake() {
		segments = new LinkedList<>();
		BodySegment snakeHead = new BodySegment(0.5, 0.5, SEGMENT_SIZE);
		segments.add(snakeHead);

		deltaX = 0;
		deltaY = 0;
		currentDirection = 0;
	}

	public void changeDirection(int direction) {

		if ((currentDirection == 1 && direction == 2) || (currentDirection == 2 && direction == 1)
				|| (currentDirection == 3 && direction == 4) || (currentDirection == 4 && direction == 3)) {
			return;
		}

		if (direction == 1) { // up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { // down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { // left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { // right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
		currentDirection = direction;
	}

	/**
	 * Moves the snake by updating the position of each of the segments based on the
	 * current direction of travel
	 */
	public void move() {
		if (segments.isEmpty()) {
			return;
		}

		BodySegment snakeHead = segments.getFirst();
		double newX = snakeHead.getX() + deltaX;
		double newY = snakeHead.getY() + deltaY;

		BodySegment newSnakeHead = new BodySegment(newX, newY, SEGMENT_SIZE); // created new head segment
		segments.addFirst(newSnakeHead); // adds new segment to the body
		segments.removeLast(); // removes last segment to create movement
	}

	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (int i = 0; i < segments.size() - 1; i++) {
			BodySegment current = segments.get(i);
			BodySegment next = segments.get(i + 1);

			double xCurrent = current.getX();
			double yCurrent = current.getY();
			double xNext = next.getX();
			double yNext = next.getY();

			StdDraw.setPenColor(ColorUtils.snakeColor());
			StdDraw.setPenRadius(SEGMENT_SIZE * 2);
			StdDraw.line(xCurrent, yCurrent, xNext, yNext);
		}
		segments.getFirst().draw();
		StdDraw.setPenRadius();
	}

	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * 
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		BodySegment snakeHead = segments.getFirst();
		// Math.hypot calculates the length of the hypotenuse formed by a right triangle
		double distance = Math.hypot(snakeHead.getX() - f.getX(), snakeHead.getY() - f.getY()); // check distance from
																								// food

		if (distance < SEGMENT_SIZE + Food.FOOD_SIZE) {
			BodySegment snakeTail = segments.getLast();
			BodySegment newSegment = new BodySegment(snakeTail.getX(), snakeTail.getY(), SEGMENT_SIZE); // add new body
																										// segment if
																										// snake is
																										// sufficiently
																										// close
			segments.addLast(newSegment);
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the head of the snake is in bounds
	 * 
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() { // determines boundary for snake
		BodySegment snakeHead = segments.getFirst();
		double x = snakeHead.getX();
		double y = snakeHead.getY();

		return (x >= 0.0 && x < 1.0 && y >= 0 && y <= 1.0);
	}
}
