package mnk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        final int n = 4, m = 5, k = 3;
        final int numberOfPlayers = 2;
        final int numberOfWins = 2;
        final Game game = new Game(false, List.of(
                new HumanPlayer(),
                new SequentialPlayer()
        ));
        int firstPlayerScore = 0;
        int secondPlayerScore = 0;
        int gameNo = 0;
        do {
            int result = game.play(new MNKBoard(n, m, k, numberOfPlayers, gameNo));
            if (result == 1) {
                firstPlayerScore++;
            } else {
                secondPlayerScore++;
            }
            System.out.println("Current result: " + result);
            System.out.println("Current score: " + firstPlayerScore + " : " + secondPlayerScore);
            System.out.println("Change of sides");
            gameNo++;

        } while (Math.max(firstPlayerScore, secondPlayerScore) < numberOfWins);
        System.out.println("Game result:" );
        if (firstPlayerScore > secondPlayerScore) {
            System.out.println("First player won");
        } else {
            System.out.println("Second player won");
        }
    }
}
