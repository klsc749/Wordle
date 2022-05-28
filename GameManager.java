public class GameManager {
    private static GameManager instance;
    private String answer;
    private WordLists wordLists;
    private WordlePanel wPanel;
    private KeyboardPanel keyboardPanel;
    private WordleWindow wordleWindow;
    private int currentRow = 0;
    private int currentCol = -1;
    public enum GameMode{
        EASY,
        MEDIUM,
        HARD
    }

    private GameManager(){

    }

    public void play(){
        init();
    }

    public void init(){
        this.wordleWindow = new WordleWindow();
        this.wPanel = this.wordleWindow.getWordlePanel();
        this.keyboardPanel = this.wordleWindow.getKeyboardPanel();
        wordLists = new WordLists(GameMode.EASY);
        this.answer = wordLists.getAnswer();
        System.out.println(answer);
    }
    
    public static GameManager getInstance(){
        if(GameManager.instance == null){
            GameManager.instance = new GameManager();
        }

        return GameManager.instance;
    }

    public void handleInput(char c){
        wordleWindow.setTipAndType("Please input words :)", WordleWindow.TipType.HINT);

        if(c == '\n'){
            if(currentCol == 4){
                if(checkWord()){
                    compareWithAnswer();
                    currentRow++;
                    currentCol = -1;
                }else{
                    wordleWindow.setTipAndType("Please input correct word", WordleWindow.TipType.WARNING);
                }
            }else{
                wordleWindow.setTipAndType("Please input 5 letters", WordleWindow.TipType.WARNING);
            }
        }else if(c == '\b'){
            if(currentCol != -1){
                wPanel.getCharacterLabel(currentRow, currentCol).setLabelState(Gameconfiguration.CharState.EMPTY);
                currentCol--;
            }else{
                wordleWindow.setTipAndType("You can't delete now", WordleWindow.TipType.WARNING);
            }
        }else if(c >= 'A' && c <= 'Z'){
            if(currentCol > 3){
                wordleWindow.setTipAndType("You cant't input more letters", WordleWindow.TipType.WARNING);
            }else{
                currentCol++;
                wPanel.getCharacterLabel(currentRow, currentCol).setText(Character.toString(c));
            }
        }else{
            wordleWindow.setTipAndType("Please input letters", WordleWindow.TipType.WARNING);
        }
    }

    private boolean checkWord(){
        String word = "";
        for(int i = 0; i < 5; i++){
            word += wPanel.getCharacterLabel(currentRow, i).getText();
        }
        return wordLists.verifyWord(word);
    }

    private void compareWithAnswer(){
       Gameconfiguration.CharState state = null;
        
        for(int i = 0; i < 5; i++){
            int index = answer.indexOf(wPanel.getCharacterLabel(currentRow, i).getText());
            if(index == -1){
                state = Gameconfiguration.CharState.DO_NOT_CONTAIN;
            }
            else{
                if(answer.charAt(i) == wPanel.getCharacterLabel(currentRow, i).getText().toCharArray()[0]){
                    state = Gameconfiguration.CharState.CONTAIN_AND_RIGHT_POSITION;
                }
                else{
                    state =  Gameconfiguration.CharState.CONTAIN_BUT_WRONG_POSITION;
                }
            }
            wPanel.getCharacterLabel(currentRow, i).setLabelState(state);
            keyboardPanel.getKeyButton(wPanel.getCharacterLabel(currentRow, i).getText()).setKeyButtonState(state);;
        }
    }
}
