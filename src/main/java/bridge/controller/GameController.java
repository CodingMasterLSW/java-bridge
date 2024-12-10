package bridge.controller;

import bridge.service.GameService;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.function.Supplier;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;

    public GameController(InputView inputView, OutputView outputView, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void start() {
        outputView.printStartMessage();
        int bridgeLength = handleBridgeLength();
    }

    private int handleBridgeLength() {
        return retryOnInvalidInput(() -> {
            inputView.printBridgeLengthMessage();
            return inputView.readBridgeSize();
        });
    }

    private <T> T retryOnInvalidInput(Supplier<T> input) {
        while (true) {
            try {
                return input.get();
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }
}
