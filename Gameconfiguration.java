import java.awt.*;

/**
 * The {@code GameConfiguration} class includes the configuration of the game.
 * The properties of The {@code GameConfiguration} class are all static
 * @author Wang Xin
 * @version 1.0
 */
public final class GameConfiguration {
    /**
     * The width of the wordle window {@link WordleWindow}
     */
    public static final int WORDLE_WINDOW_WIDTH = 800;
    /**
     * The height of the wordle window {@link WordleWindow}
     */
    public static final int WORDLE_WINDOW_HEIGHT = 1000;

    /**
     * The width of the start window {@link StartWindow}
     */
    public static final int START_WINDOW_WIDTH = 400;
    /**
     * The height of the start window {@link StartWindow}
     */
    public static final int START_WINDOW_HEIGHT = 500;

    /**
     * The width of the result window {@link ResultWindow}
     */
    public static final int RESULT_WINDOW_WIDTH = 300;
    /**
     * The height of the result window {@link ResultWindow}
     */
    public static final int RESULT_WINDOW_HEIGHT = 400;

    /**
     * The width of the helper window {@link HelperWindow}
     */
    public static final int HELPER_WINDOW_WIDTH = 400;
    /**
     * The height of the helper window {@link HelperWindow}
     */
    public static final int HELPER_WINDOW_HEIGHT= 500;

    /**
     * The gray color
     */
    public static final Color GRAY = new Color(120,124,126);
    /**
     * The yellow color
     */
    public static final Color YELLOW = new Color(201,180,88);
    /**
     * The green color
     */
    public static final Color GREEN = new Color(106,170,100);
    /**
     * The color of {@link CharacterLabel} when the label has no text
     */
    public static final Color LABEL_EMPTY = new Color(213, 213, 213);
    /**
     * The color of {@link KeyButton} when the key button is not pressed
     */
    public static final Color Key_BUTTON_NOT_PRESS_COLOR = new Color(211, 214, 218);

    /**
     * The font of the game
     */
    public static final String GAME_FONT = "Arial";

    /**
     * The state of the {@link CharacterLabel}
     * <p>
     * {@link CharState#CONTAIN_AND_RIGHT_POSITION} means that the answer includes the char and the char is at right position.
     * <p>
     * {@link CharState#CONTAIN_BUT_WRONG_POSITION} means that the answer includes the char but the char is at wrong position.
     * <p>
     * {@link CharState#DO_NOT_CONTAIN} means that the answer doesn't includes the char.
     * <p>
     * {@link CharState#EMPTY} means that the label is empty.
     */
    public enum CharState{
        CONTAIN_AND_RIGHT_POSITION,
        CONTAIN_BUT_WRONG_POSITION,
        DO_NOT_CONTAIN,
        EMPTY
    }

    /**
     * The mode of the game
     */
    public enum GameMode{
        EASY,
        MEDIUM,
        HARD
    }

    /**
     * The constructor of {@code GameConfiguration} class is private so that {@code GameConfiguration} class can't be instantiated
     */
    private GameConfiguration(){

    }
}