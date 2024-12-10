package bridge.config;

import bridge.controller.GameController;
import bridge.service.GameService;
import bridge.view.InputView;
import bridge.view.OutputView;

public class AppConfig {
    private AppConfig() {
    }

    public static GameController createController() {
        return new GameController(InputView.create(), OutputView.create(), createService());
    }

    public static GameService createService() {
        return new GameService();
    }
}
