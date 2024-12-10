package bridge.view;

import bridge.domain.GameResult;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String GAME_START_MESSAGE = "다리 건너기 게임을 시작합니다.";
    private static final String GAME_SUCCESS_MESSAGE = "게임 성공 여부: 성공";
    private static final String GAME_FAIL_MESSAGE = "게임 성공 여부: 실패";
    private static final String TRY_COUNT_MESSAGE = "총 시도한 횟수: %s";
    private static final String GAME_RESULT_MESSAGE = "최종 게임 결과";
    private static final String GAME_RESULT = "[%s]";
    private static final String BLANK = "";


    private OutputView() {
    }

    public static OutputView create() {
        return new OutputView();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printStartMessage() {
        printMessage(GAME_START_MESSAGE);
    }

    public void printGameSuccessOrFailure(boolean status) {
        if (status) {
            System.out.println(GAME_SUCCESS_MESSAGE);
            return;
        }
        System.out.println(GAME_FAIL_MESSAGE);
    }

    public void printResult(GameResult gameResult) {
        printMessage(GAME_RESULT_MESSAGE);
        printMap(gameResult);
    }

    public void printMap(GameResult gameResult) {
        System.out.printf(GAME_RESULT, gameResult.getUpResult());
        printMessage(BLANK);
        System.out.printf(GAME_RESULT, gameResult.getDownResult());
        printMessage(BLANK);
    }

    public void printTryCountResult(int tryCount) {
        System.out.printf(TRY_COUNT_MESSAGE, tryCount);
    }


}
