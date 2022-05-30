public class GameManager {
    private static GameManager instance;
    private String answer;
    private WordLists wordLists;
    private WordlePanel wPanel;
    private KeyboardPanel keyboardPanel;
    private WordleWindow wordleWindow;
    private StartWindow startWindow;
    private ResultWindow resultDialog;
    private int currentRow = 0;
    private int currentCol = -1;
    private Gameconfiguration.GameMode gameMode;

    private GameManager(){

    }

    public static GameManager getInstance(){
        if(GameManager.instance == null){
            GameManager.instance = new GameManager();
        }

        return GameManager.instance;
    }

    public void start(){
        init();
    }

    public void play(){
        closeStartWindow();
        showWordleWindow();
    }

    public void restart(){
        closeResultDialog();
        start();
    }

    private void init(){
        showStartWindow();
        currentRow = 0;
        currentCol = -1;
    }


    private void showWordleWindow(){
        createWordleWindow();
        this.wordleWindow.setVisible(true);
    }

    private void closeWordleWindow(){
        this.wordleWindow.dispose();
    }

    private void showStartWindow(){
        createStartWindow();
        this.startWindow.setVisible(true);
    }

    private void closeStartWindow(){
        this.startWindow.dispose();
    }

    private void createWordleWindow(){
        this.wordleWindow = new WordleWindow();
        this.wPanel = this.wordleWindow.getWordlePanel();
        this.keyboardPanel = this.wordleWindow.getKeyboardPanel();
        wordLists = new WordLists(this.gameMode);
        this.answer = wordLists.getAnswer();
        System.out.println(this.answer);
        this.wordleWindow.dispose();
    }

    private void createStartWindow(){
        this.startWindow = new StartWindow();
        this.startWindow.dispose();
    }

    private void showResultDialog(String result){
        closeWordleWindow();
        this.resultDialog = new ResultWindow(result);
    }

    private void closeResultDialog(){
        this.resultDialog.dispose();
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

        if(currentRow == 6){
            showResultDialog("FAIL");
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
        
        int correctCNt = 0;

        for(int i = 0; i < 5; i++){
            int index = answer.indexOf(wPanel.getCharacterLabel(currentRow, i).getText());
            if(index == -1){
                state = Gameconfiguration.CharState.DO_NOT_CONTAIN;
            }
            else{
                if(answer.charAt(i) == wPanel.getCharacterLabel(currentRow, i).getText().toCharArray()[0]){
                    state = Gameconfiguration.CharState.CONTAIN_AND_RIGHT_POSITION;
                    correctCNt++;
                }
                else{
                    state =  Gameconfiguration.CharState.CONTAIN_BUT_WRONG_POSITION;
                }
            }
            wPanel.getCharacterLabel(currentRow, i).setLabelState(state);
            keyboardPanel.getKeyButton(wPanel.getCharacterLabel(currentRow, i).getText()).setKeyButtonState(state);;
        }

        if(correctCNt == 5){
            showResultDialog("SUCCESS");
        }
    }


    public void setGameMode(String gameMode){
        switch (gameMode) {
            case "EASY":
                this.gameMode = Gameconfiguration.GameMode.EASY;
                break;
            case "MEDIUM":
                this.gameMode = Gameconfiguration.GameMode.MEDIUM;
                break;
            case "HARD":
                this.gameMode = Gameconfiguration.GameMode.HARD;
                break;
            default:
                this.gameMode = Gameconfiguration.GameMode.EASY;
                break;
        }
    }

    public String getAnswer(){
        return this.answer;
    }
}
