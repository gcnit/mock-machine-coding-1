import java.io.*;
import java.util.*;
public class SnakeAndLadder {
	private ArrayList<Snake>snakes;
	private ArrayList<Ladder>ladders;
	private HashMap<Player, Position> playerPositions;
	Position startPosition = new Position(1);
	Position endPosition = new Position(100);

	public SnakeAndLadder(Position startPosition , Position endPosition) {
		snakes = new ArrayList<>();
		ladders = new ArrayList<>();
		playerPositions = new HashMap<>();
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}

	private Position hasLadder(int finalPosition) {
		for (int i = 0; i < ladders.size(); i++) {
			if (ladders.get(i).getStart().getPosition() == finalPosition) {
				return ladders.get(i).getEnd();
			}
		}
		return null;
	}
	private Position hasSnake(int finalPosition) {
		for (int i = 0; i < snakes.size(); i++) {
			if (snakes.get(i).getStart().getPosition() == finalPosition) {
				return snakes.get(i).getEnd();
			}
		}
		return null;
	}
	public boolean MovePlayer(Player player, int finalPosition) {
		if (finalPosition > endPosition.getPosition()) {
			return false;
		}
		while (true) {
			boolean has = false;
			Position snakeTail = hasSnake(finalPosition);
			if (snakeTail != null) {
				finalPosition = snakeTail.getPosition();
				has = true;
			}
			Position ladderTail = hasLadder(finalPosition);
			if (ladderTail != null) {
				finalPosition = ladderTail.getPosition();
				has = true;
			}
			if (has == false) {
				break;
			}
		}
		gamePlayerPosition(player, finalPosition);
		return true;
	}
	public Position getEndPosition() {
		return endPosition;
	}
	public ArrayList<Snake> getAllSnakes() {
		return snakes;
	}
	public ArrayList<Ladder> getAllLadders() {
		return ladders;
	}

	public HashMap<Player, Position> getPlayerPositions() {
		return playerPositions;
	}

	public void gamePlayerPosition(Player player, int value) {
		playerPositions.put(player, new Position(value));
	}

	public Position getPlayerPosition(Player player) {
		return playerPositions.get(player);
	}
}


