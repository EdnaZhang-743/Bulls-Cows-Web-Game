package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FilePlayer implements Player{

    private List<String> guesses; // all possible codes
    private String secretCode;


    public FilePlayer() {
        this.guesses = new LinkedList<>();

        loadCodesFromFile();
    }

    @Override
    public String getSecretCode() {
        return this.secretCode;
    }

    public String getGuessCode() {
        if (guesses.isEmpty()) {
            return CodeHelpers.getSecretCode();
        }
        String nextGuess = this.guesses.get(0);
        this.guesses.remove(0);
        return nextGuess;
    }

    // Load code from file
    private void loadCodesFromFile() {

        System.out.println("Please enter a file name");
        String filename = Keyboard.readInput();
        while (true) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                boolean secretCodeSet = false;

                while ((line = reader.readLine()) != null) {
                    line = line.trim(); // 去除空格
                    if (isValidCode(line)) {
                        if (!secretCodeSet) {
                            this.secretCode = line; // The first line of valid code serves as the secret code
                            secretCodeSet = true;
                        } else {
                            guesses.add(line); // The remaining code is added to the queue
                        }
                    }
                }

                if (this.secretCode == null) {
                    System.out.println("No valid code found in the file, please re-enter the file name.");
                    System.out.println("Please enter the correct file name");
                    filename = Keyboard.readInput();
                } else {
                    System.out.println("File loaded successfully! The secret code is set.");
                    break;
                }
            } catch (IOException e) {
                System.out.println("File not found, please re-enter the file name:");
                System.out.println("Please enter the correct file name");
                filename = Keyboard.readInput();
            }
        }
    }

    // Check whether the code is legal (4 digits and not repeated)
    private boolean isValidCode(String code) {
        if (code.length() != 4 || !code.matches("\\d{4}")) {
            return false;
        }
        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : code.toCharArray()) {
            if (!uniqueDigits.add(c)) { // If there are duplicate numbers, return false
                return false;
            }
        }
        return true;
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

