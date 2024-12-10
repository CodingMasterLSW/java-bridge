package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public class GameResult {

    private static final String BLANK = " ";

    private List<String> up;
    private List<String> down;

    private GameResult() {
        this.up = new ArrayList<>();
        this.down = new ArrayList<>();
    }

    public static GameResult create() {
        return new GameResult();
    }

    public void addUpResult(String result) {
        up.add(BLANK + result  + BLANK);
        down.add(BLANK.repeat(3));
    }

    public void addDownResult(String result) {
        up.add(BLANK.repeat(3));
        down.add(BLANK + result + BLANK);
    }

    public String getUpResult() {
        return String.join("|", up);
    }

    public String getDownResult() {
        return String.join("|", down);
    }

    public void resetGameResult() {
        up.clear();
        down.clear();
    }
}
