package bullscows;

import java.util.HashSet;

import java.util.Scanner;

public class Grader {
    private final Scanner scanner = new Scanner(System.in);
    private int countBulls;
    private int countCows;
    private static int codeLen;
    private static int numberPossibleSymbols;
    private int turn = 1;

    public void playGame() {

        if (!firstOutputAndInput(scanner)) {
            return;
        }

        String secretCode = generateSecretCode(codeLen, numberPossibleSymbols);

        while (true) {
            System.out.println("Turn " + turn +":");
            String guess = scanner.next();

            if (!isInputValid(guess, codeLen)) {
                System.out.println("Your input should be a " + codeLen + "-digit input." );
                continue;
            }

            if (guess.equals(secretCode)) {
                System.out.println("Grade: " + codeLen + " bulls");
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }

            mainLogic(secretCode, guess);

            secondOutput(countBulls, countCows, codeLen);
            turn++;
        }

    }


    private static void secondOutput(int countBulls, int countCows, int codeLen) {
        if (codeLen == 1) {
            System.out.println("Grade: " + countBulls + " bull");
        } else if (countBulls > 0 && countCows > 0) {
            String bullText = countBulls == 1 ? "bull" : "bulls";
            String cowText = countCows == 1 ? "cow" : "cows";
            System.out.println("Grade: " + countBulls + " " + bullText + " and " + countCows + " " + cowText);
        } else if (countBulls > 0) {
            String bullText = countBulls == 1 ? "bull" : "bulls";
            System.out.println("Grade: " + countBulls + " " + bullText);
        } else if (countCows > 0) {
            String cowText = countCows == 1 ? "cow" : "cows";
            System.out.println("Grade: " + countCows + " " + cowText);
        } else {
            System.out.println("Grade: 0 bulls and 0 cows");
        }
    }

   private boolean firstOutputAndInput(Scanner scanner) {
       // Query the code length
        System.out.println("Input the length of the secret code:");
        while (true) {
            try {
                String input = scanner.next();
                codeLen = Integer.parseInt(input);
                if (codeLen <= 0) {
                    System.out.println("Error: minimum length of the secret code is 1.");
                    return false;
                } else if (codeLen > 36) {
                    System.out.println("Error: maximum length of the secret code is 36.");
                    return false;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: invalid input. Enter a number.");
            }
        }

       // Query the possible number of symbols
        System.out.println("Input the number of possible symbols in the code:");
        while (true) {
            try {
                numberPossibleSymbols = scanner.nextInt();
                if (numberPossibleSymbols < codeLen) {
                    System.out.println("Error: it's not possible to generate a code with a length of " +
                            codeLen + " with " + numberPossibleSymbols + " unique symbols.");
                    return false;
                }
                if (numberPossibleSymbols > 36) {
                    System.out.println("Error: maximum number of possible symbols is 36 (0-9, a-z).");
                    return false;
                }
                break;
            } catch (Exception e) {
                System.out.println("Error: invalid input. Enter a number.");
                scanner.next();
            }
        }

        // Display the preparation message
        String symbolRange;
        if (numberPossibleSymbols <= 10) {
            symbolRange = "(0-" + (numberPossibleSymbols - 1) + ")";
        } else {
            char lastLetter = (char) ('a' + numberPossibleSymbols - 11);
            symbolRange = "(0-9, a-" + lastLetter + ")";
        }

        System.out.println("The secret is prepared: " + "*".repeat(codeLen) + " " + symbolRange);
        System.out.println("Okay, let's start a game!");
        return true;
    }

    private static String generateSecretCode(int codeLen, int numberPossibleSymbols) {
        String allPossibleChars = getStringForSecretCode(numberPossibleSymbols);
        StringBuilder secretCode = new StringBuilder();
        HashSet<Character> usedChars = new HashSet<>();
    
        while (secretCode.length() < codeLen) {
            int randomIndex = (int) (Math.random() * allPossibleChars.length());
            char randomChar = allPossibleChars.charAt(randomIndex);
            
            if (!usedChars.contains(randomChar)) {
                usedChars.add(randomChar);
                secretCode.append(randomChar);
            }
        }
    
        return secretCode.toString();
    }

    private static String getStringForSecretCode(int numberPossibleSymbols) {
        StringBuilder possibleChars = new StringBuilder();

        int numCount = Math.min(10, numberPossibleSymbols);
        for (int i = 0; i < numCount; i++) {
            possibleChars.append((char)('0' + i));
        }

        if (numberPossibleSymbols > 10) {
            int letterCount = numberPossibleSymbols - 10;
            for (int i = 0; i < letterCount; i++) {
                possibleChars.append((char)('a' + i));
            }
        }

        return possibleChars.toString();
    }

    private boolean isInputValid(String guess, int codeLen) {
        if (guess.length() != codeLen)
            return false;
        for (char c : guess.toCharArray()) {
            if (!Character.isLetterOrDigit(c))
                return false;
            if (Character.isDigit(c) && c - '0' >= Math.min(10, numberPossibleSymbols))
                return false;
            if (Character.isLetter(c) && (c < 'a' || c > 'a' + numberPossibleSymbols - 11))
                return false;
        }


        return true;
    }

    private void mainLogic(String secretCode, String guess) {
        countBulls = 0;
        countCows = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                countBulls++;
            }
        }
        for (int i = 0; i < secretCode.length(); i++) {
            for (int j = 0; j < secretCode.length(); j++) {
                if (i == j) continue;
                if (guess.charAt(i) == secretCode.charAt(j)) {
                    countCows++;
                }
            }
        }
    }

}
