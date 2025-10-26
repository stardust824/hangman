import java.util.Random;

public class RandomWord {	
    private static Random random = new Random();
    private static String[] words = {"cauldron", "campfire", "ichthyosaurus", "starlight", "foxes", "serpent", "mountain", "tides", "liquid", "huckleberry"};

    public static String newWord() {
        int index = random.nextInt(words.length);
        return words[index];
    }
}
