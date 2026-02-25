package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class HardAIPlayer implements Player {

    private List<String> possibleCodes; // all possible codes
    private Random random;

    public static void main(String[] args) {
        Player easyAIPlayer = new HardAIPlayer();

        easyAIPlayer.getSecretCode();

    }

    public HardAIPlayer() {
        this.possibleCodes = generateAllCodes();
        this.random = new Random();
    }

    private List<String> generateAllCodes() {
        List<String> codes = new ArrayList<>();
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                for (int c = 0; c < 10; c++) {
                    for (int d = 0; d < 10; d++) {
                        String code = "" + a + b + c + d;
                        if (CodeHelpers.isValidInput(code)) {
                            codes.add(code);
                        }
                    }
                }
            }
        }
//
//        for (int i = 1; i <= 4; i++) {
//            for (int j = 1; j <= 4; j++) {
//                if (i != j) { // 确保数字不重复
//                    codes.add("" + i + j);
//                }
//            }
//        }
        return codes;
    }

    public String getGuessCode() {
        return possibleCodes.get(random.nextInt(possibleCodes.size()));
    }

@Override
    public void feedback(String guess, int[] result) {
        List<String> filteredCodes = new ArrayList<>();
        for (String code : possibleCodes) {
            int[] test = CodeHelpers.compareTo(guess, code);
            if (test[0] == result[0] && test[1] == result[1]) {
                filteredCodes.add(code);
            }
        }
        this.possibleCodes = filteredCodes; // Updated list of possible codes
    }

    private String compareCodes(String code, String guess) {
        int bulls = 0, cows = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else if (code.contains("" + guess.charAt(i))) {
                cows++;
            }
        }
        return "A" + bulls + "B" + cows ;
    }

}
//        String input = Keyboard.readInput();
//        while (true) { // 循环直到用户输入正确
//            System.out.print("Please enter a four-digit number with no repeating digits：");
//
//            if (isValidInput(input)) {
//                System.out.println("The input is correct! your number is：" + input);
//                break; // 输入正确，结束循环
//            } else {
//                System.out.println("Invalid input, please input again！");
//            }
//        }
//        return input;


//    public static boolean isValidInput(String input) {
//        // 检查是否为四位数字
//        if (input.length() != 4 || !input.matches("\\d+")) {
//            return false;
//        }
//
//        // 检查数字是否有重复
//        for (int i = 0; i < input.length(); i++) {
//            for (int j = i + 1; j < input.length(); j++) {
//                if (input.charAt(i) == input.charAt(j)) {
//                    return false;
//                }
//            }
//        }
//
//        return true; // 输入符合条件
//    }

