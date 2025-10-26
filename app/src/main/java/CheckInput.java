import java.util.Arrays;
import java.util.Scanner;


public class CheckInput {
    String theInput;

    public static String printSomething(String theString) {
        Scanner sc = new Scanner(System.in);

        //Prints string and gets user input
        System.out.println(theString);
        String theInput = sc.nextLine();
        return theInput;
    }

    public static DifficultyLevel Difficulty(String theInput) {
        //returns null if bad input
        if (!theInput.equals("e") && !theInput.equals("i") && !theInput.equals("h")) {
            theInput = printSomething("Invalid difficulty . Try Again ...");
            //calls itself again to check input again
            return Difficulty(theInput);
        }
        else {
            //Making a SetDifficultyLevel object
            DifficultyLevel level = new DifficultyLevel(theInput);
            return level;
        }
    }

    public static char Guess(String theInput, String theWord, int guessesRemaining) {
        char theCharacter = theInput.charAt(0);
        if (Character.isDigit(theCharacter)) {
            theInput = printSomething("Your input is not valid . Try again .");
            System.out.println("Guesses Remaining : " + guessesRemaining);
            return Guess(theInput, theWord, guessesRemaining);

        }
        else {
            return theCharacter;
        }
    }

    public static int[] askSpaces(String theInput, DifficultyLevel difficultyLevel, int guessesRemaining, int secretWordLength) {
        //turn into array.
        String[] guessArray = theInput.split(" ");
        int[] intGuessArray = new int[guessArray.length];

        for (int i = 0; i < guessArray.length; i++) {
            //turning the array into ints
            intGuessArray[i] = Integer.parseInt(guessArray[i]);
        }
        Arrays.sort(intGuessArray);
        //should know what the difficulty level is due to the order of the functions being called
        if (difficultyLevel.numSpaces == 4 && intGuessArray.length == 4 && intGuessArray[3] < secretWordLength) {
            return intGuessArray;
        } else if (difficultyLevel.numSpaces == 3 && intGuessArray.length == 3 && intGuessArray[2] < secretWordLength) {
            return intGuessArray;
        } else if (difficultyLevel.numSpaces == 2 && intGuessArray.length == 2 && intGuessArray[1] < secretWordLength) {
            return intGuessArray;
        }
        else {
            //if not valid, call print something to say so and call itself again
            theInput = printSomething("Your input is not valid. Try again.");
            System.out.println("Guesses Remaining: " + guessesRemaining);
            return askSpaces(theInput, difficultyLevel, guessesRemaining, secretWordLength);
        }

    }

    public static String keepPlaying(String theInput) {
        if (!theInput.equals("y") && !theInput.equals("n")) {
            theInput = printSomething("Your input is not valid. Try again.");
            //calls itself again to check input again
            return keepPlaying(theInput);
        }
        else {
            return theInput;
        }
    }

}
