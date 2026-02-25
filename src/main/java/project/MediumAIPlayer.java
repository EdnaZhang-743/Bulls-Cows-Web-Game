package project;

import java.util.HashSet;
import java.util.Set;

class MediumAIPlayer implements Player {
    private final Set<String> guesscode = new HashSet<>();

    public static void main(String[] args) {
        Player easyAIPlayer = new MediumAIPlayer();

        easyAIPlayer.getSecretCode();

    }


//    public String getSecretCode() {
//        int[] code = new int[]{-1,-1,-1,-1};
//        Random random = new Random();
//        for (int i = 0; i < 4; i++) {
//            int number = -1;
//            while (number == -1) {
//                number = random.nextInt(0, 10);
//                for (int i1 : code) {
//                    if (number == i1) {
//                        number = -1;
//                        break;
//                    }
//                }
//            }
//            code[i] = number;
//        }
//        return "" + code[0] + code[1] + code[2] + code[3];
//    }

    //    public static void main(String[] args) {
//        EasyAIPlayer easyAIPlayer = new EasyAIPlayer();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(easyAIPlayer.getSecretCode());
//        }
//    }
//
    public String getGuessCode() {
        while(true){
            String guesslist = CodeHelpers.getSecretCode();
            if(!guesscode.contains(guesslist)){
                guesscode.add(guesslist);
                return guesslist;
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
    }

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

}
