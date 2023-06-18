import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    Board board; // Every game has a board
    List<Player> playerList; // list of total players , players will move according to order of this list
    List<Dice> diceList; // there can be multiple dice in a game, can be taken from user also at game start

    Map<Player, Cell> playerPositionMap; // Position of each player on board

    int playerIndexToMove; // which player has turn to roll dice

    //TODO currently not getting used
    List<Player> winners; // winners list, there can be multiple winner in chronological order

    //TODO not getting used
    List<Moves> moves; // history of all moves occured in a game


    //TODO add validations on invalid data
    //TODO that connectors should not make a infinite loop
    public Game(int endPosition, List<Connector> connectors, List<Player> players) {
        Map<Integer, Integer> connectorsMap = new HashMap<>();

        for (Connector connector:connectors) {
            connectorsMap.put(connector.start, connector.end);
        }
        this.board = new Board(endPosition, connectorsMap);
        this.playerList = players;

        this.diceList = new ArrayList<>();
        diceList.add(new Dice(6));

        this.playerPositionMap = new HashMap<>();
        for (Player player: players) {
            playerPositionMap.put(player, new Cell(0));
        }

        this.playerIndexToMove = 0; // hard coded for now, first player in list will move first
        this.winners = new ArrayList<>(); // optional for now

        this.moves  = new ArrayList<>(); // optional for now
    }

    // on getting 6 the dice will be rolled again, will give total number of steps to take
    private int getNumberOfSteps() {
        int steps = 0;
        for (Dice dice:diceList) {
            int currentStep = dice.getNumber();
            System.out.println("Calling dice and got " + currentStep);
            //System.out.println("Dice number is " + currentStep);
            steps += currentStep;
            if (currentStep == 6) {
                //System.out.println("Calling dice again ");
                int moreSteps = getNumberOfSteps();
                steps += moreSteps;
                //System.out.println("Calling dice again and got " + moreSteps);
            }
        }
        return steps;
    }

    // will make progress in a game by rolling dice and changing player positions
    // will change playerIndexToMove which will change the next player having turn
    // will give true if game winner is not declared
    // in console logs will print the dice output, player name, current position, next position
    // if winner is found then this will return false
    // TODO this method can be improved -- should make next move only, winner logic can be taken out from this
    //TODO ideally this should be a void method
    public boolean play() {
        //this.playerIndexToMove

        Player playerToMove = playerList.get(playerIndexToMove);

        int steps = getNumberOfSteps();

        int currentPosition = playerPositionMap.get(playerToMove).position;

        Cell updatedPosition = this.board.getNext(currentPosition, steps);

        if (updatedPosition == null) {
            System.out.println(" Not able to move as cannot cross the board");
        } else {

            //update player position
            playerPositionMap.put(playerToMove, updatedPosition);

            System.out.println("Player  " + playerToMove.name + " moved from " + currentPosition + " to " + updatedPosition.position + " dice output is "
            + steps);


            //winner logic
            if (updatedPosition.position == board.endPosition) {
                System.out.println(" Winner of the game is " + playerToMove.name);
                // stop the game
                return false;
            }
        }



        if (playerIndexToMove < playerList.size()-1) {
            playerIndexToMove++;
        } else if (playerIndexToMove == playerList.size()-1) {
            playerIndexToMove = 0;
        }

        //System.out.println("Next player move is " + playerIndexToMove);

        return true;
    }

}
