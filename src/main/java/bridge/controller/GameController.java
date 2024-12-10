package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.domain.GameResult;
import bridge.service.GameService;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;
    private final BridgeGame bridgeGame;
    private final GameResult gameResult;

    public GameController(InputView inputView, OutputView outputView, GameService gameService,
            BridgeGame bridgeGame, GameResult gameResult) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
        this.bridgeGame = bridgeGame;
        this.gameResult = gameResult;
    }

    public void start() {
        outputView.printStartMessage();
        int bridgeLength = handleBridgeLength();
        List<String> bridge = gameService.generateBridge(bridgeLength);
        List<String> copyBridge = new ArrayList<>(bridge);

        copyBridge = handleGame(copyBridge, bridge);
        decideGameResult(copyBridge);

        outputView.printGameSuccessOrFailure(gameResult.isSuccess());
        outputView.printResult(bridgeGame.getTryCount());
    }

    private void decideGameResult(List<String> copyBridge) {
        if (copyBridge == null) {
            return;
        }
        if (copyBridge.size() == 0) {
            gameResult.gameClear();
        }
    }

    private List<String> handleGame(List<String> copyBridge, List<String> bridge) {
        while (copyBridge.size() != 0) {
            copyBridge = handleAllMove(copyBridge, bridge);
            if (copyBridge == null) {
                break;
            }
        }
        return copyBridge;
    }

    private List<String> handleAllMove(List<String> copyBridge, List<String> bridge) {
        String movingChoice = handleMovingBox();
        boolean canMove = bridgeGame.move(copyBridge, movingChoice);
        if (canMove) {
            handleCanMove(copyBridge);
        }
        if (!canMove) {
            copyBridge = handleCannotMove(copyBridge, bridge);
            if (copyBridge == null) {
                return null;
            }
        }
        return copyBridge;
    }

    private List<String> handleCannotMove(List<String> copyBridge, List<String> bridge) {
        String userInput = inputView.readGameCommand();
        boolean retry = bridgeGame.retry(userInput);
        if (!retry) {
            String compareResult = copyBridge.get(0);
            addGameResult(compareResult);
            outputView.printFinalResult(gameResult);
            return null;
        }
        copyBridge = new ArrayList<>(bridge);
        gameResult.resetGameResult();
        return copyBridge;
    }

    private void addGameResult(String compareResult) {
        if (compareResult.equals("U")) {
            gameResult.addUpResult("X");
        }
        if (compareResult.equals("D")) {
            gameResult.addDownResult("X");
        }
    }

    private void handleCanMove(List<String> copyBridge) {
        String remove = copyBridge.remove(0);
        if (remove.equals("U")) {
            gameResult.addUpResult("O");
        }
        if (remove.equals("D")) {
            gameResult.addDownResult("O");
        }

        if (copyBridge.size() == 0) {
            outputView.printFinalResult(gameResult);
            return;
        }
        outputView.printResult(gameResult);
    }


    private String handleMovingBox() {
        inputView.printMoveMessage();
        return retryOnInvalidInput(() -> {
            return inputView.readMoving();
        });
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
