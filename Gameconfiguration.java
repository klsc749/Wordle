import java.awt.*;
public class Gameconfiguration {
    public static final int WORDLE_WINDOW_WIDTH = 800;
    public static final int WORDLE_WINDOW_HEIGHT = 1000;

    public static final int START_WINDOW_WIDTH = 400;
    public static final int START_WINDOW_HEIGHT = 500;

    public static final Color GRAY = new Color(120,124,126);
    public static final Color YELLOW = new Color(201,180,88);
    public static final Color GREEN = new Color(106,170,100);
    public static final Color LABEL_EMPTY = new Color(213, 213, 213);
    public static final Color Key_BUTTON_NOT_PRESS_COLOR = new Color(211, 214, 218);

    public enum CharState{
        CONTAIN_AND_RIGHT_POSITION,
        CONTAIN_BUT_WRONG_POSITION,
        DO_NOT_CONTAIN,
        EMPTY
    }

    public enum GameMode{
        EASY,
        MEDIUM,
        HARD
    }

    private Gameconfiguration(){

    }
}
