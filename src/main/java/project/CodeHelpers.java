package project;

import java.util.Random;

public class CodeHelpers {

    public static String getSecretCode() {
        int[] code = new int[]{-1, -1, -1, -1};
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int number = -1;
            while (number == -1) {
                number = random.nextInt(0, 10);
                for (int i1 : code) {
                    if (number == i1) {
                        number = -1;
                        break;
                    }
                }
            }
            code[i] = number;
        }
        return "" + code[0] + code[1] + code[2] + code[3];
    }

    public static boolean isValidInput(String input) {
        // Check if it is a four digit number
        if (input.length() != 4 || !input.matches("\\d+")) {
            return false;
        }

        // Check if numbers are repeated
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j < input.length(); j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    return false;
                }
            }
        }

        return true; // Enter the criteria
    }

    public static int[] compareTo(String guess, String secretCode) {
        int correctPosition = 0;
        int correctNumber = 0;

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                correctPosition++;
            } else if (secretCode.contains(String.valueOf(guess.charAt(i)))) {
                correctNumber++;
            }
        }
//        return correctPosition + "A" + correctNumber + "B";
        return new int[]{correctPosition, correctNumber};
    }
}
