public class DifficultyLevel {
    public int numGuesses;
    public int numSpaces;

    //constructor
    public DifficultyLevel(String theInput) {
        if(theInput.equals("e")) {
            numGuesses = 15;
            numSpaces = 4;
        }
        if(theInput.equals("i")) {
            numGuesses = 12;
            numSpaces = 3;
        }
        if(theInput.equals("h")) {
            numGuesses = 10;
            numSpaces = 2;
        }
    }
}
