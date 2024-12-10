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

    public void printFinalResult(GameResult gameResult) {
        printMessage(GAME_RESULT_MESSAGE);
        printResult(gameResult);
    }

    public void printResult(GameResult gameResult) {
        System.out.printf(GAME_RESULT, gameResult.getUpResult());
        printMessage(BLANK);
        System.out.printf(GAME_RESULT, gameResult.getDownResult());
        printMessage(BLANK);
    }


    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap() {
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(int tryCount) {
        System.out.printf(TRY_COUNT_MESSAGE, tryCount);
    }


}
