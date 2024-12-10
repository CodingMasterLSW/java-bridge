package bridge.domain;

import java.util.List;

public class BridgeGame {

    private int tryCount = 1;

    private BridgeGame() {
    }

    public static BridgeGame create() {
        return new BridgeGame();
    }

    public boolean move(List<String> bridge, String movingChoice) {
        return bridge.get(0).equals(movingChoice);
    }

    public boolean retry(String userInput) {
        if (userInput.equals("R")) {
            this.tryCount++;
            return true;
        }
        return false;
    }

    public int getTryCount() {
        return tryCount;
    }
}
