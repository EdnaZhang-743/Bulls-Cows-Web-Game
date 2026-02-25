package project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GameLogger {
    private final Player p1;
    private final Player p2;
    private final String secret1;
    private final String secret2;
    List<Event> events = new ArrayList<Event>();

    class Event {
        final String  guess;
        final int[] result;
        final Player player;

        Event(String guess, int[] result, Player player) {
            this.guess = guess;
            this.result = result;
            this.player = player;
        }

        public String getGuess() {
            return guess;
        }

        public int[] getResult() {
            return result;
        }

        public Player getPlayer() {
            return player;
        }
    }


    public GameLogger(Player p1, String secretCode1, Player p2, String secretCode2) {
        this.p1 = p1;
        this.p2 = p2;
        this.secret1 = secretCode1;
        this.secret2 = secretCode2;
    }

    public void addEvent(String guess, int[] result, Player player) {
        events.add(new Event(guess, result, player));
    }

    public void save(String path) {
        try(PrintWriter pw = new PrintWriter(path)) {
            pw.println("Bulls & Cows game result.");
            pw.println("Player 1 secret code: " + secret1);
            pw.println("Player 2 secret code: " + secret2);

            for (Event event : events) {
                pw.println(event.getPlayer().getClass().getSimpleName()
                        + " guessed " + event.getGuess()
                        + " and scored " + event.getResult()[0] + " bulls and " + event.getResult()[1] + " cows");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
