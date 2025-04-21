package assignment9;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

	private Snake snake;
	private Food food;
	private int score;

	public Game() {
		StdDraw.setScale(0, 1);
		StdDraw.enableDoubleBuffering();

		snake = new Snake();
		food = new Food();
		score = 0;
	}

	public void play() {
		while (snake.isInbounds()) { // when the snake goes out of bounds the game ends
			int dir = getKeypress(); // sets direction to which key (W, A, S, or D) is pressed
			if (dir != -1) {
				snake.changeDirection(dir);
			}

			snake.move(); // calls snake.move() to simulate movement

			if (snake.eatFood(food)) {
				food = new Food(); // generates new food if food is consumed
				score++; // add one to the score each time food is eaten
			}

			updateDrawing(); // calls the updateDrawing method

		}
		Font fontGameOver = new Font("Times New Roman", Font.BOLD, 60); // font for "GAME OVER"
		Font fontScore = new Font("Times New Roman", Font.BOLD, 40); // font for final score

		StdDraw.setFont(fontGameOver);
		StdDraw.setPenColor(ColorUtils.gameOver());
		StdDraw.text(0.5, 0.5, "GAME OVER"); // displays "GAME OVER"

		StdDraw.setFont(fontScore);
		StdDraw.text(0.5, 0.4, "Final Score: " + score); // displays final score

		StdDraw.show();

	}

	private int getKeypress() {
		if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}

	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		StdDraw.clear(ColorUtils.backgroundColor());

		snake.draw();
		food.draw();

		StdDraw.pause(50);
		StdDraw.show();

	}

	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
