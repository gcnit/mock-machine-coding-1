import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //TODO accept these from user from console
        // a connector can be a snake or a ladder
        List<Connector> connectorList = new ArrayList<>();

        //ladders
        connectorList.add(new Connector(5, 37));
        connectorList.add(new Connector(9, 45));

        connectorList.add(new Connector(17, 88));

        connectorList.add(new Connector(25, 41));

        connectorList.add(new Connector(36, 70));
        connectorList.add(new Connector(66, 83));
        connectorList.add(new Connector(78, 97));
        connectorList.add(new Connector(81, 92));

        //snakes
        connectorList.add(new Connector(38, 7));
        connectorList.add(new Connector(99, 27));
        connectorList.add(new Connector(61, 19));
        connectorList.add(new Connector(93, 11));
        connectorList.add(new Connector(85, 56));
        connectorList.add(new Connector(42, 17));
        connectorList.add(new Connector(72, 51));
        connectorList.add(new Connector(53, 21));

        //list of players
        List<Player> players = new ArrayList<>();

        players.add(new Player("shivani"));

        players.add(new Player("sachin"));


        // total positions in game starting from 1, connectors list, number of players
        Game game = new Game(100, connectorList, players);


        //game simulation which will stop when first winner is declared
        while (game.play()) {
            //do nothing
            //Thread.sleep(500);
        }

        //user based input and play, uncomment to play
       /* Scanner input = new Scanner(System.in);
        while (true) {
            String line = input.nextLine();
            if ("exit".equalsIgnoreCase(line)) {
                break;
            }
            game.play();
        }*/
    }
}
