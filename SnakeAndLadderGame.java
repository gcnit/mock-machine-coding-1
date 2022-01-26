import java.io.*;
import java.util.*;

public class SnakeAndLadderGame {
	private SnakeAndLadder snakeAndLadder;
	private Queue<Player>playerOrder;
	private ArrayList<Player>winningOrder;
	int size = 6;
	Dice dice;
	int playerSize = 2;
	public SnakeAndLadderGame(int size, int startPosition, int endPosition) {
		this.size = size;
		this.dice = new Dice(size);
		snakeAndLadder = new SnakeAndLadder(new Position(startPosition), new Position(endPosition));
		Scanner sc = new Scanner(System.in);
		this.winningOrder  = new ArrayList<>();
		this.playerOrder = new LinkedList<>();
		InitializeSnakes(sc);
		InitializeLadders(sc);
		initializePlayers(sc);
	}

	public void InitializeSnakes(Scanner sc) {
		int numberOfSnakes = sc.nextInt();
		for (int i = 0; i < numberOfSnakes; i++) {
			sc.nextLine();
			Position start =  new Position(sc.nextInt());
			Position end =  new Position(sc.nextInt());
			Snake newSnake = new Snake(start, end);
			snakeAndLadder.getAllSnakes().add(newSnake);
		}

	}
	public void InitializeLadders(Scanner sc) {
		int numberOfLadders = sc.nextInt();
		// System.out.println(numberOfLadders);
		for (int i = 0; i < numberOfLadders; i++) {
			sc.nextLine();
			Position start =  new Position(sc.nextInt());
			Position end =  new Position(sc.nextInt());
			Ladder newLadder = new Ladder(start, end);
			snakeAndLadder.getAllLadders().add(newLadder);
		}
	}

	public void initializePlayers(Scanner sc) {
		int numberOfPlayers = sc.nextInt();
		// System.out.println(numberOfPlayers);
		for (int i = 0; i < numberOfPlayers; i++) {
			String name = sc.next();
			Player newPlayer = new Player(name);
			playerOrder.add(newPlayer);
			snakeAndLadder.gamePlayerPosition(newPlayer, 0);
		}
	}
	public void PlayGame() {
		while (winningOrder.size() < playerSize - 1 ) {
			Player currentPlayer = playerOrder.peek();
			int value  = currentPlayer.RollDice(this.dice);
			Position currentPosition = snakeAndLadder.getPlayerPosition(currentPlayer);
			int finalPosition = value + currentPosition.getPosition();
			boolean valid = snakeAndLadder.MovePlayer(currentPlayer, finalPosition);
			if (valid) {
				playerOrder.poll();
				System.out.println(currentPlayer.getName() + " rolled a " + value + " and moved from " + currentPosition.getPosition() + " to " + snakeAndLadder.getPlayerPosition(currentPlayer).getPosition() );
				if (snakeAndLadder.getPlayerPosition(currentPlayer).getPosition() == snakeAndLadder.getEndPosition().getPosition()) {
					winningOrder.add(currentPlayer);
					System.out.println(currentPlayer.getName() + " wins the game");
				} else {
					playerOrder.add(currentPlayer);
				}
			} else {
				playerOrder.poll();
				playerOrder.add(currentPlayer);
			}
		}
	}
	public Player getCurrentPlayer() {
		return playerOrder.peek();
	}

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("input.txt"));
			System.setOut(new PrintStream(new FileOutputStream("output.txt")));
		} catch (Exception e) {
			System.err.println("Error");
		}
		SnakeAndLadderGame snakeAndLadderGame = new SnakeAndLadderGame(6, 1, 100);
		snakeAndLadderGame.PlayGame();

	}
}