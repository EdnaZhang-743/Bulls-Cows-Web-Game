package project;

public interface Player {

    default String getSecretCode() {
        return CodeHelpers.getSecretCode();
    }

    String getGuessCode() ;

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
//        System.out.println(input);
//        return input;
//    }
//
//    static boolean isValidInput(String input) {
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

    default void feedback(String guess, int[] result) {}

    default int[] compareGuess(String guess,String secretCode) {
        return CodeHelpers.compareTo(guess, secretCode);
    }

}
