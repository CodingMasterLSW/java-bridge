package bridge.view;

import static bridge.exception.ErrorMessage.INVALID_INPUT;
import static bridge.exception.ErrorMessage.INVALID_NUMBER_RANGE;
import static bridge.exception.ErrorMessage.NOT_BLANK_INPUT;
import static bridge.exception.ErrorMessage.NOT_NUMBER;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private static final String BRIDGE_LENGTH_MESSAGE = "다리의 길이를 입력해주세요.";
    private static final String MOVE_MESSAGE = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String AGAIN_MESSAGE = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";
    private static final String BLANK = "";
    private static final Pattern NUMBER = Pattern.compile("\\d+");
    private static final int MIN_BRIDGE_LENGTH = 3;
    private static final int MAX_BRIDGE_LENGTH = 20;


    private InputView() {
    }

    public static InputView create() {
        return new InputView();
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        String userInput = userInput();
        validateInput(userInput);
        validateNumber(userInput);
        validateNumberRange(userInput);
        return Integer.parseInt(userInput);
    }

    public void printBridgeLengthMessage() {
        printMessage(BLANK);
        printMessage(BRIDGE_LENGTH_MESSAGE);
    }

    public void printMoveMessage() {
        printMessage(BLANK);
        printMessage(MOVE_MESSAGE);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        String userInput = userInput();
        validateUserChoose(userInput, "U", "D");
        return userInput;
    }

    private void validateUserChoose(String userInput, String firstCondition, String secondCondition) {
        if (!userInput.equals(firstCondition) && !userInput.equals(secondCondition)) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    public String readGameCommand() {
        printMessage(AGAIN_MESSAGE);
        String userInput = userInput();
        validateUserChoose(userInput, "R", "Q");
        return userInput;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    private String userInput() {
        String userInput = Console.readLine();
        validateInput(userInput);
        return userInput;
    }

    private void validateInput(String userInput) {
        if (userInput.isBlank() || userInput == null) {
            throw new IllegalArgumentException(NOT_BLANK_INPUT.getMessage());
        }
    }

    private void validateNumber(String userInput) {
        if (!NUMBER.matcher(userInput).matches()) {
            throw new IllegalArgumentException(NOT_NUMBER.getMessage());
        }
    }

    private void validateNumberRange(String userInput) {
        int bridgeLength = Integer.parseInt(userInput);
        if (bridgeLength<MIN_BRIDGE_LENGTH || bridgeLength>MAX_BRIDGE_LENGTH) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE.getMessage());
        }
    }
}
