package project;

class UserPlayer implements Player {
//    private final Set<String> guesscode = new HashSet<>();

    public static void main(String[] args) {
        Player easyAIPlayer = new UserPlayer();

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
        String input;
        while (true) { // Loop until user input is correct

            System.out.print("Please enter a four-digit number with no repeating digits：");
            input = Keyboard.readInput();
            if (CodeHelpers.isValidInput(input)) {
                System.out.println("The input is correct! your number is：" + input);
                break; // Enter correctly and end the loop
            } else {
                System.out.println("Invalid input, please input again！");

            }
        }
        return input;
        }


}
