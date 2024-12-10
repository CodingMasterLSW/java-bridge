package bridge.exception;

public enum ErrorMessage {
    NOT_BLANK_INPUT("입력은 공백일 수 없습니다. 다시 입력해주세요."),
    NOT_NUMBER("양의 정수만 입력 가능합니다. 다시 입력해주세요."),
    INVALID_NUMBER_RANGE("다리 길이는 3부터 20사이의 숫자여야 합니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
