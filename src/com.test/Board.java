import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    Cell[] positions;

    Map<Cell, Connector> cellConnectorMap;

    int startPosition; // 0 -- will start from 0 (0 will denote initial start position of the players)

    int endPosition; // 100

    // 1 based position
    public Board(int endPosition, Map<Integer, Integer> connectorsMap) {
        this.endPosition = endPosition;
        this.startPosition = 0;// not giving option for user to init it -- 0 is outside the board
        this.positions = new Cell[endPosition+1];

        //initialise all cells
        int position = 0;
        for (Cell cell:positions) {
            positions[position] = new Cell(position);
            //cell.position = position;
            position++;
        }

        //initialise all connectors
        this.cellConnectorMap = new HashMap<>();
        for (Integer cellPosition:connectorsMap.keySet()) {
            cellConnectorMap.put(this.positions[cellPosition], new Connector(cellPosition, connectorsMap.get(cellPosition)));
        }
    }

    // get next cell from a current cell, give number of steps to take
    // if next cell is not possible return null
    // if already at end position then return the same end position cell
    public Cell getNext(int current, int stepsToTake) {
        int updatedPosition = current + stepsToTake;

        if (updatedPosition > endPosition) {
            //not possible to move
            return null;
        }

        if (updatedPosition == endPosition) {
            return positions[endPosition]; // no need to check for now as this is winner position
        }

        int currentPosition = updatedPosition;

        //iterate in cellConnectorMap till it is getting found
        while (cellConnectorMap.containsKey(positions[currentPosition])) {
            int nextPosition = cellConnectorMap.get(positions[currentPosition]).end;
            System.out.println("Taking the step from currentPosition " + currentPosition + " to " + nextPosition);
            currentPosition = nextPosition;
        }

        return positions[currentPosition];
    }
}
