import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class WordLists {
    private final String PARENT_PATH = "resource/";
    private HashSet<String> verifyList = new HashSet<>();
    private String answer;

    public WordLists(Gameconfiguration.GameMode gameMode){
        readVerifyList();
        chooseAnswer(gameMode);
    }

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

    private void chooseAnswer(Gameconfiguration.GameMode gameMode){
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

    public boolean verifyWord(String word){
        return verifyList.contains(word);
    }

    public String getAnswer(){
        return this.answer;
    }
}
