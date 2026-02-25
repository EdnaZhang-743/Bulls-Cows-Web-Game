package project;

public class GameControl {
    private Player player1 = new EasyAIPlayer();
    private Player player2 = new EasyAIPlayer();
    private String secretCode1;
    private String secretCode2;


    public GameLogger start() {
        player1=chooseCompetitor();
        player2=chooseCompetitor();

        System.out.println("The game begins! Have two players generate secret codes...");
        secretCode1 = player1.getSecretCode();
        secretCode2 = player2.getSecretCode();

        GameLogger logger = new GameLogger(player1, secretCode1, player2, secretCode2);
        System.out.println("Player 1 and Player 2 secret codes generated");

        int rounds = 7;
        for (int round = 1; round <= rounds; round++) {
            System.out.println("No. " + round + " round：");

            // Player 1 guesses Player 2's secret code
            String guess1 = player1.getGuessCode();
            System.out.println("Player 1 guesses: " + guess1);
            int[] result1 = player2.compareGuess(guess1,secretCode2);
            player1.feedback(guess1, result1);

            logger.addEvent(guess1, result1, player1);

            System.out.println("Feedback from Player 2: " + result1[0] + " Bull and " + result1[1] + " Cow");
//            if (result1.equals("4A0B")) {
            if (result1[0] == 4) {
                System.out.println("Player 1 guessed correctly! Player 1 wins!");
                return logger;
            }

            // Player 2 guesses Player 1's secret code
            String guess2 = player2.getGuessCode();
            System.out.println("Player 2 guesses: " + guess2);
//            String result2 = player1.compareGuess(secretCode1,guess2);
            int[] result2 = player1.compareGuess(secretCode1,guess2);
            player2.feedback(guess2, result2);

            logger.addEvent(guess2, result2, player2);

            System.out.println("Feedback from Player 1: " + result2[0] + " Bull and " + result2[1] + " Cow");
//            if (result2.equals("4A0B")) {
            if (result2[0] == 4) {
                System.out.println("Player 2 guessed correctly! Player 2 wins!");
                return logger;
            }
        }


        System.out.println("At the end of 7 rounds, neither side guessed correctly, and the game was a draw!");
        return logger;
    }


//
//    public int[] calculateBallandCow(String a, String b) {
//        return null;
//    }

    public Player chooseCompetitor(){
        System.out.println("Choose competitor. You could choose 'easy','medium','hard','user','file'. And 'user' could only choose once.");
        String choice = Keyboard.readInput();
        switch (choice) {
            case "easy":
                return new EasyAIPlayer();
            case "medium":
                return new MediumAIPlayer();
            case "user":
                return new UserPlayer();
            case "hard":
                return new HardAIPlayer();
            case "file":
                return new FilePlayer();

        }

        return null;
    }

    public static void main(String[] args) {
        GameControl gc = new GameControl();
        GameLogger gl = gc.start();

        System.out.print("Do you want to save game results to a file? (yes/no)：");
        String choice = Keyboard.readInput();
        if (choice.equalsIgnoreCase("yes")) {
            gl.save("testFile");
        }

        System.out.println("game over!");
    }
}

