package bridge.controller;

import bridge.domain.BridgeGame;
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

    public GameController(InputView inputView, OutputView outputView, GameService gameService,
            BridgeGame bridgeGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
        this.bridgeGame = bridgeGame;
    }

    public void start() {
        outputView.printStartMessage();
        int bridgeLength = handleBridgeLength();
        List<String> bridge = gameService.generateBridge(bridgeLength);
        List<String> copyBridge = new ArrayList<>(bridge);
        List<String> upGameResult = new ArrayList<>();
        List<String> downGameResult = new ArrayList<>();
        boolean gameSuccess = false;

        while(copyBridge.size() != 0) {
            String movingChoice = handleMovingBox();
            boolean canMove = bridgeGame.move(copyBridge, movingChoice);
            if (canMove) {
                String remove = copyBridge.remove(0);
                if (remove.equals("U")) {
                    upGameResult.add(" O ");
                    downGameResult.add("   ");
                } else{
                    downGameResult.add(" O ");
                    upGameResult.add("   ");
                }
                if (copyBridge.size() == 0) {
                    System.out.println("최종 게임 결과");
                }
                System.out.println("[" + String.join("|", upGameResult) + "]");
                System.out.println("[" + String.join("|", downGameResult) + "]");
            }

            if (!canMove) {
                String userInput = inputView.readGameCommand();
                boolean retry = bridgeGame.retry(userInput);
                if (!retry) {
                    String compareResult = copyBridge.get(0);
                    if (compareResult.equals("U")) {
                        upGameResult.add(" X ");
                        downGameResult.add("   ");
                    } else{
                        downGameResult.add(" X ");
                        upGameResult.add("   ");
                    }
                    System.out.println("최종 게임 결과");
                    System.out.println("[" + String.join("|", upGameResult) + "]");
                    System.out.println("[" + String.join("|", downGameResult) + "]");
                    break;
                } else {
                    copyBridge = new ArrayList<>(bridge);
                    upGameResult = new ArrayList<>();
                    downGameResult = new ArrayList<>();
                }
            }
        }
        if(copyBridge.size() == 0) {
            gameSuccess = true;
        }

        if(gameSuccess) {
            System.out.println("게임 성공 여부: 성공");
        } else {
            System.out.println("게임 성공 여부: 실패");
        }
        System.out.println("총 시도한 횟수: " + bridgeGame.getTryCount());
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
