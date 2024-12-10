package bridge;

import bridge.config.AppConfig;
import bridge.controller.GameController;

public class Application {

    public static void main(String[] args) {
        GameController gameController = AppConfig.createController();
        gameController.start();
    }
}
