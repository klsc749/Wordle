import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * The {@code WordLists} class is used to read word from files
 * @author Wang Xin
 * @version 1.0
 */
public class WordLists {
    /**
     * Store the parent path of the word file
     */
    private final String PARENT_PATH = "resource/";
    /**
     * Add all 5-letter words to hash set so that can fast find if word exits 
     */
    private HashSet<String> verifyList = new HashSet<>();
    /**
     * Store the answer of the game
     */
    private String answer;

    /**
     * Read answer words and all 5-letter words from files
     * @param gameMode the difficulty level of the game
     */
    public WordLists(GameConfiguration.GameMode gameMode){
        readVerifyList();
        chooseAnswer(gameMode);
    }
    /**
     * Read all 5-letter words from files
     */
    private void readVerifyList(){
        File f = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            f = new File("resource/words.txt");
            isr = new InputStreamReader(new FileInputStream(f));
            br = new BufferedReader(isr);
            String word = null;
            while((word = br.readLine()) != null){
                verifyList.add(word.toUpperCase());
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Read answer words from files and choose a answer randomly
     * @param gameMode
     */
    private void chooseAnswer(GameConfiguration.GameMode gameMode){
        ArrayList<String> wordList = new ArrayList<>();
        String fileName = null;
        switch (gameMode) {
            case EASY:
                fileName = "easy.txt";
                break;
            case MEDIUM:
                fileName = "mid.txt";
                break;
            case HARD:
                fileName = "hard.txt";
                break;
        }

        try {
            File f = new File(PARENT_PATH + fileName);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
            BufferedReader br = new BufferedReader(isr);
            String word = null;
            while((word = br.readLine()) != null){
                wordList.add(word);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        Random r = new Random();
        int answerIndex = r.nextInt(wordList.size());
        this.answer = wordList.get(answerIndex).toUpperCase();
    }

    /**
     * Verify if a word exits 
     * @param word word to be verified
     * @return if the word exits
     */
    public boolean verifyWord(String word){
        return verifyList.contains(word);
    }

    /**
     * Get the answer of the game
     * @return the answer of the game
     */
    public String getAnswer(){
        return this.answer;
    }

    /**
     * get search result
     * @param regex regex of the word
     * @return the search result
     */
    public StringBuilder getTargetWords(String regex){
        StringBuilder words = new StringBuilder();
        for (String string : verifyList) {
            if(string.matches(regex)){
                words.append(string + "\n");
            }
        }
        return words;
    }
}
