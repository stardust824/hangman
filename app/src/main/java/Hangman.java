//Date: Feb 5th 2025
import java.util.Arrays;

public class Hangman {
    private static final boolean testingMode = true;
    public static void main(String[] args) {
        boolean keepPlaying = true;
        //for loop to play
        while(keepPlaying == true) {
            //Asks user for difficulty
            String theInput = CheckInput.printSomething("Enter your difficulty: Easy ( e ) , Intermediate ( i ) , or Hard ( h )");
            //checks input and sets difficulty level
            DifficultyLevel level = CheckInput.Difficulty(theInput);

            String secretWord = RandomWord.newWord();
            char [] secretArray = new char[secretWord.length()];
            char [] dashesArray = new char[secretWord.length()];

            for(int i = 0; i < secretWord.length(); i++) {
                secretArray[i] = secretWord.charAt(i);
                dashesArray[i] = '-';
            }
            if(testingMode == true) {
                System.out.println(secretWord);
            }
            //make thing that formats the string
            System.out.println("The word is: " + formatStuff(dashesArray));

            boolean isPlaying = true;
            while (isPlaying == true) {
                String letterGuessed;

                //user guesses letters
                letterGuessed = CheckInput.printSomething("Please enter the letter you want to guess: ");
                //sets up and stores the letter guess in charLetter
                if(letterGuessed.equalsIgnoreCase("solve")){
                    System.out.println("Please solve the word: " + secretWord);
                    System.out.println("You have guessed the word! Congratulations");
                    String keepGoing = CheckInput.printSomething("Would you like to play again? Yes ( y ) or No ( n )");
                    String yesOrNo = CheckInput.keepPlaying(keepGoing);
                    if(yesOrNo.equals("y")) {
                        isPlaying = false;
                        break;
                    }
                    else {
                        isPlaying = false;
                        keepPlaying = false;
                        break;
                    }

                }

                char guessChar = CheckInput.Guess(letterGuessed, secretWord, level.numGuesses);

                //user enters spaces
                String spacesGuessed = CheckInput.printSomething("Please enter the spaces you want to check ( separated by spaces ):");
                int lengthSecretWord = secretWord.length();
                //checks to make sure input is correct and formats it into an int array
                int[] guessArray = CheckInput.askSpaces(spacesGuessed, level, level.numGuesses, lengthSecretWord);

                //this is each space the user guessed
                boolean isAltered = false;
                for (int i = 0; i < guessArray.length; i++) {
                    //this sets j to each of the spaces guessed
                    int j = guessArray[i];
                    //this checks if index j is the right letter
                    if (secretArray[j] == guessChar) {
                        dashesArray[j] = guessChar;
                        isAltered = true;
                    }
                }


                if(isAltered == true) {
                    System.out.println("Your guess is in the word !");
                    System.out.println("The updated word is: " + formatStuff(dashesArray));
                    System.out.println("Guesses Remaining: " + level.numGuesses);

                    //Initializes boolean to check if there are dashes in the array and whether or not the word is solved
                    boolean isDashes = true;
                    for (int i = 0; i < dashesArray.length; i++) {
                        if (dashesArray[i] == '-') {
                            isDashes = false;
                        }
                    }
                    if(isDashes == true) {
                        System.out.println("You have guessed the word! Congratulations");
                        String keepGoing = CheckInput.printSomething("Would you like to play again? Yes ( y ) or No ( n )");
                        String yesOrNo = CheckInput.keepPlaying(keepGoing);
                        //checks if user wants to continue playing
                        if(yesOrNo.equals("y")) {
                            isPlaying = false;
                        }
                        else {
                            isPlaying = false;
                            keepPlaying = false;
                        }
                    }
                }
                else {
                    System.out.println("Your letter was not found in the spaces you provided.");
                    level.numGuesses = (level.numGuesses - 1);
                    System.out.println("Guesses Remaining: " + level.numGuesses);
                    if (level.numGuesses == 0) {
                        System.out.println("You have failed to guess the word ... :(");
                        String keepGoing = CheckInput.printSomething("Would you like to play again? Yes ( y ) or No ( n )");
                        String yesOrNo = CheckInput.keepPlaying(keepGoing);
                        if(yesOrNo.equals("y")) {
                            isPlaying = false;
                        }
                        else {
                            isPlaying = false;
                            keepPlaying = false;
                        }
                    }
                }
            }

        }
    }
    public static String formatStuff (char[] dashesArray) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < dashesArray.length; i++) {
            builder.append(dashesArray[i]);
        }
        String dashesString = builder.toString();
        return dashesString;
    }
}
