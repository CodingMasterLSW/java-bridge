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

        while(copyBridge.size() != 0) {
            String movingChoice = handleMovingBox();
            boolean canMove = bridgeGame.move(copyBridge, movingChoice);
            if (canMove) {
                String remove = copyBridge.remove(0);
                if (remove.equals("U")) {
                    gameResult.addUpResult("O");
                } else{
                    gameResult.addDownResult("O");
                }
                if (copyBridge.size() == 0) {
                    outputView.printFinalResult(gameResult);
                    break;
                }
                outputView.printResult(gameResult);
            }

            if (!canMove) {
                String userInput = inputView.readGameCommand();
                boolean retry = bridgeGame.retry(userInput);
                if (!retry) {
                    String compareResult = copyBridge.get(0);
                    if (compareResult.equals("U")) {
                        gameResult.addUpResult("X");
                    } else{
                        gameResult.addDownResult("X");
                    }
                    outputView.printFinalResult(gameResult);
                    break;
                } else {
                    copyBridge = new ArrayList<>(bridge);
                    gameResult.resetGameResult();
                }
            }
        }
        if(copyBridge.size() == 0) {
            gameResult.gameClear();
        }

        outputView.printGameSuccessOrFailure(gameResult.isSuccess());
        outputView.printResult(bridgeGame.getTryCount());
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
